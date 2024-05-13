package org.ordersmgmtapp.controller;

import org.ordersmgmtapp.dao.OrderDAO;
import org.ordersmgmtapp.dao.OrderDAORepo;

public class OrderController {

    private OrderDAORepo orderDAORepo = new OrderDAO();
    public void addOrder(long clientId, long productId, int stock) {
        orderDAORepo.addOrder(clientId, productId, stock);
    }
}
