//package com.tim.service;
//
//import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.Spy;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//public class UserServiceTest {
//    UserService userService = new UserService();
//
//    public UserServiceTest() throws SQLException {
//    }
//
//    @Test
//    public void save() throws SQLException {
//        userService.save();
//
//        assertTrue(userService.connection.isClosed());
//        assertTrue(userService.statement.isClosed());
//
//        assertNull(userService.resultSet);
//        assertNull(userService.preparedStatement);
//        assertNull(userService.preparedStatementIns);
//    }
//
//    @Test
//    public void getAll() throws SQLException {
//        userService.getAll();
//        assertEquals("SELECT developers_skills.id AS id, developers.id AS developer_id,developers.firstName AS firstName, developers.lastName AS lastName, developers.status AS status, specialty.name AS specialty, skills.name AS skill\n" +
//                        "FROM developers_skills LEFT JOIN developers ON developers_skills.developers_id = developers.id\n" +
//                        "LEFT JOIN specialty ON developers.specialty_id = specialty.id LEFT JOIN skills ON developers_skills.skills_id = skills.id;"
//                , userService.sqlQuery);
//    }
//
//    @Test
//    public void deleteById() {
//        databaseRepository.deleteById(1L);
//        assertEquals("UPDATE developers SET status = 'DELETED' WHERE id = ?", databaseRepository.sqlQuery);
//    }
//
//    @Test
//    public void update() {
//        databaseRepository.update(1L, "Test", "LastTest");
//        assertEquals("UPDATE developers SET firstName = ?, lastName = ? WHERE id = ?", databaseRepository.sqlQuery);
//    }
//
//    @Test
//    public void insert() {
//        databaseRepository.insert("1","2","ACTIVE",1,1);
//        assertEquals("INSERT INTO developers(firstName, lastName, status, specialty_id) VALUES (?,?,?,?)"
//                , databaseRepository.sqlQuery);
//    }
//
//    // Применение моков с Mockito.mock
//    @Test
//    public void whenNotUseMockAnnotation_thenCorrect() {
//        List mockList = Mockito.mock(ArrayList.class);
//
//        mockList.add("one");
//        Mockito.verify(mockList).add("one");
//        assertEquals(0, mockList.size());
//
//        Mockito.when(mockList.size()).thenReturn(100);
//        assertEquals(100, mockList.size());
//    }
//
//    // Применение моков через @Mock
//
//    @Mock
//    List<String> mockedList;
//
//    @Test
//    public void whenUseMockAnnotation_thenMockIsInjected() {
//        mockedList.add("one");
//        Mockito.verify(mockedList).add("one");
//        assertEquals(0, mockedList.size());
//
//        Mockito.when(mockedList.size()).thenReturn(100);
//        assertEquals(100, mockedList.size());
//    }
//
//    @Spy
//    List<String> spiedList = new ArrayList<>();
//
//    @Test
//    public void whenUseSpyAnnotation_thenSpyIsInjectedCorrectly() {
//        spiedList.add("one");
//        spiedList.add("two");
//
//        Mockito.verify(spiedList).add("one");
//        Mockito.verify(spiedList).add("two");
//
//        assertEquals(2, spiedList.size());
//
//        Mockito.doReturn(100).when(spiedList).size();
//        assertEquals(100, spiedList.size());
//    }
//
//
//
//
//
//
//
//
//
//}