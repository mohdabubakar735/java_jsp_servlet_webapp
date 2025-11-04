package com.testweb.dao;

import java.sql.*;

import javax.sql.DataSource;

import com.testweb.entities.Users;

public class LoginDao {


    private DataSource dataSource;

    public LoginDao() {}
    
    public LoginDao(DataSource dataSource) {
    	this.dataSource= dataSource;
    }


    public Users getUserByUsername(String username) {
        Users user = new Users();
        String selectSQL= "SELECT * FROM users WHERE username=?";
        try {
//        	Connection conn1 =  JdbcConnection.getConnection();
//        	JdbcConnection.closeConn(conn1);
        	System.out.println("dataSource:"+dataSource);
        	Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(selectSQL);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                user.setUserid(rs.getInt("userid"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setIsactive(rs.getBoolean("isactive"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


}
