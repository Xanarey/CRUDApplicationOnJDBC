package com.tim.repository;
import com.tim.model.Developer;
import com.tim.model.Skill;
import com.tim.model.Specialty;
import com.tim.model.Status;
import com.tim.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JdbcDeveloperRepositoryImpl implements DeveloperRepository {

    @Override
    public Developer getById(Long aLong) {
        Developer developer = new Developer();
        Specialty specialty = new Specialty();
        List<Skill> skills = new ArrayList<>();

        try(Statement statement = JdbcUtils.getInstance().createStatement()) {
            String sqlQuery = "" +
                    "SELECT developer.id AS id, developer.firstName, developer.lastName, developer.status, specialty.name AS specialty, developers_skills.skills_id AS skills " +
                    "FROM developer " +
                    "LEFT JOIN specialty ON developer.specialty_id = specialty.id " +
                    "LEFT JOIN developers_skills ON developer.id = developers_skills.developers_id;";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            developer.setId(resultSet.getLong("id"));
            developer.setFirstName(resultSet.getString("firstName"));
            developer.setLastName(resultSet.getString("lastName"));
            if (resultSet.getString("status").equals("ACTIVE"))
                developer.setStatus(Status.ACTIVE);
            if (resultSet.getString("status").equals("DELETED"))
                developer.setStatus(Status.DELETED);

            specialty.setName(resultSet.getString("specialty"));
            developer.setSpecialty(specialty);

            while (resultSet.next()) {
                Skill skill = new Skill();
                skill.setName(resultSet.getString("skill"));
                skills.add(skill);
            }
            developer.setSkills(skills);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developer; //  TODO закончил здесь
    }

    @Override
    public List<Developer> getAll() {
        List<Developer> developerList = new ArrayList<>();
        Developer developer;
        try {
            Statement statement = JdbcUtils.getInstance().createStatement(
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_UPDATABLE
            );

            //TODO: get all data with 1 query
            String sqlQuery = "SELECT developer.id AS id, developer.firstName AS firstName, developer.lastName AS lastName,developer.status AS status, specialty.name AS specialty, developer.specialty_id AS specialty_id " +
                              "FROM developer LEFT JOIN specialty ON developer.specialty_id = specialty.id;";

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

    @Override
    public void deleteById(Long id) {
        String sqlQuery = "UPDATE developer SET status = 'DELETED' WHERE id = ?";
        //TODO: use try-with-resource
        try {
            PreparedStatement preparedStatement = JdbcUtils.getInstance().prepareStatement(sqlQuery);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Long id, String firstNewName, String lastNewName)  {
        String sqlQuery = "UPDATE developer SET firstName = ?, lastName = ? WHERE id = ?";
        try {
            JdbcUtils.getPreparedStatement(sqlQuery).setString(1 ,firstNewName);
            JdbcUtils.getPreparedStatement(sqlQuery).setString(2 ,lastNewName);
            JdbcUtils.getPreparedStatement(sqlQuery).setLong(3 ,id);
            JdbcUtils.getPreparedStatement(sqlQuery).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
