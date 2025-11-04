<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@include file="menu.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Registration</title>
    <link rel="stylesheet" href="css/hamdard.css">

</head>
<body>

<div class="container">
    <h1>Student Registration</h1>
    <p>Welcome to the student registration page.<br>
   <center> <img src="https://upload.wikimedia.org/wikipedia/en/thumb/4/4d/Jamia_Hamdard_Logo.svg/800px-Jamia_Hamdard_Logo.png"></center>
	<p>    Please fill out the form below and check your timetable.</p> 

    <hr>

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
</div>

<!-- Success Popup -->
<div id="popup" class="overlay">
    <div class="popup">
        <h2>Registration Successful!</h2>
        <p id="popupContent"></p> <!-- Added for dynamic content -->
        <a href="#">Close</a>
    </div>	
</div>

<!-- Error Popup -->
<div id="errorPopup" class="overlay">
    <div class="popup" style="border:2px solid red;">
        <h2 style="color:red;">Error!</h2>
        <p>Please complete the form.</p>
        <a href="#">Close</a>
    </div>
</div>

<script>
document.getElementById("registrationForm").addEventListener("submit", function(event) {
    event.preventDefault();

    let name = document.getElementById("name").value.trim();
    let email = document.getElementById("email").value.trim();
    let course = document.getElementById("course").value.trim();
    let hobbies = Array.from(document.querySelectorAll('input[name="hobbies"]:checked')).map(h => h.value);

    if (name === "" || email === "" || course === "" || hobbies.length === 0) {
        window.location.hash = "errorPopup";
    } else {
        document.getElementById("popupContent").innerHTML =
            `<strong>Name:</strong> ${name} <br>
             <strong>Email:</strong> ${email} <br>
             <strong>Hobbies:</strong> ${hobbies.join(", ")} <br>
             <strong>Course:</strong> ${course}`;
        window.location.hash = "popup";
    }
	document.getElementById("popupContent").innerHTML =
    `<strong>Name:</strong> ${name} <br>
     <strong>Email:</strong> ${email} <br>
     <strong>Hobbies:</strong> ${hobbies.join(", ")} <br>
     <strong>Course:</strong> ${course} <br><br>
     <em style="color:green; font-weight:bold;">Your classes will start soon!</em>`;
});
</script>

</body>
</html>
