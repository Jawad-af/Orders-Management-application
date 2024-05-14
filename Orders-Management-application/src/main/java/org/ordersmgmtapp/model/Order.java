package org.ordersmgmtapp.model;

import java.time.LocalDateTime;

/**
 * The Order class represents an order entity in the system.
 */
public class Order {
    private static long orderId = 0;
    private long id;
    private long clientId;
    private String productId;
    private int quantity;
    private LocalDateTime orderDate;

    /**
     * Constructs a new Order object with the specified client ID, product ID, and quantity.
     * @param clientId The ID of the client placing the order.
     * @param productId The ID of the product being ordered.
     * @param quantity The quantity of the product being ordered.
     */
    public Order(long clientId, String productId, int quantity) {
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
        this.orderDate = LocalDateTime.now(); // Set the order date to the current date and time
    }

    /**
     * Retrieves the ID of the order.
     * @return The ID of the order.
     */
    public long getId() {
        return id;
    }

    /**
     * Retrieves the ID of the client associated with the order.
     * @return The ID of the client associated with the order.
     */
    public long getClientId() {
        return clientId;
    }

    /**
     * Sets the ID of the client associated with the order.
     * @param clientId The ID of the client to set.
     */
    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    /**
     * Retrieves the ID of the product associated with the order.
     * @return The ID of the product associated with the order.
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Sets the ID of the product associated with the order.
     * @param productId The ID of the product to set.
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * Retrieves the quantity of the product in the order.
     * @return The quantity of the product in the order.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product in the order.
     * @param quantity The quantity to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Retrieves the date and time the order was placed.
     * @return The date and time the order was placed.
     */
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * Returns a string representation of the order.
     * @return A string representation of the order.
     */
    @Override
    public String toString() {
        return "id:  " + id +
                "  |  clientId=" + clientId +
                "  |  productId=" + productId +
                "  |  quantity=" + quantity +
                "  |  Date=" + orderDate;
    }
}
