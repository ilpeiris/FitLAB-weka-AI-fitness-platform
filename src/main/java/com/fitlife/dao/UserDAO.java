package com.fitlife.dao;


import com.fitlife.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// import the bcrypt library
import org.mindrot.jbcrypt.BCrypt;


public class UserDAO {

    public boolean registerUser(User user) {


        String sql = "INSERT INTO Users (username, password) VALUES (?, ?)";

  
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));



        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {


            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, hashedPassword); // Save the HASHED password

  
            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
          
            System.err.println("Error registering user: " + e.getMessage());
            return false;
        }
    }

    public User loginUser(String username, String plainTextPassword) {
        String sql = "SELECT * FROM Users WHERE username = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {

             
                if (rs.next()) {
                    
                    String storedHashedPassword = rs.getString("password");

                    if (BCrypt.checkpw(plainTextPassword, storedHashedPassword)) {

                        
                        User user = new User();
                        user.setUserId(rs.getInt("user_id"));
                        user.setUsername(rs.getString("username"));
                      

                        return user; 
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error during login: " + e.getMessage());
        }

      
        return null;
    }
}