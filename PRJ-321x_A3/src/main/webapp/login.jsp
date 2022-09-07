<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/css/form.css"/>
<title> Login </title>
</head>

<body>

	<header>
		
		<img src="assets/imgs/adminlogin.jpg"/>
		
	</header>

	<main>
		
		<% if (session.getAttribute("error") != null) { %>
		<p style="margin-left: 24%; color: red; font-size: 1.75vw"> <%= session.getAttribute("error") %> </p>
		<% } %>
		
		<% if (session.getAttribute("successAlert") != null) { %>
		<p style="margin-left: 24%; color: blue; font-size: 1.75vw"> <%= session.getAttribute("successAlert") %> </p>
		<% } %>
	
		<form method="post" action="login">
		
	        <label for="email"> Email: 
	        <input type="text" name="user_email" id="email" placeholder="Please enter your email">
	        </label><br/>
	        
	        <label for="pwd"> Password: 
	        <input type="text" name="password" id="pwd" placeholder="Please enter your password">
	        </label><br/>
	        
            <label for="remember">
            <input type="checkbox" id="remember" value="Remember me">
             Remember me 
            </label><br/>
            
	        <input type="submit" id="submit" value="Log in" onclick="checkname();">
	        
	    </form>
	    
	    <br/>
	    
	   	<div style="text-align: center;">
	   	
	    	<b style="float:none; font-size: 1.6vw">Or if you hadn't had an account:</b>
	    
	    </div>
	    
	    <br/>
	    
	    <div style="text-align: center;">
	    
	    	<form action="${pageContext.servletContext.contextPath}/register_page" method="post" style="margin-left: 1%">
					<button type="submit">Sign up</button>
			</form>
	    	
	    </div>
    </main>
    
    <footer>
    	<script src="assets/js/login.js"></script>
    </footer>
    
</body>

</html>