package org.ordersmgmtapp.model;

public class Product {
    private static long code = 0;
    private static String id;
    private String name;
    private String description;
    private double price;
    public Product(String name, String description, double price) {
        this.id = "COD00" + ++code;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public static long getCode() {
        return code;
    }

    public static void setCode(long code) {
        Product.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return '{' +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
