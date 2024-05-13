package org.ordersmgmtapp.dao;

import org.ordersmgmtapp.model.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDAORepo{
    void add(Client client) throws SQLException;
    void delete(long ClientId);
    void update(Client client) throws SQLException;

    List<Client> getAll() throws SQLException;
    Client getClientById(long id) throws SQLException;
}
