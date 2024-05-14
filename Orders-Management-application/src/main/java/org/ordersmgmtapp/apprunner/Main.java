package org.ordersmgmtapp.apprunner;

import org.ordersmgmtapp.databaseobjects.DatabaseConnectionManager;
import org.ordersmgmtapp.ui.main.MainFrame;

import javax.swing.*;

/**
 * The Main class represents the entry point of the application.
 */
public class Main {

    public static void main(String[] args) {
        // Establishing a connection with the database
        DatabaseConnectionManager databaseConnectionManager = new DatabaseConnectionManager();
        databaseConnectionManager.connect();


        // Launching the main frame of the application
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(databaseConnectionManager);
            frame.setVisible(true);
        });
    }
}
