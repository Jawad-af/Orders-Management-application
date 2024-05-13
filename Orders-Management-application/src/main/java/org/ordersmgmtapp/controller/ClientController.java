package org.ordersmgmtapp.controller;

import org.ordersmgmtapp.model.Client;
import org.ordersmgmtapp.services.ClientService;

import java.sql.SQLException;
import java.util.List;

public class ClientController {

    private ClientService clientService = new ClientService();

    public void addClient(String name, int age) throws SQLException {
        Client client = new Client();
        if (client != null) {
            client.setName(name);
            client.setAge(age);
            clientService.add(client);
        }
    }

    public List<Client> getAllClients() throws SQLException {
        return clientService.getAll();
    }
}
