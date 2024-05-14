package org.ordersmgmtapp.controller;

import org.ordersmgmtapp.dao.OrderDAO;
import org.ordersmgmtapp.dao.OrderDAORepo;

/**
 * The OrderController class manages operations related to orders.
 */
public class OrderController {

    private OrderDAORepo orderDAORepo = new OrderDAO();

    /**
     * Adds a new order.
     *
     * @param clientId  The ID of the client placing the order.
     * @param productId The ID of the product being ordered.
     * @param stock     The quantity of the product being ordered.
     */
    public void addOrder(long clientId, long productId, int stock) {
        orderDAORepo.addOrder(clientId, productId, stock);
    }
}
