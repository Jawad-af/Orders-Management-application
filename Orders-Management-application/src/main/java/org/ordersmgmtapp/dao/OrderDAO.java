package org.ordersmgmtapp.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.ordersmgmtapp.dbconnection.DatabaseConnectionManager.connection;

public class OrderDAO implements OrderDAORepo {

    @Override
    public void addOrder(long clientId, long productId, int quantity) {
        String ADD_ORDER = "INSERT INTO `order`(client_id, product_id, quantity) VALUES(?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER);
            preparedStatement.setLong(1, clientId);
            preparedStatement.setLong(2, productId);
            preparedStatement.setInt(3, quantity);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("The order of client " + clientId + " has been added successfully.");
            } else {
                System.out.println("Failed adding the order of the client " + clientId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
