package com.fitlife.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**This is a helper class to manage the database connection.I use this so all our DAO classes can get a connection from one place.*/


public class DatabaseManager {


    private static final String DATABASE_URL = "jdbc:sqlite:fitlife.db";

     
    public static Connection getConnection() throws SQLException {
        try {
         
            Class.forName("org.sqlite.JDBC"); [cite_start]
        } catch (ClassNotFoundException e) {
          
            System.err.println("SQLite JDBC driver not found.");
            e.printStackTrace();
        }

 
        return DriverManager.getConnection(DATABASE_URL); [cite_start]
    }
}