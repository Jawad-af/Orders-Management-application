package org.ordersmgmtapp.ui;

import javax.swing.*;
import java.awt.*;

public class ProductManagementFrame extends JFrame {
    private JList<String> productList;

    public ProductManagementFrame() {
        setTitle("Product Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        productList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(productList);

        add(scrollPane, BorderLayout.CENTER);
    }

    // Method to update product list
    public void updateProductList(String[] products) {
        productList.setListData(products);
    }
}

