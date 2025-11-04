<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.*" %>
<%@ page import="com.testweb.entities.*" %>
<%@include file="menu.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Employee Page-E-commerce Example</title>
  <link rel="stylesheet" href="/testweb/css/design.css">
   <script type="text/javascript" src="/testweb/jscript/employee.js">
  </script>
  
</head>
<body>

  <h2>Employee Page</h2>
  
  <form name="employeeFrm" action="/testweb/employee" method="post">
  
    <input type="hidden" name="empId" value="" id="empId"/>
    <div class="form-section">
      <div class="form-group">
        <label for="empFullName">Full Name:</label>
        <input type="text" id="empFullName" name="empFullName" placeholder="Enter full name" value="">
      </div>

      <div class="form-group">
        <label for="empBalance">Balance:</label>
        <input type="number" id="empBalance" name="empBalance" placeholder="Enter balance" value="">
      </div>

      <div class="form-group">
        <label>Employee Type:</label>
        <div class="radio-group">
          <label><input type="radio" name="empType" value="FULLTIME" checked="checked"> Full-Time</label>
          <label><input type="radio" name="empType" value="PARTTIME"> Part-Time</label>
        </div>
      </div>

      <div class="form-group">
        <label>Sex:</label>
        <div class="radio-group">
          <label><input type="radio" name="empSex" value="M" checked="checked">Male</label>
          <label><input type="radio" name="empSex" value="F">Female</label>
          <label><input type="radio" name="empSex" value="O">Others</label>
        </div>
      </div>
	  <br/>
      <div class="form-group">
        <label for="empCity">City:</label>
        <select id="empCity" name="empCity">
          <option value="">Select City</option>
          <option value="Bangalore">Bangalore</option>
          <option value="Delhi">Delhi</option>
          <option value="Mumbai">Mumbai</option>
          <option value="Kolkata">Kolkata</option>
        </select>
      </div>

      <div class="form-group">
        <label for="empJoiningDate">Joining Date:</label>
        <input type="date" id="empJoiningDate" name="empJoiningDate" value="2025-10-14">
      </div>
    </div>

    <div class="buttons">
      <button type="button" onclick="clearEmployeeForm()">Clear Employee</button>
      <button type="button" onclick="refreshEmployeeTable()">Refresh</button>
      <input  type="submit" class="input-btn" name="submit" value="Submit Employee"/>
    </div>
  </form>

  <div class="scroll-table">
    <table id="employeeTable">
	<tr>
		<th>EmpID</th>
        <th>Name</th>
        <th>Balance</th>
        <th>Type</th>
        <th>Sex</th>
        <th>City</th>
        <th>Joining Date</th>
        <th>Action</th>
	</tr>

	<%
         List<Employee> employees = (List<Employee>)request.getAttribute("employees");
    %>
    <%
    	for(Employee emp: employees){
    		
    		%>
    		<tr>
    			<td><% out.println(emp.getEmpId()); %></td>
    			<td><% out.println(emp.getFullName()); %></td>
    			<td><% out.println(emp.getBalance()); %></td>
    			<td><% out.println(emp.getEmpType()); %></td>
    			<td><% out.println(emp.getEmpSex()); %></td>
    			<td><% out.println(emp.getCity()); %></td>
    			<td><% out.println(emp.getJoiningDt()); %></td>
<%--     			<td><% out.println("DEL & UPDATE"); %></td>
 --%>    			 <td>
            	<input type="button" class="input-btn"  name="deleteBtn" value="DELETE"  onclick="deleteEmp(<%=emp.getEmpId()%>)"/>
            	<input type="button" class="input-btn"  name="updateBtn" value="UPDATE" onclick="updateEmp(<%=emp.getEmpId()%>)"/>
            </td>
    		</tr>
    		<% 
    	}
    
    %>
    
  	</table>
  	 </div>
</body>
</html>
