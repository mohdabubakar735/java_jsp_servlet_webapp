<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*" %>
<%@ page import="com.testweb.entities.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="menu.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>E-commerce Example - Users Page</title>
  <link rel="stylesheet" href="/testweb/css/design.css">
  <script type="text/javascript" src="/testweb/jscript/users.js"></script>
</head>
<body>

  <h2>Users Page</h2>

  <form name="usersFrm" action="/testweb/users" onsubmit="return validateUser()" method="post">
    <div class="form-section">
      <div class="form-group">
        <label for="username">Username:*</label>
        <input type="text" id="username" name="username" placeholder="Enter username" value="">
      </div>

      <div class="form-group">
        <label for="password">Password:*</label>
        <input type="password" id="password" name="password" placeholder="Enter password" value="">
      </div>

      <div class="form-group">
        <label for="email">Email:*</label>
        <input type="email" id="email" name="email" placeholder="Enter email" value="">
      </div>
   </div>
   
   <div class="form-section">
      <div class="form-group">
        <label for="role">Role:</label>
        <select id="role" name="role">
          <option value="">Select Role</option>
          <option value="Admin">Admin</option>
          <option value="Manager">Manager</option>
          <option value="Customer">Customer</option>
        </select>
      </div>

      <div class="form-group">
        <label for="isactive">Active Status:</label>
        <select id="isactive" name="isactive">
          <option value="true">Active</option>
          <option value="false">Inactive</option>
        </select>
      </div>
      
       <div class="form-group"></div>

    </div>

    <div class="buttons">
      <button type="button" onclick="clearUserForm()">Clear</button>
      <button type="button" onclick="refreshUserTable()">Refresh</button>
      <input type="submit" class="input-btn" name="submit" value="Submit User"/>
    </div>
  </form>

  <div class="scroll-table">
    <table id="userTable">
      <tr>
        <th>User ID</th>
        <th>Username</th>
        <th>Email</th>
        <th>Role</th>
        <th>Active</th>
        <th>Created Date</th>
        <th>Action</th>
      </tr>

      <%
      List<Users> userList = (List<Users>) request.getAttribute("users");
      %>
      <%
      if (userList != null) {
                for (Users u : userList) {
      %>
        <tr>
          <td><%= u.getUserid() %></td>
          <td><%= u.getUsername() %></td>
          <td><%= u.getEmail() %></td>
          <td><%= u.getRole() %></td>
          <td><%= u.getIsactive() ? "Active" : "Inactive" %></td>

          <td><fmt:formatDate value="<%= u.getCreatedat() %>" pattern="yyyy-MM-dd"/></td>
          <td>
            <input type="button" class="input-btn" value="DELETE" onclick="deleteUser(<%= u.getUserid() %>)"/>
            <input type="button" class="input-btn" value="UPDATE" onclick="updateUser(<%= u.getUserid() %>)"/>
          </td>
        </tr>
      <%
          }
        }
      %>
    </table>
  </div>

</body>
</html>
