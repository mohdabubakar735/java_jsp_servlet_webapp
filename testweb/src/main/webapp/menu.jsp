<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	 <link rel="stylesheet" href="/testweb/css/design.css">
	<style type="text/css">
	
	 .link-btn{
		 text-decoration: none; 
		 padding: 5px 20px; 
		 background-color: #4CAF50; 
		 color: white; 
		 font-size: 18px; 
		 font-weight: 600; 
		 border-radius: 4px; 
		 margin: 10px; 
		 display: inline-block; 
		 transition: background-color 0.3s, transform 0.3s;
	 }
	 
	 .link-btn:hover {
	    background-color: #4CAF50 !important;
	    transform: scale(1.1);
	}
	</style>
	
	<script type="text/javascript">
		
		function logoutPage() {
	
		    const xhr = new XMLHttpRequest();
		    xhr.open("GET", "/testweb/login", true);
		    xhr.setRequestHeader("Content-Type", "text/html");
		    console.log(xhr);
	
		    xhr.onload = function() {
		        if (xhr.status === 200) {
		            console.log("Redirecting to users edit page...");
		            window.location.href = "/testweb/index.jsp";
		        } else {
		            console.error("HTTP request failed with status: " + xhr.status);
		        }
		    };
	
		    xhr.onerror = function() {
		        console.error("Network error occurred.");
		    };
	
		    xhr.send();
		}
	
	</script>
	
</head>
<body>

  <div class="form-section">

	<a class="link-btn" href="/testweb/main.jsp">
         Home
     </a>
     
     
     <form action="/testweb/order" method="get">
     	<input type="submit" class="link-btn" name="order-btn" value="Order">
     </form>
     
     <form action="/testweb/customer" method="get">
     	<input type="submit" class="link-btn" name="cust" value="Customer">
     </form>

     <form action="/testweb/product" method="get">
     	<input type="submit" class="link-btn" name="prod" value="Product">
     </form>

     <form action="/testweb/employee" method="get">
       <input type="submit" class="link-btn" name="emp" value="Employee">
     </form>
    
     <form action="/testweb/users" method="get">
     	<input type="submit" class="link-btn" name="users" value="User">
     </form>
            
      <div><input class="link-btn" type="button" onclick="logoutPage()" name="logout" value="Logout" ></div>
   </div>

</body>
</html>