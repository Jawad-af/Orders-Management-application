package org.ordersmgmtapp.ui.mgmt;

import org.ordersmgmtapp.controller.OrderController;
import org.ordersmgmtapp.dao.*;
import org.ordersmgmtapp.model.Client;
import org.ordersmgmtapp.model.Product;
import org.ordersmgmtapp.ui.models.product.AddProduct;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class OrderManagementFrame extends JFrame {

    private JComboBox<Product> productComboBox;
    private JComboBox<Client> clientComboBox;
    private JTextField quantityField;
    private JButton createOrderButton;
    private JLabel statusLabel;

    public OrderManagementFrame() {
        setTitle("Create Product Order");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2));

        productComboBox = new JComboBox<>();
        clientComboBox = new JComboBox<>();
        quantityField = new JTextField();
        createOrderButton = new JButton("Create Order");
        statusLabel = new JLabel();

        // Retrieve existing products and clients from the database and populate the combo boxes
        loadProducts();
        loadClients();

        mainPanel.add(new JLabel("Select Product:"));
        mainPanel.add(productComboBox);
        mainPanel.add(new JLabel("Select Client:"));
        mainPanel.add(clientComboBox);
        mainPanel.add(new JLabel("Quantity:"));
        mainPanel.add(quantityField);
        mainPanel.add(createOrderButton);
        mainPanel.add(statusLabel);

        createOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product selectedProduct = (Product) productComboBox.getSelectedItem();
                Client selectedClient = (Client) clientComboBox.getSelectedItem();
                int quantity = Integer.MAX_VALUE;
                if (quantityField.getText().equals("")) {
                    JOptionPane.showMessageDialog(OrderManagementFrame.this, "Please specify the quantity!");
                } else {
                    quantity = Integer.parseInt(quantityField.getText());
                    if (selectedProduct.getStockQuantity() < quantity) {
                        statusLabel.setText("Under-stock: Not enough products available.");
                    } else {
                        try {
                            // Insert order into database
                            createOrder(selectedProduct, selectedClient, quantity);
                            // Decrement product stock
                            decrementStock(selectedProduct, quantity);
                            statusLabel.setText("Order created successfully.");
                    // Check if there are enough products in stock
                        } catch (SQLException ex) {
                            statusLabel.setText("Failed to create order: " + ex.getMessage());
                        }
                        // Insert order into the database and decrement product stock
                    }
                }
            }
        });

        add(mainPanel);
        setVisible(true);
    }

    private void loadProducts() {
        // Retrieve existing products from the database and populate the product combo box
        ProductDAO productDAO = new ProductDAO();
        List<Product> productList = productDAO.getAllProducts();
        for (Product product : productList) {
            productComboBox.addItem(product);
        }
    }

    private void loadClients() {
        // Retrieve existing clients from the database and populate the client combo box
        ClientDAO clientDAO = new ClientDAO();
        List<Client> clientList = clientDAO.getAll();
        for (Client client : clientList) {
            clientComboBox.addItem(client);
        }
    }

    private void createOrder(Product product, Client client, int quantity) throws SQLException {
        // Insert order into the database
        OrderController controller = new OrderController();
        controller.addOrder(client.getId(), product.getId(), quantity);
    }

    private void decrementStock(Product product, int quantity) throws SQLException {
        // Decrement product stock in the database
        // We should update the stock quantity for the selected product in the product table
        ProductDAORepo productDAO = new ProductDAO();
        productDAO.decrementStock(product, quantity);
    }
}
