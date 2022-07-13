package com.tim.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static volatile ConnectionDB instance;
    private static Connection connection = null;
    private static final String DATABASE_URL = "jdbc:sqlite:identifier.sqlite";
    private static final String USER = "";
    private static final String PASSWORD = "";

    private ConnectionDB() {}

    public static Connection getInstance() {
        if (instance == null) {
            synchronized (ConnectionDB.class) {
                if (instance == null) {
                    instance = new ConnectionDB();
                }
            }
        }
        return connection;
    }

    private static Connection installConnection() throws SQLException {
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        return connection;
    }
}
