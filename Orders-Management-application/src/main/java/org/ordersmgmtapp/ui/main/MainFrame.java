package org.ordersmgmtapp.ui.main;

import org.ordersmgmtapp.controller.ClientController;
import org.ordersmgmtapp.controller.ProductController;
import org.ordersmgmtapp.databaseobjects.DatabaseConnectionManager;
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

/**
 * The MainFrame class represents the main frame of the application, which allows access to different management functionalities.
 */
public class MainFrame extends JFrame {
    private JButton clientsButton, productsButton, createOrderButton;
    private DatabaseConnectionManager dbManager;

    /**
     * Constructs a new MainFrame object with the specified database manager.
     * @param dbManager The database connection manager.
     */
    public MainFrame(DatabaseConnectionManager dbManager) {
        this.dbManager = dbManager;
        setTitle("Order Management App");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 3));

        clientsButton = new JButton("Clients");
        productsButton = new JButton("Products");
        createOrderButton = new JButton("Create Order");

        add(clientsButton);
        add(productsButton);
        add(createOrderButton);

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
                createOrder();
            }
        });
    }

    /**
     * Displays the client management frame.
     * @throws SQLException if a SQL exception occurs.
     */
    private void showClientManagementFrame() throws SQLException {
        ClientManagementFrame clientFrame = new ClientManagementFrame();
        clientFrame.setVisible(true);
        updateClientList(clientFrame);
    }

    /**
     * Displays the product management frame.
     */
    private void showProductManagementFrame() {
        ProductManagementFrame productFrame = new ProductManagementFrame();
        productFrame.setVisible(true);
        updateProductList(productFrame);
    }

    /**
     * Updates the client list in the client management frame.
     * @param clientFrame The client management frame.
     */
    private void updateClientList(ClientManagementFrame clientFrame) {
        ClientController controller = new ClientController();
        List<Client> clientsList = controller.getAllClients();
        clientFrame.updateClientList(clientsList);
    }

    /**
     * Updates the product list in the product management frame.
     * @param productFrame The product management frame.
     */
    private void updateProductList(ProductManagementFrame productFrame) {
        ProductController controller = new ProductController();
        List<Product> productList = controller.getAllProducts();
        productFrame.updateProductList(productList);
    }

    /**
     * Creates a new order.
     */
    private void createOrder() {
        OrderManagementFrame orderWindow = new OrderManagementFrame();
        orderWindow.setVisible(true);
    }
}
