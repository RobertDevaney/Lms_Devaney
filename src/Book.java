/**
 * Robert
 * CEN-3024C-15339
 * September 9, 2024
 * Book.java
 * This class represents a book in a library management system. It stores details such as ID, title, and author of the book.
 */
class Book {
    private int id;
    private String title;
    private String author;

    /**
     * Constructor: Book
     * Creates a book object with specified ID, title, and author.
     * Parameter id the unique identifier for the book
     * Parameter title the title of the book
     * Parameter author the author of the book
     */

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    /**
     * Method: getId
     * Returns the ID of the book.
     */

    public int getId() {
        return id;
    }

    /**
     * Method: getTitle
     * Returns the title of the book.
     */

    public String getTitle() {
        return title;
    }

    /**
     * Method: getAuthor
     * Returns the author of the book.
     */

    public String getAuthor() {
        return author;
    }

    /**
     * Method: toString
     * Provides a string representation of the book including its ID, title, and author.
     */

    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author;
    }
}