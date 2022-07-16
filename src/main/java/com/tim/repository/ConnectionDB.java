package com.tim.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static volatile ConnectionDB instance;
    private static Connection connection = null;
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/datadevelopers";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    private ConnectionDB() {}

    public static Connection getInstance() {
        if (instance == null) {
            synchronized (ConnectionDB.class) {
                if (instance == null) {
                    instance = new ConnectionDB();
                    installConnection();
                }
            }
        }
        return connection;
    }

    private static Connection installConnection() {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
