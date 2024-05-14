package org.ordersmgmtapp.dao;

import org.ordersmgmtapp.model.Client;

import java.sql.SQLException;
import java.util.List;

/**
 * The ClientDAORepo interface defines methods for interacting with client data in the database.
 */
public interface ClientDAORepo {

    /**
     * Adds a new client to the database.
     *
     * @param client The client object to add.
     * @throws SQLException If an SQL exception occurs.
     */
    void add(Client client) throws SQLException;

    /**
     * Deletes a client from the database based on the client's ID.
     *
     * @param clientId The ID of the client to delete.
     */
    void delete(long clientId);

    /**
     * Updates the information of a client in the database.
     *
     * @param client The updated client object.
     * @throws SQLException If an SQL exception occurs.
     */
    void update(Client client) throws SQLException;

    /**
     * Retrieves a list of all clients from the database.
     *
     * @return A list of all clients.
     * @throws SQLException If an SQL exception occurs.
     */
    List<Client> getAll() throws SQLException;

    /**
     * Retrieves a client from the database based on the client's ID.
     *
     * @param id The ID of the client to retrieve.
     * @return The client object.
     * @throws SQLException If an SQL exception occurs.
     */
    Client getClientById(long id) throws SQLException;
}
