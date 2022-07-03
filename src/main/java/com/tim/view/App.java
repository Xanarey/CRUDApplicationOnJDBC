package com.tim.view;

import java.sql.SQLException;

public class App {
    public static void main( String[] args ) throws SQLException {
        System.out.println( "Start App" );
        DevelopersJDBC developersJDBC = new DevelopersJDBC();
        developersJDBC.startingJDBC();

        UserChoice userChoice = new UserChoice();
        userChoice.start();
    }
}
