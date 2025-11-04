<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@include file="menu.jsp" %>
 
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Web App</title>
	<link rel="stylesheet" href="/testweb/css/design.css">
	
	
</head>
<body>
  


    <h2>Registration Form</h2>
    <form id="registrationForm">
        <div class="form-group">
            <label for="name">Full Name:</label>
            <input type="text" id="name" placeholder="Enter your name">
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" placeholder="Enter your email">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" placeholder="Enter password">
        </div>
        <div class="form-group">
            <label>Gender:</label>
            <input type="radio" name="gender" value="male"> Male
            <input type="radio" name="gender" value="female"> Female
        </div>
        <div class="form-group">
            <label>Hobbies:</label>
            <input type="checkbox" name="hobbies" value="reading"> Reading
            <input type="checkbox" name="hobbies" value="sports"> Sports
            <input type="checkbox" name="hobbies" value="music"> Music
        </div>
        <div class="form-group">
            <label for="course">Select Course:</label>
            <select id="course">
                <option value="" disabled selected>-- Select --</option>
                <option>BCA</option>
                <option>BBA</option>
                <option>BSc</option>
            </select>
        </div>
        <button type="submit">Register</button>
    </form>

    <hr>

    <h2>Class Timetable</h2>
    <div class="timetable-container">
        <table>
            <tr>
                <th>Day</th>
                <th>9:00AM-10:00AM</th>
                <th>10:00 AM - 11:00 AM</th>
                <th>11:00 AM - 12:00 PM</th>
            </tr>
            <tr>
                <td>Monday</td>
                <td>Math</td>
                <td>English</td>
                <td>Science</td>
            </tr>
            <tr>
                <td>Tuesday</td>
                <td>Physics</td>
                <td>Math</td>
                <td>Computer</td>
            </tr>
        </table>
    </div>





</body>
</html>