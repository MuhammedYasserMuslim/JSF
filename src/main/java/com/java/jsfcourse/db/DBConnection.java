package com.java.jsfcourse.db;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {


    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsf", "root", "root");
        } catch (SQLException e) {
            throw new RuntimeException("Error occured while connecting to database");
        }
//        JOptionPane.showMessageDialog(null, "Connected to database");
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Error occurred while closing connection");
            }
        }
    }

}
