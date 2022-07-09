package com.tim.service;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class DeveloperServiceTest {
    DeveloperService developerService = new DeveloperService();

    public DeveloperServiceTest() throws SQLException {
    }

    @Test
    public void save() throws SQLException {
        developerService.save();

        assertTrue(developerService.connection.isClosed());
        assertTrue(developerService.statement.isClosed());

        assertNull(developerService.resultSet);
        assertNull(developerService.preparedStatement);
        assertNull(developerService.preparedStatementIns);
    }

    @Test
    public void getAll() {
        developerService.getAll();
        assertEquals("SELECT developers_skills.id AS id, developers.id AS developer_id,developers.firstName AS firstName, developers.lastName AS lastName, developers.status AS status, specialty.name AS specialty, skills.name AS skill\n" +
                              "FROM developers_skills LEFT JOIN developers ON developers_skills.developers_id = developers.id\n" +
                              "LEFT JOIN specialty ON developers.specialty_id = specialty.id LEFT JOIN skills ON developers_skills.skills_id = skills.id;"
                , developerService.sqlQuery);
    }

    @Test
    public void deleteById() throws SQLException {
        developerService.deleteById(1L);
        assertEquals("UPDATE developers SET status = 'DELETED' WHERE id = ?", developerService.sqlQuery);
    }

    @Test
    public void update() throws SQLException {
        developerService.update(1L, "Test", "LastTest");
        assertEquals("UPDATE developers SET firstName = ?, lastName = ? WHERE id = ?", developerService.sqlQuery);
    }

    @Test
    public void insert() throws SQLException {
        developerService.insert("1","2","ACTIVE",1,1);
        assertEquals("INSERT INTO developers(firstName, lastName, status, specialty_id) VALUES (?,?,?,?)"
                , developerService.sqlQuery);
    }
}