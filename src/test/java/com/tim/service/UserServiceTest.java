package com.tim.service;

import com.tim.utils.JdbcUtils;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserServiceTest {
    DeveloperService userService = new DeveloperService();

    @Test
    public void save() throws SQLException {
        userService.developerRepository.save();
        assertNull(userService.developerRepository.resultSet);
        assertNull(userService.developerRepository.statement);
        assertNull(userService.developerRepository.preparedStatement);
        assertNull(userService.developerRepository.preparedStatementIns);
        assertTrue(JdbcUtils.getInstance().isClosed());
    }

    @Test
    public void getAll() {
        userService.developerRepository.getAll();
        assertEquals(userService.developerRepository.sqlQuery, """
                SELECT developers_skills.id AS id, developers_skills.developers_id AS developer_id,developer.firstName AS firstName, developer.lastName AS lastName, developer.status AS status, specialty.name AS specialty, skills.name AS skill
                FROM developers_skills LEFT JOIN developer ON developers_skills.developers_id = developer.id
                LEFT JOIN specialty ON developer.specialty_id = specialty.id LEFT JOIN skills ON developers_skills.skills_id = skills.id;""");
    }

    @Test
    public void deleteById() {
        userService.developerRepository.deleteById(1L);
        assertEquals(userService.developerRepository.sqlQuery, "UPDATE developer SET status = 'DELETED' WHERE id = ?");
    }

    @Test
    public void update() {
        userService.developerRepository.update(1L, "Nick", "Tester");
        assertEquals(userService.developerRepository.sqlQuery, "UPDATE developer SET firstName = ?, lastName = ? WHERE id = ?");
    }

    @Test
    public void insert() {
        userService.developerRepository.insert("Tom", "Anderson", "ACTIVE", 1, 1);
        assertEquals(userService.developerRepository.sqlQuery, "INSERT INTO developer(firstName, lastName, status, specialty_id) " +
                "VALUES (?,?,?,?)");
    }

    //==================================Mockito===================================



}
