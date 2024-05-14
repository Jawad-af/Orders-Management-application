package org.ordersmgmtapp.dao;

/**
 * The OrderDAORepo interface defines the contract for interacting with order data in the database.
 */
public interface OrderDAORepo {

    /**
     * Adds a new order to the database.
     *
     * @param clientId  The ID of the client placing the order.
     * @param productId The ID of the product being ordered.
     * @param quantity  The quantity of the product being ordered.
     */
    void addOrder(long clientId, long productId, int quantity);
}
