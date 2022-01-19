package business.book;

public class Book {

    private final long bookId;
    private final String title;
    private final String author;
    private final int price;
    private final boolean isPublic;
    private final long categoryId;
    private final String description;
    private final int rating;
    private final boolean isFeatured;


    public Book(long bookId, String title, String author, String description, int price, int rating, boolean isPublic, boolean isFeatured, long categoryId) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.price = price;
        this.isPublic = isPublic;
        this.categoryId = categoryId;
        this.description = description;
        this.rating = rating;
        this.isFeatured = isFeatured;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", isPublic=" + isPublic +
                ", categoryId=" + categoryId +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", isFeatured=" + isFeatured +
                '}';
    }

    public long getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPrice() {
        return price;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }

    public boolean getIsFeatured() {
        return isFeatured;
    }
}
