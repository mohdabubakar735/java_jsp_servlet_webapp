<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.*" %>
<%@ page import="com.testweb.entities.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Edit Order Page - E-commerce Example</title>
  <link rel="stylesheet" href="/testweb/css/design.css">
  <script type="text/javascript" src="/testweb/jscript/order.js">
  </script>
  
  <script type="text/javascript"></script>
</head>

<body>

	<%
	   Order order =(Order)session.getAttribute("order");
	   String orderDate = (String)session.getAttribute("orderDate");
	%>
  <h2>Edit Order Page</h2>
  
  <form name="orderFrm" action="/testweb/order" method="post">
  
    <input type="hidden" name="orderId"  id="orderId" value="<%= order.getOrderId() %>" >
    
    <div class="form-section">
      <div class="form-group">
        <label for="orderDate">Order Date:</label>
        <input type="date" id="orderDate" name="orderDate" value="<%= orderDate %>">
      </div>

      <div class="form-group">
        <label for="totalAmount">Total Amount:</label>
        <input type="number" id="totalAmount" name="totalAmount" value="<%=order.getTotalAmount()%>" >
        
      </div>

      <div class="form-group">
        <label for="description">Description:</label>
        <textarea id="description" name="description" rows="2"><%= order.getDescription() %></textarea>
      </div>
    </div>

    <div class="buttons">
      <input type="submit" name="submit" value="Submit Order"/>
    </div>
  </form>

</body>
</html>