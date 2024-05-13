package org.ordersmgmtapp.ui.models.product;

import org.ordersmgmtapp.controller.ProductController;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AddProduct extends JFrame {

    private ProductController controller = new ProductController();
    private JTextField name, description, price, stock;
    private JButton addButton;

    public AddProduct() {
        setTitle("Add New product");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setSize(200, 200);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        name = new JTextField(10);
        description = new JTextField(10);
        price = new JTextField(10);
        stock = new JTextField(10);
        addButton = new JButton("Add product");

        panel.add(new JLabel("Name: "));
        panel.add(name);
        panel.add(new JLabel("Description: "));
        panel.add(description);
        panel.add(new JLabel("Price: "));
        panel.add(price);
        panel.add(new JLabel("Stock: "));
        panel.add(stock);
        panel.add(addButton);

        addButton.addActionListener(e -> {
            addproduct();
            JOptionPane.showMessageDialog(AddProduct.this, "product added successfully!");
        });

        add(panel);
    }
    private void addproduct() {
        String productName = name.getText();
        String productDescription = description.getText();
        double productPrice = Double.parseDouble(price.getText());
        int productStock = Integer.parseInt(stock.getText());
        controller.addProduct(productName, productDescription, productPrice, productStock);
    }

}
