<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.testweb.entities.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Edit Customer Page - E-commerce Example</title>
	<link rel="stylesheet" href="/testweb/css/design.css">
	<script type="text/javascript" src="/testweb/jscript/customer.js">
	</script>
	  <script type="text/javascript"></script>
	
</head>

<body>

	<%
		//  customer details
		Customer customer = (Customer) session.getAttribute("customer");
	%>

	<h2>Edit Customer Page</h2>

	<form name="customerFrm" action="/testweb/customer" method="post">

		<!-- Hidden Customer ID -->
		<input type="hidden" name="customerId" id="customerId" value="<%=customer.getCustomerId()%>">

		<div class="form-section">

			<div class="form-group">
				<label for="custFirstName">First Name:</label> <input
					type="text" id="custFirstName" name="custFirstName"
					value="<%=customer.getCustomerFirstName()%>" required>
			</div>

			<div class="form-group">
				<label for="custLastName">Last Name:</label> <input type="text"
					id="custLastName" name="custLastName"
					value="<%=customer.getCustomerLastName()%>" required>
			</div>

			<div class="form-group">
				<label for="custEmail">Email:</label> <input type="email"
					id="custEmail" name="custEmail"
					value="<%=customer.getCustomerEmail()%>">

			</div>

			<div class="form-group">
				<label for="custPhone">Phone:</label> 
				<input type="text" id="custPhone" name="custPhone"
					value="<%=customer.getCustomerPhoneNumber()%>">
			</div>
		</div>
		<div class="form-section">
		
			<div class="form-group">
				<label for="custBalance">Balance:</label> 
				<input type="number" id="custBalance" name="custBalance" step="0.01"
					value="<%=customer.getCustBalance()%>">

			</div>

			<div class="form-group">
				<label>Sex:</label>
				<div class="radio-group">
					<label>
					<input type="radio" name="custSex" value="M"
						<%="M".equals(customer.getCustSex()) ? "checked" : ""%>>
						Male</label> <label>
					<input type="radio" name="custSex" value="F"
						<%="F".equals(customer.getCustSex()) ? "checked" : ""%>>
						Female</label> <label>
					<input type="radio" name="custSex" value="O"
						<%="O".equals(customer.getCustSex()) ? "checked" : ""%>>
						Others</label>
				</div>
			</div>

			<div class="form-group">
				<label for="custCity">City:</label> <select id="custCity"
					name="custCity">
					<option value="">Select City</option>
					<option value="Bangalore"
						<%="Bangalore".equals(customer.getCity()) ? "selected" : ""%>>Bangalore</option>
					<option value="Delhi"
						<%="Delhi".equals(customer.getCity()) ? "selected" : ""%>>Delhi</option>
					<option value="Mumbai"
						<%="Mumbai".equals(customer.getCity()) ? "selected" : ""%>>Mumbai</option>
					<option value="Kolkata"
						<%="Kolkata".equals(customer.getCity()) ? "selected" : ""%>>Kolkata</option>
				</select>
			</div>

			<div class="form-group">
				<label for="custJoinDate">Join Date:</label> <input type="date"
					id="custJoinDate" name="custJoinDate"
					value="<%=customer.getJoinDate() != null ? customer.getJoinDate() : ""%>">
			</div>
		</div>

		<div class="buttons">
			<input type="submit" name="submit" value="Submit Customer" />
		</div>
	</form>

</body>
</html>
