package com.tim.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcUtils {
    private static volatile JdbcUtils instance;
    private static Connection connection = null;
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/datadevelopers";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    private JdbcUtils() {}

    public static Connection getInstance() {
        if (instance == null) {
            synchronized (JdbcUtils.class) {
                if (instance == null) {
                    instance = new JdbcUtils();
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

    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return getInstance().prepareStatement(sql);
    }
}
