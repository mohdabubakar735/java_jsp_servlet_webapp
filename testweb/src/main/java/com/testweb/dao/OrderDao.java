package com.testweb.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.testweb.entities.Order;

public class OrderDao {

    private DataSource dataSource;
    
    public OrderDao() {}
 
    public OrderDao(DataSource dataSource) {
    	this.dataSource= dataSource;
    }


    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try {
        	Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders ORDER BY order_date DESC");

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setDescription(rs.getString("description"));
                orders.add(order);
            }

            rs.close();
            stmt.close();
//            closeConn(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    
    public Order getOrderById(Integer orderId) {
    	Order order = new Order();
        try {
        	Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders where order_id="+orderId);

            while (rs.next()) {
                
                order.setOrderId(rs.getInt("order_id"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setDescription(rs.getString("description"));
            }

            rs.close();
            stmt.close();
//            closeConn(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }


    public Order saveOrder(Order order) {
        String insertSQL = "INSERT INTO orders (order_id, order_date, total_amount, description) VALUES (?, ?, ?, ?)";

        try {
        	Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertSQL);
            ps.setInt(1, order.getOrderId());
            ps.setTimestamp(2, order.getOrderDate());
            ps.setDouble(3, order.getTotalAmount());
            ps.setString(4, order.getDescription());
            int rows = ps.executeUpdate();
            System.out.println("Inserted " + rows + " row(s) into orders table");
            ps.close();
//            closeConn(conn);
        } catch (Exception e) {
            System.out.println("Error saving order: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return order;
    }

    public int deleteOrder(String orderId) {
    	
    	System.out.println("proceed to deleting redcord..");
    	String deleteSQL = "DELETE FROM orders WHERE order_id = ?";
        int rowsDeleted = 0;

        try { 
        	Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(deleteSQL);

            ps.setInt(1, Integer.parseInt(orderId));
            rowsDeleted = ps.executeUpdate();
            System.out.println("Deleted " + rowsDeleted + " order(s) from the orders table");
        } catch (SQLException e) {
            System.out.println("Error in deleting order: " + e.getMessage());
            e.printStackTrace();
        }
        return rowsDeleted;
    }

	public Order updateOrder(Order order) {
		System.out.println("proceed to deleting redcord..");
    	String deleteSQL = "update orders set order_date=?, total_amount=?, description=? "
    			+ "where order_id=?";

        try { 
        	Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(deleteSQL);

             ps.setTimestamp(1, order.getOrderDate());
             ps.setDouble(2, order.getTotalAmount());
             ps.setString(3, order.getDescription());
             ps.setInt(4, order.getOrderId());
             ps.execute();
             System.out.println("Update order(s) from the orderId :"+order.getOrderId());
        } catch (SQLException e) {
            System.out.println("Error in deleting order: " + e.getMessage());
            e.printStackTrace();
        }
        return order;
		
	}
}
