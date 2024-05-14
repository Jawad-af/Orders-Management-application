package org.ordersmgmtapp.model;

/**
 * The Product class represents a product entity in the system.
 */
public class Product {
    private long id;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;

    /**
     * Constructs a new Product object with default values.
     */
    public Product(){}

    /**
     * Constructs a new Product object with the specified ID, name, description, price, and stock quantity.
     * @param id The ID of the product.
     * @param name The name of the product.
     * @param description The description of the product.
     * @param price The price of the product.
     * @param stockQuantity The stock quantity of the product.
     */
    public Product(long id, String name, String description, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    /**
     * Constructs a new Product object with the specified name, description, price, and stock quantity.
     * @param name The name of the product.
     * @param description The description of the product.
     * @param price The price of the product.
     * @param stockQuantity The stock quantity of the product.
     */
    public Product(String name, String description, double price, int stockQuantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    /**
     * Retrieves the ID of the product.
     * @return The ID of the product.
     */
    public long getId() {
        return id;
    }

    /**
     * Retrieves the name of the product.
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the description of the product.
     * @return The description of the product.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the product.
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the price of the product.
     * @return The price of the product.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     * @param price The price to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Retrieves the stock quantity of the product.
     * @return The stock quantity of the product.
     */
    public int getStockQuantity() {
        return stockQuantity;
    }

    /**
     * Sets the stock quantity of the product.
     * @param stockQuantity The stock quantity to set.
     */
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    /**
     * Returns a string representation of the product.
     * @return A string representation of the product.
     */
    @Override
    public String toString() {
        return  "id:  " + id +
                "  |  name:  " + name +
                "  |  description:  " + description +
                "  |  price:  " + price +
                "  |  stock:  " + stockQuantity;
    }
}
