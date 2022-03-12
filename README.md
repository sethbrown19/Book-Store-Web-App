# Book-Store-Web-App
Book It Up! is a Single Page Application bookstore I made in the Masters program at Virginia Tech. Course CS 5244 Web Application Development.

This is the code I used for the final submittal of the bookstore ecommerce web application. In this application, I used the Vue Framework with HTML, CSS, and Javascript for the front end (no component libraries or templates), Java and JDBC for the business side/ REST Api's, and MySQL for the database.

The pages in the web application that you can navigate to are the home page, four book category pages with respective books in that category, a cart page,  a checkout page, a order confirmation page, and a 404 error page if someone tries to access an uknown page. Functions of this web app: you can access different pages by the hamburger menu or by different icons and buttons through out the app, you can add books to your cart on cateogry pages,  you can add and remove books in the cart page, you can enter you personal information in the checkout page and submit this information to receive an order. 

All information when submitted via the checkout form page is added to the MySQL data base that is POSTed by REST Api's developed in Java. Book information is also stored in the MySQL database which I used GET Api's, developed in Java, to access for the user interface in the Bookstore.
