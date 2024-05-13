package org.ordersmgmtapp.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static long orderId = 0;
    private static long id;
    private Client client;
    private List<Product> products;
    private double totalPrice = 0;

    public Order(Client client) {
        this.id = ++orderId;
        this.client = client;
        this.products = new ArrayList<>();
    }

    public void calculateTotalPrice() {
        if(products == null)
            return;

        int sum = 0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        totalPrice = sum;
    }

    public static long getId() {
        return id;
    }

    public static void setId(long id) {
        Order.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return '{' +
                "client=" + client +
                ", products=" + products +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
