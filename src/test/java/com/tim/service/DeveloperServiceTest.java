package com.tim.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class DeveloperServiceTest {
    DeveloperService developerService = new DeveloperService();

    public DeveloperServiceTest() throws SQLException {
    }



    @Test
    public void getAll() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void update() {
    }

    @Test
    public void insert() {
    }

    @After
    public void save() {
        ResultSet resultSet = developerService.resultSet;
        Statement statement = developerService.statement;
        PreparedStatement preparedStatement = developerService.preparedStatement;
        assertNotEquals(resultSet, null);
        assertNotEquals(statement, null);
        assertNotEquals(preparedStatement, null);
    }

     // TODO СДЕЛАТЬ ОТРАБОТКУ теста Save после остальных тестов и запуска кода
}