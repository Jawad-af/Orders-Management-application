package org.ordersmgmtapp.ui;

import org.ordersmgmtapp.controller.ClientController;
import org.ordersmgmtapp.dbconnection.DatabaseConnectionManager;
import org.ordersmgmtapp.model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private JButton clientsButton, productsButton;
    private DatabaseConnectionManager dbManager;

    public MainFrame(DatabaseConnectionManager dbManager) {
        this.dbManager = dbManager;
        setTitle("Order Management App");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        clientsButton = new JButton("Clients");
        productsButton = new JButton("Products");

        add(clientsButton);
        add(productsButton);

        clientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showClientManagementFrame();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        productsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductManagementFrame productFrame = new ProductManagementFrame();
                productFrame.setVisible(true);
            }
        });
    }

    private void showClientManagementFrame() throws SQLException {
        ClientManagementFrame clientFrame = new ClientManagementFrame();
        clientFrame.setVisible(true);
        updateClientList(clientFrame);
    }

    private void updateClientList(ClientManagementFrame clientFrame) throws SQLException {
        // Fetch clients from the database and update the client list in ClientManagementFrame
        ClientController controller = new ClientController();
        List<Client> clientsList = controller.getAllClients();
        clientFrame.updateClientList(clientsList);
    }
}
