package org.ordersmgmtapp.services;

import org.ordersmgmtapp.dao.ClientDAO;
import org.ordersmgmtapp.model.Client;

import java.sql.SQLException;
import java.util.List;

public class ClientService {

    private ClientDAO clientDAO;

    public ClientService() {
        this.clientDAO = new ClientDAO();
    }

    public void add(Client client) throws SQLException {
        clientDAO.add(client);
    }

    public List<Client> getAll() throws SQLException {
        return clientDAO.getAll();
    }
}
