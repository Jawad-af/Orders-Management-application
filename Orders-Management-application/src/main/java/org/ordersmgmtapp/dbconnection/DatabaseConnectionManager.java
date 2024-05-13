package org.ordersmgmtapp.dbconnection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.ordersmgmtapp.constants.DatabaseConstants.*;

public class DatabaseConnectionManager {
    public static Connection connection;
    public void connect() {
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            if (connection == null) {
                System.out.println("Unable establishing the connection with the database.");
            }
        } catch (Exception e) {
            System.out.println("Error connecting with the database.");
        }
    }
}
