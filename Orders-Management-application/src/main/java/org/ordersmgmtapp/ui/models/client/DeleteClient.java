package org.ordersmgmtapp.ui.models.client;

import org.ordersmgmtapp.controller.ClientController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The DeleteClient class represents the UI for deleting a client.
 */
public class DeleteClient extends JFrame {
    private JTextField id;
    private JButton delete;

    /**
     * Constructs a new DeleteClient object.
     */
    public DeleteClient() {
        setTitle("Delete Client");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        id = new JTextField(10);
        delete = new JButton("Delete Client");

        mainPanel.add(new JLabel("Client ID: "));
        mainPanel.add(id);
        mainPanel.add(delete);

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long clientId = Long.parseLong(id.getText());
                ClientController controller = new ClientController();
                controller.deleteClient(clientId);
                JOptionPane.showMessageDialog(DeleteClient.this, "Client deleted successfully!");
            }
        });

        setContentPane(mainPanel);
        setVisible(true);
    }
}
