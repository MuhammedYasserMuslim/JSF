package com.java.jsfcourse.db.dao;

import com.java.jsfcourse.db.DBConnection;
import com.java.jsfcourse.db.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDao implements Repository<Person, Integer> {

    private static PersonDao instance;

    public static PersonDao getInstance() {
        if (instance == null)
            instance = new PersonDao();
        return instance;
    }

    private PersonDao() {}

    @Override
    public List<Person> findAll() {
        Connection connection = DBConnection.getConnection();
        if (connection == null)
            return new ArrayList<>();
        String query = "select * from persons";
        List<Person> people = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Person person = new Person(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("name"));
                people.add(person);
            }
            return people;

        } catch (SQLException e) {
            throw new RuntimeException("Error while finding people");
        } finally {
            DBConnection.closeConnection(connection);
        }

    }

    @Override
    public Person findById(Integer id) {
        Connection connection = DBConnection.getConnection();
        if (connection == null)
            return null;
        String query = "select * from persons where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Person(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding person");
        } finally {
            DBConnection.closeConnection(connection);
        }
        return null;
    }

    @Override
    public void save(Person person) {
        Connection connection = DBConnection.getConnection();
        if (connection != null) {
            if (person.getId() > 0) { //update
                String query = "UPDATE persons SET `username` = ?, `password` = ?, `name` = ? WHERE (`id` = ?);";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, person.getUsername());
                    preparedStatement.setString(2, person.getPassword());
                    preparedStatement.setString(3, person.getName());
                    preparedStatement.setInt(4, person.getId());
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    DBConnection.closeConnection(connection);
                }
            } else { // create
                String query = "INSERT INTO persons (`username`, `password`, `name`) VALUES (?, ?, ?);";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, person.getUsername());
                    preparedStatement.setString(2, person.getPassword());
                    preparedStatement.setString(3, person.getName());
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    DBConnection.closeConnection(connection);
                }
            }
        }
    }

    @Override
    public void deleteById(Integer id) {
        Connection connection = DBConnection.getConnection();
        String query = "delete from persons where id = ?";
        if (connection != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Error while deleting person");
            }
        }
    }

}
