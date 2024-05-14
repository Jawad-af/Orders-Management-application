package org.ordersmgmtapp.ui.models.product;

import org.ordersmgmtapp.controller.ProductController;
import org.ordersmgmtapp.model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ModifyProduct class represents the UI for modifying a product.
 */
public class ModifyProduct extends JFrame {

    private JTextField id;
    private JButton modify;

    /**
     * Constructs a new ModifyProduct object.
     */
    public ModifyProduct() {
        setTitle("Modify Product");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        id = new JTextField(10);
        modify = new JButton("Modify Product");

        mainPanel.add(new JLabel("Product ID: "));
        mainPanel.add(id);
        mainPanel.add(modify);

        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long productId = Long.parseLong(id.getText());
                ProductController controller = new ProductController();
                Product product = controller.getProductById(productId);

                JTextField nameField, descriptionField, priceField;
                JButton saveButton;

                JPanel modifyPanel = new JPanel();
                modifyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

                nameField = new JTextField(product.getName(), 10);
                descriptionField = new JTextField(product.getDescription(), 10);
                priceField = new JTextField(String.valueOf(product.getPrice()), 10);
                saveButton = new JButton("Save Product");

                modifyPanel.add(new JLabel("Name: "));
                modifyPanel.add(nameField);
                modifyPanel.add(new JLabel("Description: "));
                modifyPanel.add(descriptionField);
                modifyPanel.add(new JLabel("Price: "));
                modifyPanel.add(priceField);
                modifyPanel.add(saveButton);

                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Get updated name, description, and price
                        String updatedName = nameField.getText();
                        String updatedDescription = descriptionField.getText();
                        double updatedPrice = Double.parseDouble(priceField.getText());
                        // Update the product
                        product.setName(updatedName);
                        product.setDescription(updatedDescription);
                        product.setPrice(updatedPrice);
                        controller.updateProduct(product);
                        JOptionPane.showMessageDialog(ModifyProduct.this, "Product updated successfully!");
                    }
                });

                setContentPane(modifyPanel);
                revalidate();
                repaint();
            }
        });

        setContentPane(mainPanel);
        setVisible(true);
    }
}
