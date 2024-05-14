package org.ordersmgmtapp.dao;

import org.ordersmgmtapp.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.ordersmgmtapp.dbconnection.DatabaseConnectionManager.connection;

public class ProductDAO implements ProductDAORepo{

    @Override
    public void addProduct(String name, String description, double price, int stock) {
        String ADD_NEW_PRODUCT = "INSERT INTO product(name, description, price, stock_quantity) VALUES(?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(ADD_NEW_PRODUCT);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setDouble(3, price);
            preparedStatement.setInt(4, stock);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Product " + name + " has been added successfully.");
            } else {
                System.out.println("Failed to add the client " + name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifyProduct(Product product) {
        String MODIFY_PRODUCT = "UPDATE product SET name = ?, description = ?, price = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(MODIFY_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setLong(4, product.getId());
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Product " + product.getId() + " updated successfully.");
            } else {
                System.out.println("Failed to update the product " + product.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProduct(long id) {
        String DELETE_PRODUCT = "DELETE FROM product WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT);
            preparedStatement.setLong(1, id);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("Product " + id + " has been deleted successfully");
            } else {
                System.out.println("Unable to delete product " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getProductById(long productId) {
        String RETRIEVE_CLIENT_BY_ID = "SELECT * FROM product WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(RETRIEVE_CLIENT_BY_ID);
            preparedStatement.setLong(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int stock = resultSet.getInt("stock_quantity");
                double price = resultSet.getDouble("price");
                Product product = new Product(id, name, description, price, stock);
                return product;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String RETRIEVE_ALL_PRODUCTS = "SELECT * FROM product";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(RETRIEVE_ALL_PRODUCTS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int stock = resultSet.getInt("stock_quantity");
                Product product = new Product(id, name, description, price, stock);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public void decrementStock(Product product, int quantity) {
        String DECREMENT_STOCK_QUANTITY = "UPDATE product SET stock_quantity = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DECREMENT_STOCK_QUANTITY);
            int decrementingValue = product.getStockQuantity() - quantity;
            preparedStatement.setInt(1, decrementingValue);
            preparedStatement.setLong(2, product.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("The quantity of the product " + product.getId() + " has been decremented to " + decrementingValue);
            } else {
                System.out.println("Failed to decrement the quantity");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
