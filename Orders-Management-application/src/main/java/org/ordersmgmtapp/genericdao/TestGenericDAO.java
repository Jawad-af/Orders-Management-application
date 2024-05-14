package org.ordersmgmtapp.genericdao;

import org.ordersmgmtapp.model.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.ordersmgmtapp.constants.DatabaseConstants.*;

/**
 * A test class to demonstrate the usage of GenericDAO with a Client entity.
 */
public class TestGenericDAO {

    /**
     * The main method to execute the test.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            GenericDAO<Client> clientDAO = new GenericDAO<>(connection);

            // Create a new client
            Client newClient = new Client("Jawad", 50);
            clientDAO.create(newClient, "client", "id");

            // Find the created client by ID
            Client foundClient = clientDAO.find(1, "client", "id", Client.class);
            System.out.println("Found client: " + foundClient);

            // Edit the name of the found client
            foundClient.setName("Jawad");
            clientDAO.edit(foundClient, "client", "id");

            // Delete a client by ID
            clientDAO.delete(13, "client", "id");
        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
