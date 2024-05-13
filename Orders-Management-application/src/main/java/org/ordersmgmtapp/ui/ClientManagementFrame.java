package org.ordersmgmtapp.ui;
import org.ordersmgmtapp.controller.ClientController;
import org.ordersmgmtapp.model.Client;
import org.ordersmgmtapp.ui.client.ClientInfo;
import org.ordersmgmtapp.ui.client.DeleteClient;
import org.ordersmgmtapp.ui.client.ModifyClient;

import java.util.List;

import javax.swing.*;
        import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientManagementFrame extends JFrame {
    private JList<String> clientList;
    private JButton addClientButton, modifyClientButton, deleteClientButton;
    public ClientManagementFrame() {
        setTitle("Client Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addClientButton = new JButton("Add Client");
        modifyClientButton = new JButton("Modify Client");
        deleteClientButton = new JButton("Delete Client");
        buttons.add(addClientButton);
        buttons.add(modifyClientButton);
        buttons.add(deleteClientButton);

        JPanel clientsPanel = new JPanel(new BorderLayout());
        clientList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(clientList);
        clientsPanel.add(scrollPane, BorderLayout.CENTER);

        add(buttons, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientInfo clientInfo = new ClientInfo();
                clientInfo.setVisible(true);
            }
        });

        modifyClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModifyClient modifyClient = new ModifyClient();
                modifyClient.setVisible(true);
            }
        });

        deleteClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteClient deleteClient = new DeleteClient();
                deleteClient.setVisible(true);
            }
        });
    }

    // Method to update client list
    public void updateClientList(List<Client> clients) {
        String[] currentClients = new String[clients.size()];
        int i = 0;
        for (Client client : clients) {
            currentClients[i] = client.toString();
            i++;
        }
        clientList.setListData(currentClients);
    }
}
