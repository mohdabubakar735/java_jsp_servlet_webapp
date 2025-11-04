package com.testweb.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.testweb.entities.Users;

public class UsersDao {
    
    private DataSource dataSource;

    public UsersDao() {}
    public UsersDao(DataSource dataSource) {
    	this.dataSource= dataSource;
    }

    public List<Users> getAllUsers() {
        List<Users> usersList = new ArrayList<>();
        try {
        	Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users ORDER BY userid DESC");

            while (rs.next()) {
                Users u = new Users();
                u.setUserid(rs.getInt("userid"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setIsactive(rs.getBoolean("isactive"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));
                u.setCreatedby(rs.getString("createdby"));
                u.setCreatedat(rs.getTimestamp("createdat"));
                usersList.add(u);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public Users saveUser(Users user) {
        String insertSQL = "INSERT INTO users (userid, username, password, isactive, email, "
        		+ " role, createdby, createdat) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
        	Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertSQL);
            ps.setInt(1, user.getUserid());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setBoolean(4, user.getIsactive());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getRole());
            ps.setString(7, user.getCreatedby());
            ps.setTimestamp(8, user.getCreatedat());

            int rows = ps.executeUpdate();
            System.out.println("Inserted " + rows + " row(s) into users table");

            ps.close();
        } catch (Exception e) {
            System.out.println("Error saving user: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return user;
    }

    public int deleteUser(String userid) {
        System.out.println("Proceed to delete user...");

        String deleteSQL = "DELETE FROM users WHERE userid= ?";
        int rowsDeleted = 0;

        try {
        	Connection conn = dataSource.getConnection();
        	PreparedStatement ps = conn.prepareStatement(deleteSQL);
            ps.setInt(1, Integer.parseInt(userid));
            rowsDeleted = ps.executeUpdate();

            System.out.println("Deleted " + rowsDeleted + " row(s) from users table");
            ps.close();

        } catch (Exception e) {
            System.out.println("Error deleting user: " + e.getMessage());
            e.printStackTrace();
        }

        return rowsDeleted;
    }

    public Users getUserById(Integer userid) {
        Users user = new Users();
        try {
        	Connection conn = dataSource.getConnection();
        	Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE userid=" + userid);

            while (rs.next()) {
                user.setUserid(rs.getInt("userid"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setIsactive(rs.getBoolean("isactive"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
              
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }



    public Users updateUser(Users user) {
        System.out.println("Proceed to update user...");
        String updateSQL = "UPDATE users SET username=?, password=?, isactive=?, email=?, role=?, "
        		+ "createdby=?, createdat=? WHERE userid=?";

        try {
        	Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(updateSQL);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setBoolean(3, user.getIsactive());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getRole());
            ps.setString(6, user.getCreatedby());
            ps.setTimestamp(7, user.getCreatedat());
            ps.setInt(8, user.getUserid());
            ps.execute();
            System.out.println("Updated user(s) for the userid :" + user.getUserid());
        } catch (SQLException e) {
            System.out.println("Error in updating user: " + e.getMessage());
            e.printStackTrace();
        }
        return user;

    }
    
}
