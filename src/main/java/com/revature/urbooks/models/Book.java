package com.revature.urbooks.models;

public class Book {
    private String id;
    private String isbn;
    private String title;
    private String publisher_id;
    private double  price;
    private int quantity;

    public Book() {

    }

    public Book(String id, String isbn, String title, String publisher_id, double price, int quantity) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.publisher_id = publisher_id;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(String publisher_id) {
        this.publisher_id = publisher_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", publisher_id='" + publisher_id + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
