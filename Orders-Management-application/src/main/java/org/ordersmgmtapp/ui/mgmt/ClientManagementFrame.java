package org.ordersmgmtapp.ui.mgmt;

import org.ordersmgmtapp.model.Client;
import org.ordersmgmtapp.ui.models.client.AddClient;
import org.ordersmgmtapp.ui.models.client.DeleteClient;
import org.ordersmgmtapp.ui.models.client.ModifyClient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.List;

public class ClientManagementFrame extends JFrame {
    private JTable clientTable;
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

        clientTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(clientTable);

        add(buttons, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddClient clientInfo = new AddClient();
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

    public void updateClientList(List<Client> clients) {
        Class<Client> clientClass = Client.class;
        Field[] clientFields = clientClass.getDeclaredFields();
        try {
            Field clientIdField = clientClass.getDeclaredField("id");
            Field clientNameField = clientClass.getDeclaredField("name");
            Field clientAgeField = clientClass.getDeclaredField("age");
            clientIdField.setAccessible(true);
            clientNameField.setAccessible(true);
            clientAgeField.setAccessible(true);
            String[] columnNames = {clientIdField.getName(), clientNameField.getName(), clientAgeField.getName()};
            Object[][] data = new Object[clients.size()][columnNames.length];
            for (int i = 0; i < clients.size(); i++) {
                Client client = clients.get(i);
                data[i][0] = clientIdField.get(client);
                data[i][1] = clientNameField.get(client);
                data[i][2] = clientAgeField.get(client);
            }
            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            clientTable.setModel(model);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
