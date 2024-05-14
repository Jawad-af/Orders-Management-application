package org.ordersmgmtapp.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.ordersmgmtapp.databaseobjects.DatabaseConnectionManager.connection;

/**
 * The OrderDAO class provides methods for interacting with order data in the database.
 */
public class OrderDAO implements OrderDAORepo {

    /**
     * Adds a new order to the database.
     *
     * @param clientId  The ID of the client placing the order.
     * @param productId The ID of the product being ordered.
     * @param quantity  The quantity of the product being ordered.
     */
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
