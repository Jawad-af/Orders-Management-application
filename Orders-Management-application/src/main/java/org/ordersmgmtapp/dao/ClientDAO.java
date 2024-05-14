package org.ordersmgmtapp.dao;

import org.ordersmgmtapp.model.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.ordersmgmtapp.databaseobjects.DatabaseConnectionManager.connection;

/**
 * The ClientDAO class provides methods to interact with the database for client-related operations.
 */
public class ClientDAO implements ClientDAORepo {

    /**
     * Adds a new client to the database.
     *
     * @param client The client object to add.
     */
    @Override
    public void add(Client client) {
        String INSERT_CLIENT_SQL = "INSERT INTO client(name, age) VALUES (?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_SQL);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setInt(2, client.getAge());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Client added successfully.");
            } else {
                System.out.println("Failed to add the client " + client.getName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes a client from the database based on the client's ID.
     *
     * @param clientId The ID of the client to delete.
     */
    @Override
    public void delete(long clientId) {
        String DELETE_CLIENT = "DELETE FROM client WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT);
            preparedStatement.setLong(1, clientId);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("Client " + clientId + " has been deleted successfully");
            } else {
                System.out.println("Unable to delete client " + clientId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates the information of a client in the database.
     *
     * @param client The updated client object.
     */
    @Override
    public void update(Client client) {
        String UPDATE_CLIENT = "UPDATE client SET name = ?, age = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setInt(2, client.getAge());
            preparedStatement.setLong(3, client.getId());
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Client updated successfully.");
            } else {
                System.out.println("Failed to update the client " + client.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves a list of all clients from the database.
     *
     * @return A list of all clients.
     */
    @Override
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        String RETRIEVE_ALL_CLIENTS = "SELECT * FROM client;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(RETRIEVE_ALL_CLIENTS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                Client client = new Client(id, name, age);
                clients.add(client);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clients;
    }

    /**
     * Retrieves a client from the database based on the client's ID.
     *
     * @param id The ID of the client to retrieve.
     * @return The client object.
     */
    @Override
    public Client getClientById(long id) {
        Client client = new Client();
        String RETRIEVE_CLIENT_BY_ID = "SELECT * FROM CLIENT WHERE CLIENT.ID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(RETRIEVE_CLIENT_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                long clientId = resultSet.getLong("id");
                String clientName = resultSet.getString("name");
                int clientAge = resultSet.getInt("age");
                client.setId(clientId);
                client.setName(clientName);
                client.setAge(clientAge);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return client;
    }
}
