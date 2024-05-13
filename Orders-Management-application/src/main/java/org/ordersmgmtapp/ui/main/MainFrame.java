package org.ordersmgmtapp.ui.main;

import org.ordersmgmtapp.controller.ClientController;
import org.ordersmgmtapp.controller.ProductController;
import org.ordersmgmtapp.dbconnection.DatabaseConnectionManager;
import org.ordersmgmtapp.model.Client;
import org.ordersmgmtapp.model.Product;
import org.ordersmgmtapp.ui.mgmt.ClientManagementFrame;
import org.ordersmgmtapp.ui.mgmt.OrderManagementFrame;
import org.ordersmgmtapp.ui.mgmt.ProductManagementFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class MainFrame extends JFrame {
    private JButton clientsButton, productsButton, createOrderButton;
    private DatabaseConnectionManager dbManager;

    public MainFrame(DatabaseConnectionManager dbManager) {
        this.dbManager = dbManager;
        setTitle("Order Management App");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 3)); // Adjust the layout to accommodate the new button

        clientsButton = new JButton("Clients");
        productsButton = new JButton("Products");
        createOrderButton = new JButton("Create Order"); // Add a new button for creating orders

        add(clientsButton);
        add(productsButton);
        add(createOrderButton); // Add the new button to the frame

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
                showProductManagementFrame();
            }
        });

        createOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createOrder(); // Call the method to create an order when the button is clicked
            }
        });
    }

    private void showClientManagementFrame() throws SQLException {
        ClientManagementFrame clientFrame = new ClientManagementFrame();
        clientFrame.setVisible(true);
        updateClientList(clientFrame);
    }

    private void showProductManagementFrame() {
        ProductManagementFrame productFrame = new ProductManagementFrame();
        productFrame.setVisible(true);
        updateProductList(productFrame);
    }

    private void updateClientList(ClientManagementFrame clientFrame) {
        // Fetch clients from the database and update the client list in ClientManagementFrame
        ClientController controller = new ClientController();
        List<Client> clientsList = controller.getAllClients();
        clientFrame.updateClientList(clientsList);
    }

    private void updateProductList(ProductManagementFrame productFrame) {
        // Fetch clients from the database and update the client list in ClientManagementFrame
        ProductController controller = new ProductController();
        List<Product> productList = controller.getAllproducts();
        productFrame.updateProductList(productList);
    }

    private void createOrder() {
        OrderManagementFrame orderWindow = new OrderManagementFrame();
        orderWindow.setVisible(true); // Show the window for creating orders
    }
}
