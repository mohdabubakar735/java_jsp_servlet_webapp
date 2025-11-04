package com.testweb.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.testweb.entities.Customer;

public class CustomerDao {

    private DataSource dataSource;

    public CustomerDao() {}

    public CustomerDao(DataSource dataSource) {
    	this.dataSource=dataSource;
    }

	public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers ORDER BY customer_id DESC");

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setCustomerFirstName(rs.getString("first_name"));
                customer.setCustomerLastName(rs.getString("last_name"));
                customer.setCustomerEmail(rs.getString("email"));
                customer.setCustomerPhoneNumber(rs.getString("phone_number"));
                customer.setCustomerAddress(rs.getString("address"));
                customer.setCustomerAge(rs.getInt("age"));
                customer.setCustomerType(rs.getString("customer_type"));
                customer.setCustBalance(rs.getDouble("cust_balance"));
                customer.setCustSex(rs.getString("cust_sex"));
                customer.setCity(rs.getString("city"));
                customer.setState(rs.getString("state"));
                customer.setzipcode(rs.getString("zip_code"));
                customer.setJoinDate(rs.getDate("join_date"));

                customers.add(customer);
            }

            rs.close();
            stmt.close();
//            closeConn(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
    public Customer saveCustomer(Customer customer) {
        String insertSQL = "INSERT INTO customers (customer_id, first_name, last_name, email, phone_number, address, city, state, zip_code, age, customer_type,cust_balance, cust_sex, join_date) "
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
        	Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertSQL);
            ps.setInt(1, customer.getCustomerId());
            ps.setString(2, customer.getCustomerFirstName());
            ps.setString(3, customer.getCustomerLastName());
            ps.setString(4, customer.getCustomerEmail());
            ps.setString(5, customer.getCustomerPhoneNumber());
            ps.setString(6, customer.getCustomerAddress());
            ps.setString(7, customer.getCity());
            ps.setString(8, customer.getState());
            ps.setString(9, customer.getzipcode());
            ps.setInt(10, customer.getCustomerAge());
            ps.setString(11, customer.getCustomerType());
            ps.setDouble(12, customer.getCustBalance());
            ps.setString(13, customer.getCustSex());
            ps.setDate(14, customer.getJoinDate());


            int rows = ps.executeUpdate();
            System.out.println("Inserted " + rows + " row(s) into customers table");

            ps.close();
//            closeConn(conn);
        } catch (Exception e) {
            System.out.println("Error saving customer: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return customer;
    }

    public int deleteCustomer(String customerId) {
        System.out.println("Proceed to delete customer...");

        String deleteSQL = "DELETE FROM customers WHERE customer_id = ?";
        int rowsDeleted = 0;

        try {
        	Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(deleteSQL);
            ps.setInt(1, Integer.parseInt(customerId));
            rowsDeleted = ps.executeUpdate();

            System.out.println("Deleted " + rowsDeleted + " row(s) from customers table");
            ps.close();
//            closeConn(conn);

        } catch (Exception e) {
            System.out.println("Error deleting customer: " + e.getMessage());
            e.printStackTrace();
        }

        return rowsDeleted;
    }

    public Customer getCustomerById(Integer customerId) {
        Customer customer = new Customer();
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers WHERE customer_id=" + customerId);

            while (rs.next()) {
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setCustomerFirstName(rs.getString("first_name"));
                customer.setCustomerLastName(rs.getString("last_name"));
                customer.setCustomerEmail(rs.getString("email"));
                customer.setCustomerPhoneNumber(rs.getString("phone_number"));
                customer.setCustBalance(rs.getDouble("cust_balance"));
                customer.setCustomerAddress(rs.getString("address"));
                customer.setCustomerAge(rs.getInt("age"));
                customer.setCustSex(rs.getString("cust_sex"));
                customer.setCity(rs.getString("city"));
                customer.setCustomerType(rs.getString("customer_type"));
                customer.setJoinDate(rs.getDate("join_date"));
            }

            rs.close();
            stmt.close();
//            closeConn(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public Customer updateCustomer(Customer customer) {
        System.out.println("Proceed to update customer...");

        String updateSQL = "UPDATE customers SET first_name=?, last_name=?, email=?, address=?, "
        		+ " age=?, customer_type=?, cust_balance=?, cust_sex=?, join_date=?, city=?, phone_number=? "
        		+ " WHERE customer_id=?";

        try {
           Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(updateSQL);

            ps.setString(1, customer.getCustomerFirstName());
            ps.setString(2, customer.getCustomerLastName());
            ps.setString(3, customer.getCustomerEmail());
            ps.setString(4, customer.getCustomerAddress());
            ps.setInt(5, customer.getCustomerAge());
            ps.setString(6, customer.getCustomerType());
            ps.setDouble(7, customer.getCustBalance());
            ps.setString(8, customer.getCustSex());
            ps.setDate(9, customer.getJoinDate());
            ps.setString(10, customer.getCity());
            ps.setString(11, customer.getCustomerPhoneNumber());
            ps.setInt(12, customer.getCustomerId());

            ps.executeUpdate();
            System.out.println("Updated customer with ID: " + customer.getCustomerId());

            ps.close();
//            closeConn(conn);

        } catch (SQLException e) {
            System.out.println("Error updating customer: " + e.getMessage());
            e.printStackTrace();
        }

        return customer;
    }

}
