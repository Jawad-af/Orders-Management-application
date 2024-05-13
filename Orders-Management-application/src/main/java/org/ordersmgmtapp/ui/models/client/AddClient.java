package org.ordersmgmtapp.ui.models.client;

import org.ordersmgmtapp.controller.ClientController;

import javax.swing.*;
import java.sql.SQLException;

public class AddClient extends JFrame {

    private ClientController controller = new ClientController();
    private JTextField name, age;
    private JButton addButton;

    public AddClient() {
        setTitle("Add New Client");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setSize(200, 200);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        name = new JTextField();
        name.setSize(50, 10);
        age = new JTextField();
        age.setSize(50, 10);
        addButton = new JButton("Add Client");

        panel.add(new JLabel("Name: "));
        panel.add(name);
        panel.add(new JLabel("Age: "));
        panel.add(age);
        panel.add(addButton);

        addButton.addActionListener(e -> {
            try {
                addClient();
                JOptionPane.showMessageDialog(AddClient.this, "Client added successfully!");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        add(panel);
    }
    private void addClient() throws SQLException {
        String clientName = name.getText();
        int clientAge = Integer.parseInt(age.getText());
        controller.addClient(clientName, clientAge);
    }
}
