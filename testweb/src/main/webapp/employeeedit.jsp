<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*" %>
<%@ page import="com.testweb.entities.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Edit Employee - E-commerce Example</title>
  <link rel="stylesheet" href="/testweb/css/design.css">
  <script type="text/javascript" src="/testweb/jscript/employee.js"></script>
</head>

<body>
<%
    // Retrieve the employee object from the session
    Employee emp = (Employee) session.getAttribute("employee");
	String joiningDate = (String)session.getAttribute("joiningDate");
 
%>

<h2>Edit Employee</h2>

<form name="employeeEditFrm" action="/testweb/employee" method="post">
    <!-- Hidden Employee ID -->
    <input type="hidden" name="empId" id="empId" value="<%= emp.getEmpId() %>">

    <div class="form-section">
        <div class="form-group">
            <label for="empFullName">Full Name:</label>
            <input type="text" id="empFullName" name="empFullName"
                   value="<%= emp.getFullName() %>" required>
        </div>

        <div class="form-group">
            <label for="empBalance">Balance:</label>
            <input type="number" id="empBalance" name="empBalance"
                   value="<%= emp.getBalance() %>" required>
        </div>

        <div class="form-group">
            <label>Employee Type:</label>
            <div class="radio-group">
                <label><input type="radio" name="empType" value="FULLTIME"
                    <%= "FULLTIME".equals(emp.getEmpType()) ? "checked" : "" %>> Full-Time</label>
                <label><input type="radio" name="empType" value="PARTTIME"
                    <%= "PARTTIME".equals(emp.getEmpType()) ? "checked" : "" %>> Part-Time</label>
            </div>
        </div>

        <div class="form-group">
            <label>Sex:</label>
            <div class="radio-group">
                <label><input type="radio" name="empSex" value="M"
                    <%= "M".equals(emp.getEmpSex()) ? "checked" : "" %>> Male</label>
                <label><input type="radio" name="empSex" value="F"
                    <%= "F".equals(emp.getEmpSex()) ? "checked" : "" %>> Female</label>
                <label><input type="radio" name="empSex" value="O"
                    <%= "O".equals(emp.getEmpSex()) ? "checked" : "" %>> Others</label>
            </div>
        </div>

        <div class="form-group">
            <label for="empCity">City:</label>
            <select id="empCity" name="empCity">
                <option value="">Select City</option>
                <option value="Bangalore" <%= "Bangalore".equals(emp.getCity()) ? "selected" : "" %>>Bangalore</option>
                <option value="Delhi" <%= "Delhi".equals(emp.getCity()) ? "selected" : "" %>>Delhi</option>
                <option value="Mumbai" <%= "Mumbai".equals(emp.getCity()) ? "selected" : "" %>>Mumbai</option>
                <option value="Kolkata" <%= "Kolkata".equals(emp.getCity()) ? "selected" : "" %>>Kolkata</option>
            </select>
        </div>

        <div class="form-group">
            <label for="empJoiningDate">Joining Date:</label>
            <input type="date" id="empJoiningDate" name="empJoiningDate" value="<%= joiningDate %>">
        </div>
    </div>

    <div class="buttons">
        <input type="submit" name="submit" value="Update Employee"/>
    </div>
</form>


</body>
</html>
