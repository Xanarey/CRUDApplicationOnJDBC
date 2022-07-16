package com.tim.service;

import com.tim.repository.ConnectionDB;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService userService = new UserService();

    @Test
    public void save() throws SQLException {
        userService.databaseRepository.save();
        assertNull(userService.databaseRepository.resultSet);
        assertNull(userService.databaseRepository.statement);
        assertNull(userService.databaseRepository.preparedStatement);
        assertNull(userService.databaseRepository.preparedStatementIns);
        assertTrue(ConnectionDB.getInstance().isClosed());
    }

    @Test
    public void getAll() {
        userService.databaseRepository.getAll();
        assertEquals(userService.databaseRepository.sqlQuery, """
                SELECT developers_skills.id AS id, developers_skills.developers_id AS developer_id,developer.firstName AS firstName, developer.lastName AS lastName, developer.status AS status, specialty.name AS specialty, skills.name AS skill
                FROM developers_skills LEFT JOIN developer ON developers_skills.developers_id = developer.id
                LEFT JOIN specialty ON developer.specialty_id = specialty.id LEFT JOIN skills ON developers_skills.skills_id = skills.id;""");
    }

    @Test
    public void deleteById() {
        userService.databaseRepository.deleteById(1L);
        assertEquals(userService.databaseRepository.sqlQuery, "UPDATE developer SET status = 'DELETED' WHERE id = ?");
    }

    @Test
    public void update() {
        userService.databaseRepository.update(1L, "Nick", "Tester");
        assertEquals(userService.databaseRepository.sqlQuery, "UPDATE developer SET firstName = ?, lastName = ? WHERE id = ?");
    }

    @Test
    public void insert() {
        userService.databaseRepository.insert("Tom", "Anderson", "ACTIVE", 1, 1);
        assertEquals(userService.databaseRepository.sqlQuery, "INSERT INTO developer(firstName, lastName, status, specialty_id) " +
                "VALUES (?,?,?,?)");
    }
}