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
  <title>Product Page - E-commerce Example</title>
  <link rel="stylesheet" href="/testweb/css/design.css">
  <script type="text/javascript" src="/testweb/jscript/product.js"></script>
</head>

<body>

  <h2>Product Page</h2>

  <form name="productFrm" action="/testweb/product" method="post">
    <input type="hidden" name="productId" value="" id="productId"/>
    <div class="form-section">
      <div class="form-group">
        <label for="productName">Product Name:</label>
        <input type="text" id="productName" name="productName" maxlength="99" placeholder="Enter product name" required>
      </div>

      <div class="form-group">
        <label for="price">Price:</label>
        <input type="number" id="price" name="price" min="1" max="9999999" placeholder="Enter price" step="0.01" required>
      </div>

      <div class="form-group">
        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" min="1" max="9999" placeholder="Enter quantity" required>
      </div>

      <div class="form-group">
        <label for="description">Description:</label>
        <textarea id="description" name="description" rows="2" maxlength="200" placeholder="Enter product description"></textarea>
      </div>
    </div>

    <div class="buttons">
      <button type="button" onclick="clearProductForm()">Clear Product</button>
      <button type="button" onclick="refreshProductTable()">Refresh</button>
      <input  type="submit" class="input-btn" name="submit" value="Submit Product"/>
    </div>
  </form>

  <div class="scroll-table">
    <table id="productTable">
      <thead>
        <tr>
          <th>Product ID</th>
          <th>Product Name</th>
          <th>Price</th>
          <th>Quantity</th>
          <th>Description</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <%
          List<Product> products = (List<Product>) request.getAttribute("products");
          if (products != null) {
            for (Product product : products) {
        %>
          <tr>
            <td><%= product.getProductId() %></td>
            <td><%= product.getProductName() %></td>
            <td><%= product.getPrice() %></td>
            <td><%= product.getQuantity() %></td>
            <td><%= product.getDescription() %></td>
        
            <%--     			<td><% out.println("DEL & UPDATE"); %></td>
 --%>    	 <td>
            	<input type="button" class="input-btn" name="deleteBtn" value="DELETE"  onclick="deleteProduct(<%=product.getProductId()%>)"/>
            	<input type="button" class="input-btn" name="updateBtn" value="UPDATE" onclick="updateProductNew(<%=product.getProductId()%>)"/>
            </td>
          </tr>
        <%
            }
          }
        %>
      </tbody>
    </table>
  </div>

  <script>
    function clearProductForm() {
      document.getElementById('productName').value = '';
      document.getElementById('price').value = '';
      document.getElementById('quantity').value = '';
      document.getElementById('description').value = '';
    }


    function refreshProductTable() {
      alert('This is just a demo refresh.');
    }



  </script>

</body>
</html>
