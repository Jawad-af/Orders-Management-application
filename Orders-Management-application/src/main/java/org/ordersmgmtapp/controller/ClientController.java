package org.ordersmgmtapp.controller;

import org.ordersmgmtapp.dao.ClientDAO;
import org.ordersmgmtapp.model.Client;

import java.sql.SQLException;
import java.util.List;

/**
 * The ClientController class manages operations related to clients.
 */
public class ClientController {

    private ClientDAO clientDAO = new ClientDAO();

    /**
     * Adds a new client.
     *
     * @param name The name of the client.
     * @param age  The age of the client.
     * @throws SQLException if an SQL exception occurs.
     */
    public void addClient(String name, int age) throws SQLException {
        Client client = new Client();
        if (client != null) {
            client.setName(name);
            client.setAge(age);
            clientDAO.add(client);
        }
    }

    /**
     * Retrieves a list of all clients.
     *
     * @return A list of all clients.
     */
    public List<Client> getAllClients() {
        return clientDAO.getAll();
    }

    /**
     * Deletes a client.
     *
     * @param id The ID of the client to delete.
     */
    public void deleteClient(long id) {
        clientDAO.delete(id);
    }

    /**
     * Updates client information.
     *
     * @param client The client object containing updated information.
     */
    public void updateClient(Client client) {
        clientDAO.update(client);
    }

    /**
     * Fetches a client from the database.
     *
     * @param id which represents the ID of the client to fetch from the database.
     * @return the client with the specific id
     */
    public Client getClientById(long id){
        return clientDAO.getClientById(id);
    }
}
