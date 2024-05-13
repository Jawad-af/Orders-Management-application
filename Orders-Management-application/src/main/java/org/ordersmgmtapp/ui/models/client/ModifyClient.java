package org.ordersmgmtapp.ui.models.client;

import org.ordersmgmtapp.dao.ClientDAO;
import org.ordersmgmtapp.model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyClient extends JFrame {

    private JTextField id;
    private JButton modify;

    public ModifyClient() {
        setTitle("Modify Client");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        id = new JTextField(10);
        modify = new JButton("Modify Client");

        mainPanel.add(new JLabel("Client ID: "));
        mainPanel.add(id);
        mainPanel.add(modify);

        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long clientId = Long.parseLong(id.getText());
                ClientDAO clientDAO = new ClientDAO();
                Client client;
                client = clientDAO.getClientById(clientId);

                JTextField nameField, ageField;
                JButton saveButton;

                JPanel modifyPanel = new JPanel();
                modifyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

                nameField = new JTextField(client.getName(), 10);
                ageField = new JTextField(String.valueOf(client.getAge()), 10);
                saveButton = new JButton("Save Client");

                modifyPanel.add(new JLabel("Name: "));
                modifyPanel.add(nameField);
                modifyPanel.add(new JLabel("Age: "));
                modifyPanel.add(ageField);
                modifyPanel.add(saveButton);

                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Get updated name and age
                        String updatedName = nameField.getText();
                        int updatedAge = Integer.parseInt(ageField.getText());

                        // Update the client in the database
                        client.setName(updatedName);
                        client.setAge(updatedAge);
                        clientDAO.update(client);
                        JOptionPane.showMessageDialog(ModifyClient.this, "Client updated successfully!");
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ModifyClient());
    }
}
