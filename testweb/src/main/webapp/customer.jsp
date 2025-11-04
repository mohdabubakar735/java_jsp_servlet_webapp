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
  <title>E-commerce Example - Customer Page</title>
  <link rel="stylesheet" href="/testweb/css/design.css">
  <script type="text/javascript" src="/testweb/jscript/customer.js"></script>
  
</head>
<body>

  <h2>Customer Page</h2>

  <form name="customerFrm" action="/testweb/customer" onsubmit="return validateForm()" method="post">
    <div class="form-section">
      <div class="form-group">
        <label for="custFirstName">First Name:*</label>
        <input type="text" id="custFirstName" name="custFirstName" placeholder="Enter first name" value="">
      </div>
       <div class="form-group">
        <label for="custLastName">Last Name:*</label>
        <input type="text" id="custLastName" name="custLastName" placeholder="Enter last name" value="">
      </div>

      <div class="form-group">
        <label for="custEmail">Email:*</label>
        <input type="email" id="custEmail" name="custEmail" placeholder="Enter email" value="">
      </div>
	
		<div class="form-group">
		  <label for="custAddress">Address:</label>
		  <input type="text" id="custAddress" name="custAddress" placeholder="Enter address" value="">
		</div>

		<div class="form-group">
		  <label for="custAge">Age:</label>
		  <input type="number" id="custAge" name="custAge" placeholder="Enter age" value="">
		</div>
		
		</div>
		<div class="form-section">
		<br/>
		
		<div class="form-group">
		  <label for="custType">Customer Type:</label>
		  <select id="custType" name="custType">
		    <option value="">Select Type</option>
		    <option value="Regular">Regular</option>
		    <option value="Premium">Premium</option>
		    <option value="VIP">VIP</option>
		  </select>
		</div>
			
	
      <div class="form-group">
        <label for="custPhone">Phone:</label>
        <input type="text" id="custPhone" name="custPhone" placeholder="Enter phone number" value="">
      </div>

      <div class="form-group">
        <label for="custBalance">Balance:</label>
        <input type="number" id="custBalance" name="custBalance" placeholder="Enter balance" value="">
      </div>

      <div class="form-group">
        <label>Sex:</label>
        <div class="radio-group">
          <label><input type="radio" name="custSex" value="M" checked="checked">Male</label>
          <label><input type="radio" name="custSex" value="F">Female</label>
          <label><input type="radio" name="custSex" value="O">Others</label>
        </div>
      </div>
      <br/>

      <div class="form-group">
        <label for="custCity">City:</label>
        <select id="custCity" name="custCity">
          <option value="">Select City</option>
          <option value="Bangalore">Bangalore</option>
          <option value="Delhi">Delhi</option>
          <option value="Mumbai">Mumbai</option>
          <option value="Kolkata">Kolkata</option>
        </select>
      </div>

      <div class="form-group">
        <label for="custJoinDate">Join Date:</label>
        <input type="date" id="custJoinDate" name="custJoinDate" value="2025-10-14">
      </div>
    </div>

    <div class="buttons">
      <button type="button" onclick="clearCustomerForm()">Clear Customer</button>
      <button type="button" onclick="refreshCustomerTable()">Refresh</button>
      <input  type="submit" class="input-btn" name="submit" value="Submit Customer"/>
    </div>
  </form>

  <div class="scroll-table">
    <table id="customerTable">
      <tr>
        <th>CustID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Balance</th>
        <th>Sex</th>
        <th>City</th>
        <th>Join Date</th>
        <th>Action</th>
      </tr>

      <%
        List<Customer> customers = (List<Customer>)request.getAttribute("customers");
      %>
      <%
        if (customers != null) {
          for(Customer cust : customers) {
      %>
	        <tr>
				  <td><%= cust.getCustomerId() %></td>
				  <td><%= cust.getCustomerFirstName() + " " + cust.getCustomerLastName() %></td>
				  <td><%= cust.getCustomerEmail() %></td>
				  <td><%= cust.getCustomerPhoneNumber() %></td>
				  <td><%= cust.getCustBalance() %></td>
				  <td><%= cust.getCustSex() %></td>
				  <td><%= cust.getCity() %></td>
				  <td><%= cust.getJoinDate() %></td>
				  <td>
			    <input type="button" class="input-btn" value="DELETE" onclick="deleteCustomer(<%= cust.getCustomerId() %>)"/>
			    <input type="button" class="input-btn" value="UPDATE" onclick="updateCustomer(<%= cust.getCustomerId() %>)"/>
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
