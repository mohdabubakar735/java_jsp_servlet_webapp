<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.testweb.entities.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Product Page - E-commerce Example</title>
    <link rel="stylesheet" href="/testweb/css/design.css">
    <script type="text/javascript" src="/testweb/jscript/product.js"></script>
</head>

<body>
<%
    Product product = (Product) session.getAttribute("product");
%>

<h2>Edit Product Page</h2>

<form name="productFrm" action="/testweb/product" method="post">

    <!-- Hidden Product ID -->
    <input type="hidden" name="productId" id="productId" value="<%= product.getProductId() %>">

    <div class="form-section">
        <div class="form-group">
            <label for="productName">Product Name:</label>
            <input type="text" id="productName" name="productName" value="<%= product.getProductName() %>" required>
        </div>

        <div class="form-group">
            <label for="price">Price:</label>
            <input type="number" id="price" name="price" step="0.01" value="<%= product.getPrice() %>" required>
        </div>

        <div class="form-group">
            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" value="<%= product.getQuantity() %>" required>
        </div>

        <div class="form-group">
            <label for="description">Description:</label>
            <textarea id="description" name="description" rows="2"><%= product.getDescription() %></textarea>
        </div>
    </div>

    <div class="buttons">
        <input type="submit" name="submit" value="Update Product"/>
    </div>

</form>
</body>
</html>
