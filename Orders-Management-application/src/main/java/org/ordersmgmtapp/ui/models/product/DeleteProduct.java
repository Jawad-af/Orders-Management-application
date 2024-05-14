package org.ordersmgmtapp.ui.models.product;

import org.ordersmgmtapp.controller.ProductController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteProduct extends JFrame {

    private JTextField id;
    private JButton delete;

    public DeleteProduct() {
        setTitle("Delete Product");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        id = new JTextField(10);
        delete = new JButton("Delete Product");

        mainPanel.add(new JLabel("Product ID: "));
        mainPanel.add(id);
        mainPanel.add(delete);

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long productId = Long.parseLong(id.getText());
                ProductController controller = new ProductController();
                controller.deleteProduct(productId);
                JOptionPane.showMessageDialog(DeleteProduct.this, "Product deleted successfully!");
            }
        });

        setContentPane(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DeleteProduct());
    }
}
