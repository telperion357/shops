package ua.pforce.task.shops.domain;

import java.math.BigDecimal;

public class Product {

    public enum Status {
        AVAILABLE,
        ABSENT,
        EXPECTED;
    }

    private long id;

    private String title;

    private BigDecimal price;

    private Status status;

    /**
     * This constructor could be used before saving the product to the database,
     * because id field is autogenerated in the database.
     * @param title
     * @param price
     * @param status
     */
    public Product(String title, BigDecimal price, Status status) {
        this.title = title;
        this.price = price;
        this.status = status;
    }

    public Product(long id, String title, BigDecimal price, Status status) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.status = status;
    }

    /**
     * helper constructor
     * @param id
     * @param product
     */
    public Product(long id, Product product) {
        this.id = id;
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.status = product.getStatus();
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}