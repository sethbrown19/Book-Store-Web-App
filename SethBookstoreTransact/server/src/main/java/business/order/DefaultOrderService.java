package business.order;

import api.ApiException;
import business.BookstoreDbException;
import business.JdbcUtils;
import business.book.Book;
import business.book.BookDao;
import business.cart.ShoppingCart;
import business.cart.ShoppingCartItem;
import business.customer.Customer;
import business.customer.CustomerDao;
import business.customer.CustomerForm;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DefaultOrderService implements OrderService {

    private BookDao bookDao;
    private CustomerDao customerDao;
    private OrderDao orderDao;
    private LineItemDao lineItemDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setLineItemDao(LineItemDao lineItemDao) {
        this.lineItemDao = lineItemDao;
    }

    @Override
    public OrderDetails getOrderDetails(long orderId) {
        Order order = orderDao.findByOrderId(orderId);
        Customer customer = customerDao.findByCustomerId(order.getCustomerId());
        List<LineItem> lineItems = lineItemDao.findByOrderId(orderId);
        List<Book> books = lineItems
                .stream()
                .map(lineItem -> bookDao.findByBookId(lineItem.getBookId()))
                .collect(Collectors.toList());
        return new OrderDetails(order, customer, lineItems, books);
    }


    private Date getDate(String monthString, String yearString) {
        int month = Integer.parseInt(monthString);
        int year = Integer.parseInt(yearString);
        return Date.from(YearMonth.of(year, month).atEndOfMonth().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public long placeOrder(CustomerForm customerForm, ShoppingCart cart) {

        validateCustomer(customerForm);
        validateCart(cart);

        try (Connection connection = JdbcUtils.getConnection()) {
            Date date = getDate(
                    customerForm.getCcExpiryMonth(),
                    customerForm.getCcExpiryYear());
            return performPlaceOrderTransaction(
                    customerForm.getName(),
                    customerForm.getAddress(),
                    customerForm.getPhone(),
                    customerForm.getEmail(),
                    customerForm.getCcNumber(),
                    date, cart, connection);
        } catch (SQLException e) {
            throw new BookstoreDbException("Error during close connection for customer order", e);
        }

    }

    private long performPlaceOrderTransaction(
            String name, String address, String phone,
            String email, String ccNumber, Date date,
            ShoppingCart cart, Connection connection) {
        try {
            connection.setAutoCommit(false);
            long customerId = customerDao.create(
                    connection, name, address, phone, email,
                    ccNumber, date);
            long customerOrderId = orderDao.create(
                    connection,
                    cart.getComputedSubtotal() + cart.getSurcharge(),
                    generateConfirmationNumber(), customerId);
            for (ShoppingCartItem item : cart.getItems()) {
                lineItemDao.create(connection, customerOrderId,
                        item.getBookId(), item.getQuantity());
            }
            connection.commit();
            return customerOrderId;
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new BookstoreDbException("Failed to roll back transaction", e1);
            }
            return 0;
        }
    }

    private int generateConfirmationNumber() {
        Random random = new Random();
        return random.nextInt(999999999);
    }


    private void validateCustomer(CustomerForm customerForm) {
        if (!nameIsValid(customerForm.getName())) {
            throw new ApiException.InvalidParameter("Invalid name field");
        }
        if (!phoneIsValid(customerForm.getPhone())) {
            throw new ApiException.InvalidParameter("Invalid phone field");
        }
        if (!emailIsValid(customerForm.getEmail())) {
            throw new ApiException.InvalidParameter("Invalid email field");
        }
        if (!addressIsValid(customerForm.getAddress())) {
            throw new ApiException.InvalidParameter("Invalid address field");
        }
        if (!ccNumberIsValid(customerForm.getCcNumber())) {
            throw new ApiException.InvalidParameter("Invalid credit card number field");
        }

        if (!expiryDateIsValid(customerForm.getCcExpiryMonth(), customerForm.getCcExpiryYear())) {
            throw new ApiException.InvalidParameter("Invalid expiry date");

        }
    }

    private boolean phoneIsValid(String phone) {
        if (phone == null) return false;
        phone = phone.replaceAll(" ", "");
        phone = phone.replaceAll("-", "");
        phone = phone.replaceAll("\\(", "");
        phone = phone.replaceAll("\\)", "");
        if (phone.equals("")) {
            return false;
        }
        if (phone.length() != 10) {
            return false;
        }
        return true;
    }

    private boolean emailIsValid(String email) {
        if (email == null) {
            return false;
        }
        if (email.equals("")) {
            return true;
        }
        if (!email.matches("\\S+")) {
            return false;
        }
        if (!email.contains("@")) {
            return false;
        }
        return !email.endsWith(".");
    }

    private boolean nameIsValid(String name) {
        if (name == null) {
            return false;
        }
        if (name.equals("")) {
            return false;
        }
        if (name.length() < 4) {
            return false;
        }
        return name.length() <= 45;
    }

    private boolean addressIsValid(String address) {
        if (address == null) {
            return false;
        }
        if (address.equals("")) {
            return false;
        }
        if (address.length() < 4) {
            return false;
        }
        return address.length() <= 45;
    }

    private boolean ccNumberIsValid(String ccNumber) {
        ccNumber = ccNumber.replaceAll("-", "");
        ccNumber = ccNumber.replaceAll(" ", "");
        if (ccNumber == null) {
            return false;
        }
        if (ccNumber.equals("")) {
            return false;
        }
        if (ccNumber.length() < 13) {
            return false;
        }
        return ccNumber.length() <= 17;
    }

    private boolean expiryDateIsValid(String ccExpiryMonth, String ccExpiryYear) {
        int currentYear = Year.now(ZoneId.of("America/New_York")).getValue();
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int expireYear = Integer.parseInt(ccExpiryYear);
        int expireMonth = Integer.parseInt(ccExpiryMonth);

        if (expireYear < currentYear) {
            return false;
        }
        if (expireYear == currentYear && expireMonth < currentMonth) {
            return false;
        }
        return true;

    }

    private void validateCart(ShoppingCart cart) {

        if (cart.getItems().size() <= 0) {
            throw new ApiException.InvalidParameter("Cart is empty.");
        }

        cart.getItems().forEach(item -> {
            if (item.getQuantity() < 0 || item.getQuantity() > 99) {
                throw new ApiException.InvalidParameter("Invalid quantity");
            }
            Book databaseBook = bookDao.findByBookId(item.getBookId());
            if (item.getBookForm().getPrice() != databaseBook.getPrice()) {
                throw new ApiException.InvalidParameter("Invalid price");
            }
            if (item.getBookForm().getCategoryId() != databaseBook.getCategoryId()) {
                throw new ApiException.InvalidParameter("Invalid category");
            }
        });
    }

}
