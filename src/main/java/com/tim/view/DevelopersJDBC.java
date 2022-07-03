package com.tim.view;

import java.sql.*;

public class DevelopersJDBC {

    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/datadevelopers";

    static final String USER = "root";
    static final String PASSWORD = "password";

    public static void startingJDBC() throws SQLException {

        Connection connection;
        Statement statement;

        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        statement = connection.createStatement();

        String sqlSELECT = "SELECT * FROM developers";

        ResultSet resultSet = statement.executeQuery(sqlSELECT);
        System.out.println("Retrieving data from database...");

        System.out.println("\nDevelopers:");
        while (resultSet.next()) {
            int developers_id = resultSet.getInt("developers_id");
            String FirstName = resultSet.getString("First_Name");
            String LastName = resultSet.getString("Last_Name");
            String status = resultSet.getString("status");
            String specialty = resultSet.getString("specialty");
            String skills = resultSet.getString("skills");

            System.out.println("\n================\n");
            System.out.println("developers_id: " + developers_id);
            System.out.println("FirstName: " + FirstName);
            System.out.println("LastName: " + LastName);
            System.out.println("status: " + status);
            System.out.println("specialty: " + specialty);
            System.out.println("skills: " + skills);

        }
        resultSet.close();
        statement.close();
        connection.close();
    }

}
