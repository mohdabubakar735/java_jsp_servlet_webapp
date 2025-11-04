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
  <title>Order Page - E-commerce Example</title>
  <link rel="stylesheet" href="/testweb/css/design.css">
  <script type="text/javascript" src="/testweb/jscript/order.js">
  </script>
  
  <script type="text/javascript">
  
  	function validateForm() {
	  let amount = document.orderFrm.totalAmount.value;
	  if (amount == "") {
	    alert("Amount is mandatory.");
	    return false;
	  }
	  return true;
	}
  
  </script>
</head>


<body onload="defaultVaue()">

  <h2>Order Page</h2>
  
  <form name="orderFrm" action="/testweb/order" onsubmit="return validateForm()" method="post">
    <input type="hidden" name="orderId" value="" id="orderId"/>
    
    <div class="form-section">
      <div class="form-group">
        <label for="orderDate">Order Date:</label>
        <input type="date" id="orderDate" name="orderDate" value="2025-10-14">
      </div>

      <div class="form-group">
        <label for="totalAmount" class="required-label">Total Amount:</label>
        <input type="number" id="totalAmount" name="totalAmount" value="" step="0.01" required="required">
      </div>

      <div class="form-group">
        <label for="description">Description:</label>
        <textarea id="description" name="description" rows="2" placeholder="Enter description"></textarea>
      </div>
    </div>

    <div class="buttons">
      <button type="button" onclick="clearOrderForm()">Clear Order</button>
      <button type="button" onclick="refreshOrderTable()">Refresh</button>
      <input  type="submit" class="input-btn" name="submit" value="Submit Order"/>
    </div>
  </form>

  <div class="scroll-table">
    <table id="orderTable">
      <tr>
        <th>Order ID</th>
        <th>Order Date</th>
        <th>Total Amount</th>
        <th>Description</th>
        <th>Action</th>
      </tr>

    <%
      List<Order> orders = (List<Order>) request.getAttribute("orders");
      if (orders != null) {
        for (Order order : orders) {
    %>
          <tr>
            <td><%= order.getOrderId() %></td>
            <td><%= order.getOrderDate() %>
            	<%-- <  fmt : formatDate value="${order.orderDate}" pattern="dd-MM-yyyy" /> --%>
            </td>
            <td><%= order.getTotalAmount() %></td>
            <td><%= order.getDescription() %></td>
            <td>
            	<input type="button" class="input-btn" name="deleteBtn" value="DELETE"  onclick="deleteOrder(<%=order.getOrderId()%>)"/>
            	<input type="button" class="input-btn" name="updateBtn" value="UPDATE" onclick="updateOrder(<%=order.getOrderId()%>)"/>
            </td>
            
          </tr>
    <%
        }
      }
    %>

    </table>
  </div>

  <script>
    function clearOrderForm() {
      document.getElementById('orderDate').value = '';
      document.getElementById('totalAmount').value = '';
      document.getElementById('description').value = '';
    }

    function refreshOrderTable() {
      alert('This is just a demo refresh.');
    }
  </script>
</body>
</html>
