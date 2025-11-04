<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Web App</title>
	 	<link rel="stylesheet" href="/testweb/css/design.css">
	 <style>
		.input-btn {
		    text-decoration: none;
		    padding: 1px 3px;
		    background-color: #999999 !important;
		    color: white !important;
		    font-size: 18px;
		    font-weight: 600;
		    border: none;
		    border-radius: 4px;
		    margin: 10px;
		    cursor: pointer;
		    display: inline-block;
		    transition: background-color 0.3s, transform 0.3s;
		}
		
		.input-btn:hover {
		    background-color: #333 !important;
		    transform: scale(1.1);
		}
	</style>
	<script type="text/javascript">
	function validateLogin(){
		
		var user= document.loginFrm.username.value;
		var pass=document.loginFrm.password.value;
		
		if((user === "") || ( pass === "") ) {
			alert('Enter username & password.');
			return false;
		}
		return true;
	}
	</script>
</head>
<body style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f8f8f8; margin: 0; padding: 0; color: #333;">

  
	<header style="background-color: #ffffff; padding: 20px 0; border-bottom: 1px solid #ddd; text-align: center; padding:0">
	    <div style="display: flex; align-items: center; justify-content: center; gap: 12px;">
	        <img src="webapp2.png" alt="WebApp Logo" width="90" height="90" style="border-radius: 50%; padding:0">
	        <h1 style="font-size: 36px; margin: 0; color: #4A4A4A;">Welcome to My Professional Web App</h1>
	    </div>
	</header>


    <main style="max-width: 1200px; margin: 30px auto; padding: 0 15px;">
        <div style="text-align: center; margin-top: 50px;">
        
        	 <div class="form-group">
			<%
			  if( null != request.getAttribute("error") && !request.getAttribute("error").equals("") ){
		      	String errorMsg = (String) request.getAttribute("error");
		      	out.println(errorMsg);
			  }
		    %>
		    </div>
		    
           <form name="loginFrm" action="/testweb/login" onsubmit="return validateLogin()" method="post">
           
            <div class="form-section">
		      <div class="form-group">
		        <label for="username">Username:*</label>
		        <input type="text" id="username" name="username" placeholder="Enter username" value="">
		      </div>
			</div>
			<div class="form-section">
		      <div class="form-group">
		        <label for="password">Password:*</label>
		        <input type="password" id="password" name="password" placeholder="Enter password" value="">
		      </div>
		
		   </div>
  			 <div class="form-section">
	            <input type="submit" class="input-btn" name="login" value="Login">
	          </div>
           </form>
           
        </div>
    </main>

    
</body>
</html>