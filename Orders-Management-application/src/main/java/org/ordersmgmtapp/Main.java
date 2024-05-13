package org.ordersmgmtapp;

import org.ordersmgmtapp.dbconnection.DatabaseConnectionManager;
import org.ordersmgmtapp.ui.main.MainFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // establishing a connection with the database
        DatabaseConnectionManager databaseConnectionManager = new DatabaseConnectionManager();
        databaseConnectionManager.connect();

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(databaseConnectionManager);
            frame.setVisible(true);
        });
    }
}