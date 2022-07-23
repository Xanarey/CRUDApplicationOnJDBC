package com.tim.repository;
import com.tim.model.Developer;
import com.tim.model.Skill;
import com.tim.model.Specialty;
import com.tim.model.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DatabaseRepository implements DeveloperRepository {

    public Statement statement;
    public PreparedStatement preparedStatement;
    public PreparedStatement preparedStatementIns;
    public ResultSet resultSet;
    public String sqlQuery = "";

    @Override
    public Developer getById(Long aLong) {
        return getAllDeveloperInternal().stream()
                .filter(s -> s.getId().equals(aLong)).findFirst().orElse(null);
    }


    private List<Developer> getAllDeveloperInternal() {
        List<Developer> developerList = new ArrayList<>();
        Developer developer;
        try {
            statement = ConnectionDB.getInstance().createStatement(
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_UPDATABLE
            );

            sqlQuery = """
                SELECT developer.id AS id, developer.firstName AS firstName, developer.lastName AS lastName,developer.status AS status, specialty.name AS specialty, developer.specialty_id AS specialty_id
                FROM developer LEFT JOIN specialty ON developer.specialty_id = specialty.id;""";

            ResultSet resultSetAll = statement.executeQuery(sqlQuery);

            while (resultSetAll.next()) {
                Long id = resultSetAll.getLong("id");
                Long specialty_id = resultSetAll.getLong("specialty_id");
                String firstName = resultSetAll.getString("firstName");
                String lastName = resultSetAll.getString("lastName");
                String status = resultSetAll.getString("status");

                developer = new Developer();
                developer.setId(id);
                developer.setFirstName(firstName);
                developer.setLastName(lastName);

                if (Objects.equals(status, Status.ACTIVE.name()))
                    developer.setStatus(Status.ACTIVE);
                if (Objects.equals(status, Status.DELETED.name()))
                    developer.setStatus(Status.DELETED);
                developer.setSpecialty(getSpecialtyById(specialty_id));
                developer.setSkills(getSkillsById(id));

                developerList.add(developer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developerList;
    }

    private List<Skill> getSkillsById(Long id) {
        List<Skill> skills = new ArrayList<>();

        sqlQuery = "SELECT skills.name AS skills\n" +
                   "FROM developers_skills LEFT JOIN skills ON developers_skills.skills_id = skills.id\n" +
                   "WHERE developers_id = ?";
        try {
            preparedStatement = ConnectionDB.getInstance().prepareStatement(sqlQuery);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String skill = resultSet.getString("skills");
                Skill skillPlug = new Skill();
                skillPlug.setName(skill);
                skills.add(skillPlug);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
    }

    private Specialty getSpecialtyById(Long id) {
        Specialty specialty = new Specialty();
                sqlQuery =  "SELECT specialty.name AS specialty\n" +
                            "FROM developer LEFT JOIN specialty  on specialty.id = developer.specialty_id " +
                            "WHERE specialty_id = ? LIMIT 1\n";
        try {
            preparedStatement = ConnectionDB.getInstance().prepareStatement(sqlQuery);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String spec = resultSet.getString("specialty");
                specialty.setName(spec);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specialty;
    }

    @Override
    public void save() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatementIns != null) {
            try {
                preparedStatementIns.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            ConnectionDB.getInstance().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Developer> getAll() { return getAllDeveloperInternal();}

    @Override
    public void deleteById(Long id) {
        sqlQuery = "UPDATE developer SET status = 'DELETED' WHERE id = ?";
        try {
            preparedStatement = ConnectionDB.getInstance().prepareStatement(sqlQuery);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Long id, String firstNewName, String lastNewName)  {
        sqlQuery = "UPDATE developer SET firstName = ?, lastName = ? WHERE id = ?";
        try {
            preparedStatement = ConnectionDB.getInstance().prepareStatement(sqlQuery);
            preparedStatement.setString(1 ,firstNewName);
            preparedStatement.setString(2 ,lastNewName);
            preparedStatement.setLong(3 ,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(String firstName, String lastName, String status, int specialty, int skills)  {

        sqlQuery = "INSERT INTO developer(firstName, lastName, status, specialty_id) " +
                   "VALUES (?,?,?,?)";

        try {
            preparedStatementIns = ConnectionDB.getInstance().prepareStatement(sqlQuery);
            preparedStatementIns.setString(1, firstName);
            preparedStatementIns.setString(2, lastName);
            preparedStatementIns.setString(3, status);
            preparedStatementIns.setInt(4, specialty);
            preparedStatementIns.executeUpdate();
            insertDeveloper_Skills(skills);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void getAllSpecialty() {

        sqlQuery = "SELECT * FROM specialty";
        try {
            resultSet = statement.executeQuery(sqlQuery);
            System.out.println("\nSpecialty:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("\n================\n");
                System.out.println("id: " + id);
                System.out.println("name: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllSkills() {
        sqlQuery = "SELECT * FROM skills";
        try {
            resultSet = statement.executeQuery(sqlQuery);
            System.out.println("\nSkills:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("\n================\n");
                System.out.println("id: " + id);
                System.out.println("name: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertDeveloper_Skills(int skills) throws SQLException {
        String sqlQuery2 = "INSERT INTO developers_skills(developers_id, skills_id)" +
                "VALUES (?, ?)";

        preparedStatement = ConnectionDB.getInstance().prepareStatement(sqlQuery2);
        preparedStatement.setInt(1, getId());
        preparedStatement.setInt(2, skills);
        preparedStatement.executeUpdate();
    }

    public int getId() throws SQLException {
        String sqlQuery3 = "SELECT id FROM developer ORDER BY id DESC  LIMIT 1";

        statement = ConnectionDB.getInstance().createStatement();
        resultSet = statement.executeQuery(sqlQuery3);
        int id = 0;
        while (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        return id;
    }
}
