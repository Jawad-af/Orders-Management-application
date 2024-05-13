package org.ordersmgmtapp.controller;

import org.ordersmgmtapp.dao.ClientDAO;
import org.ordersmgmtapp.model.Client;

import java.sql.SQLException;
import java.util.List;

public class ClientController {

    private ClientDAO clientDAO = new ClientDAO();

    public void addClient(String name, int age) throws SQLException {
        Client client = new Client();
        if (client != null) {
            client.setName(name);
            client.setAge(age);
            clientDAO.add(client);
        }
    }

    public List<Client> getAllClients() {
        return clientDAO.getAll();
    }

    public void deleteClient(long id) {
        clientDAO.delete(id);
    }

    public void updateClient(Client client) {
        clientDAO.update(client);
    }
}
