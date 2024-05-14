package org.ordersmgmtapp.ui.mgmt;

import org.ordersmgmtapp.model.Product;
import org.ordersmgmtapp.ui.models.product.AddProduct;
import org.ordersmgmtapp.ui.models.product.DeleteProduct;
import org.ordersmgmtapp.ui.models.product.ModifyProduct;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.List;

/**
 * The ProductManagementFrame class represents the user interface for managing products.
 */
public class ProductManagementFrame extends JFrame {
    private JTable productTable;
    private JButton addProductButton, modifyProductButton, deleteProductButton;

    /**
     * Constructs a new ProductManagementFrame object.
     */
    public ProductManagementFrame() {
        setTitle("Product Management");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addProductButton = new JButton("Add Product");
        modifyProductButton = new JButton("Modify Product");
        deleteProductButton = new JButton("Delete Product");
        buttons.add(addProductButton);
        buttons.add(modifyProductButton);
        buttons.add(deleteProductButton);

        JPanel productsPanel = new JPanel(new BorderLayout());
        productTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(productTable);
        productsPanel.add(scrollPane, BorderLayout.CENTER);

        add(buttons, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        addProductButton.addActionListener(e -> {
            AddProduct productInfo = new AddProduct();
            productInfo.setVisible(true);
        });

        modifyProductButton.addActionListener(e -> {
            ModifyProduct modifyProduct = new ModifyProduct();
            modifyProduct.setVisible(true);
        });

        deleteProductButton.addActionListener(e -> {
            DeleteProduct deleteProduct = new DeleteProduct();
            deleteProduct.setVisible(true);
        });
    }

    /**
     * Updates the product list in the UI.
     * @param products The list of products to display.
     */
    public void updateProductList(List<Product> products) {
        Class<Product> productClass = Product.class;
        Field[] fields = productClass.getDeclaredFields();
        String[] columnNames = new String[fields.length];
        Object[][] data = new Object[products.size()][fields.length];
        for (int i = 0; i < fields.length; i++) {
            columnNames[i] = fields[i].getName();
        }
        try {
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                for (int j = 0; j < fields.length; j++) {
                    fields[j].setAccessible(true);
                    data[i][j] = fields[j].get(product);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        productTable.setModel(model);
    }
}
