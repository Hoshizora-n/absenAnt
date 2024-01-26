package com.mycompany.absen.data;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Koneksi {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3308/absen_new?allowPublicKeyRetrieval=true";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    // Creating a BasicDataSource for connection pooling
    private static final BasicDataSource dataSource = new BasicDataSource();

    // Static block to initialize the data source
    static {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database connection details
            dataSource.setUrl(JDBC_URL);
            dataSource.setUsername(USERNAME);
            dataSource.setPassword(PASSWORD);

            // Connection pooling settings
            dataSource.setMinIdle(5);
            dataSource.setMaxIdle(10);
            dataSource.setMaxOpenPreparedStatements(100);
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("Failed to load JDBC driver");
        }
    }

    // Private constructor to prevent external instantiation
    private Koneksi() {
    }

    // Singleton instance holder
    private static class SingletonHolder {
        private static final Koneksi INSTANCE = new Koneksi();
    }

    // Public method to get the singleton instance
    public static Koneksi getInstance() {
        return SingletonHolder.INSTANCE;
    }

    // Method to get a connection from the pool
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // Optional: Add methods for database operations if needed
}
