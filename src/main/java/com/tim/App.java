package com.tim;

import com.tim.view.MainView;

import java.sql.SQLException;

public class App {
    public static void main( String[] args ) throws SQLException {
        MainView mainView = new MainView();
        mainView.start();
    }
}
