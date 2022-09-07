<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/css/form.css"/>
<title>Sign up page</title>

</head>


<body>

	<header>
		
		<img src="assets/imgs/adminlogin.jpg"/>
		
	</header>

	<main>
		<form method="post" action="register">
		
	        <label for="email"> Your email: </label>
	        <input type="text" name="user_email" id="email" placeholder="Please enter your email"><br/><br/>
	        
	        <label for="pwd"> Your password: </label>
	        <input type="text" name="password" id="pwd" placeholder="Please enter your password"><br/><br/>
	        
	        <label for="uname"> Your username: </label>
	        <input type="text" name="username" id="uname" placeholder="Please enter your username"><br/><br/>
	        
	        <label for="adrs"> Your address: </label>
	        <input type="text" name="address" id="adrs" placeholder="Please enter your address"><br/><br/>
	        
	        <label for="phone"> Your phone number: </label>
	        <input type="text" name="phone_num" id="phone" placeholder="Please enter your phone number"><br/><br/>
	        
		        <% if (session.getAttribute("error") != null) { %>
					<p style="margin-left: 24%; color: red; font-size: 1.75vw"> <%= session.getAttribute("error") %> </p>
				<% } %>
			
	        <input type="submit" id="submit" value="Sign up" onclick="checkname();">
	    </form>
	</main>
	
	<footer>
    	<script src="assets/js/register.js"></script>
    </footer>

</body>

</html>