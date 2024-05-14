package org.ordersmgmtapp.ui.mgmt;

import org.ordersmgmtapp.controller.OrderController;
import org.ordersmgmtapp.dao.*;
import org.ordersmgmtapp.model.Client;
import org.ordersmgmtapp.model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * The OrderManagementFrame class represents the user interface for creating product orders.
 */
public class OrderManagementFrame extends JFrame {

    private JComboBox<Product> productComboBox;
    private JComboBox<Client> clientComboBox;
    private JTextField quantityField;
    private JButton createOrderButton;
    private JLabel statusLabel;

    /**
     * Constructs a new OrderManagementFrame object.
     */
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
                            createOrder(selectedProduct, selectedClient, quantity);
                            decrementStock(selectedProduct, quantity);
                            statusLabel.setText("Order created successfully.");
                        } catch (SQLException ex) {
                            statusLabel.setText("Failed to create order: " + ex.getMessage());
                        }
                    }
                }
            }
        });

        add(mainPanel);
        setVisible(true);
    }

    /**
     * Loads products from the database and populates the product combo box.
     */
    private void loadProducts() {
        ProductDAO productDAO = new ProductDAO();
        List<Product> productList = productDAO.getAllProducts();
        for (Product product : productList) {
            productComboBox.addItem(product);
        }
    }

    /**
     * Loads clients from the database and populates the client combo box.
     */
    private void loadClients() {
        ClientDAO clientDAO = new ClientDAO();
        List<Client> clientList = clientDAO.getAll();
        for (Client client : clientList) {
            clientComboBox.addItem(client);
        }
    }

    /**
     * Creates a new order in the database.
     * @param product The selected product.
     * @param client The selected client.
     * @param quantity The quantity of the product.
     * @throws SQLException If an SQL exception occurs.
     */
    private void createOrder(Product product, Client client, int quantity) throws SQLException {
        OrderController controller = new OrderController();
        controller.addOrder(client.getId(), product.getId(), quantity);
    }

    /**
     * Decrements the stock quantity of a product in the database.
     * @param product The selected product.
     * @param quantity The quantity to decrement.
     * @throws SQLException If an SQL exception occurs.
     */
    private void decrementStock(Product product, int quantity) throws SQLException {
        ProductDAORepo productDAO = new ProductDAO();
        productDAO.decrementStock(product, quantity);
    }
}
