<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.testweb.entities.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit User Page - E-commerce Example</title>
    <link rel="stylesheet" href="/testweb/css/design.css">
    <script type="text/javascript" src="/testweb/jscript/users.js"></script>
</head>

<body>
<%
Users user = (Users) session.getAttribute("users");
%>

<h2>Edit User Page</h2>

<form name="userFrm" action="/testweb/users" onsubmit="return validateUser()" method="post">

    <!-- Hidden User ID -->
    <input type="hidden" name="userid" id="userid" value="<%= user.getUserid() %>">

    <div class="form-section">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" value="<%= user.getUsername() %>" disabled="disabled">
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" value="<%= user.getPassword() %>" required>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="<%= user.getEmail() %>" required>
        </div>
	</div>
	<div class="form-section">
        <div class="form-group">
            <label for="role">Role:</label>
            <select id="role" name="role">
                <option value="">Select Role</option>
                <option value="Admin" <%= "Admin".equals(user.getRole()) ? "selected" : "" %>>Admin</option>
                <option value="Manager" <%= "Manager".equals(user.getRole()) ? "selected" : "" %>>Manager</option>
                <option value="Customer" <%= "Customer".equals(user.getRole()) ? "selected" : "" %>>Customer</option>
            </select>
        </div>

        <div class="form-group">
            <label for="isactive">Active Status:</label>
            <select id="isactive" name="isactive">
                <option value="true" <%= user.getIsactive() ? "selected" : "" %>>Active</option>
                <option value="false" <%= !user.getIsactive() ? "selected" : "" %>>Inactive</option>
            </select>
        </div>
        
 		<div class="form-group"></div>
    </div>

    <div class="buttons">
        <input type="submit" name="submit" value="Update User"/>
    </div>

</form>
</body>
</html>
