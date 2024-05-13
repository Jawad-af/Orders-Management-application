package org.ordersmgmtapp.dao;

public interface OrderDAORepo {

    void addOrder(long clientId, long productId, int quantity);
}
