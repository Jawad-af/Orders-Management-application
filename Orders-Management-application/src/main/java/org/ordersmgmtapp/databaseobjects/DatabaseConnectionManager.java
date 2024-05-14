package org.ordersmgmtapp.databaseobjects;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.ordersmgmtapp.constants.DatabaseConstants.*;

/**
 * The DatabaseConnectionManager class manages the connection to the database.
 */
public class DatabaseConnectionManager {
    /**
     * The connection to the database.
     */
    public static Connection connection;

    /**
     * Establishes a connection to the database using JDBC.
     */
    public void connect() {
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            if (connection == null) {
                System.out.println("Unable to establish a connection with the database.");
            }
        } catch (Exception e) {
            System.out.println("Error connecting to the database.");
        }
    }
}
