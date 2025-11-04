package com.testweb.dao;

import java.sql.Connection;		
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.testweb.entities.Employee;

public class EmployeeDao {

    private DataSource dataSource;
    
	public EmployeeDao() {
	}
	
	  public EmployeeDao(DataSource dataSource) {
	    	this.dataSource= dataSource;
	    }

	
	/**
	 * Get all employees
	 * 
	 * @return List<Employee>
	 */
	public List<Employee> getAllEmployee(){
		
		 List<Employee>  news = new ArrayList<>();
		 
		 try {
	        Connection conn = dataSource.getConnection();
		   Statement stmt =	conn.createStatement();
		   ResultSet rs = stmt.executeQuery("select * from employees order by fullname");
		   while(rs.next()) {
			   Employee emp = new Employee();
			   emp.setEmpId(rs.getInt(1));
			   emp.setFullName( rs.getString(2));
			   emp.setBalance( rs.getDouble(3));
			   emp.setEmpType( rs.getString(4));
			   emp.setEmpSex( rs.getString(5));
			   emp.setCity( rs.getString(6));
			   emp.setJoiningDt( rs.getTimestamp(7));
			   news.add(emp);
		   }
		   
		   rs.close();
		   stmt.close();
//		   closeConn(conn);
		   
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 return news;
	}
	
	/**
	 * Create new Employee
	 * 
	 * @param employee
	 * @return employee
	 */
	public Employee saveEmployee(Employee employee) {
		
		String insertSQL = "INSERT INTO public.employees (empid, fullname, balance, emptype, sex, city, joiningdt)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
        	Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertSQL);
            ps.setInt(1, employee.getEmpId());
            ps.setString(2, employee.getFullName());
            ps.setDouble(3, employee.getBalance());
            ps.setString(4, employee.getEmpType());
            ps.setString(5, employee.getEmpSex());
            ps.setString(6, employee.getCity());
            ps.setTimestamp(7, employee.getJoiningDt());
            int rows = ps. executeUpdate();
            System.out.println("Inserted " + rows + " row(s) into employees table");
            ps.close();
//            closeConn(conn);
        } catch (Exception e) {
            System.out.println("Error saving employees :"+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
			   
        return employee;
	}
	
	public Employee getEmployeeById(Integer employeeId) {
	    Employee employee = new Employee();
	    try {
        	Connection conn = dataSource.getConnection();
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM employees WHERE empid=" + employeeId);

	        while (rs.next()) {
	            employee.setEmpId(rs.getInt("empid"));
	            employee.setFullName(rs.getString("fullName"));
	            employee.setEmpType(rs.getString("emptype"));
	            employee.setBalance(rs.getDouble("balance"));
	            employee.setEmpSex(rs.getString("sex"));
	            employee.setCity(rs.getString("city"));
	            employee.setJoiningDt(rs.getTimestamp("joiningdt"));
	        }

	        rs.close();
	        stmt.close();
//	        closeConn(conn);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return employee;
	}
	
	 public int deleteEmployee(String empId) {
	    	
    	System.out.println("proceed to deleting redcord..");
    	String deleteSQL = "DELETE FROM employees WHERE empid =?";
        int rowsDeleted = 0;

        try { 
        	Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(deleteSQL);

            ps.setInt(1, Integer.parseInt(empId));
            rowsDeleted = ps.executeUpdate();
            System.out.println("Deleted " + rowsDeleted + " employee(s) from the employees table");
        } catch (SQLException e) {
            System.out.println("Error in deleting employees: " + e.getMessage());
            e.printStackTrace();
        }
        return rowsDeleted;
    }
	 
	 public Employee updateEmpployee(Employee emp) {
		System.out.println("proceed to deleting redcord..");
    	String deleteSQL = "update employees set fullname=?, balance=?, emptype=?, sex=?, city=?, joiningdt=? "
    			+ "where empid=?";

        try { 
        	Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(deleteSQL);

             ps.setString(1, emp.getFullName());
             ps.setDouble(2, emp.getBalance());
             ps.setString(3, emp.getEmpType());
             ps.setString(4, emp.getEmpSex());
             ps.setString(5, emp.getCity());
             ps.setTimestamp(6, emp.getJoiningDt());
             ps.setInt(7, emp.getEmpId());
             ps.execute();
             System.out.println("Update employee(s) from the empid :"+emp.getEmpId());
        } catch (SQLException e) {
            System.out.println("Error in deleting employee: " + e.getMessage());
            e.printStackTrace();
        }
        return emp;
		
	}
	
	
}
