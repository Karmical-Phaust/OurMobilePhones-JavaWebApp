<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE html>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="assets/css/admin.css">
<title> Admin page </title>

</head>


<body>

<% if (session.getAttribute("user") != null) { %>

<div id="header">
<strong> Manager section: </strong>
</div>

<br/>

<p> Welcome <%= session.getAttribute("user") %>!</p>

<div style="text-align: center; color: black; font-size: 2.4vw;" >
	<a href="/PRJ-321x_A3/our_mobile_phones_homepage">To homepage</a>
</div>

<br/>
<p/>

<form action="logout" method="post">
<button type="submit"> Log out </button>
</form>

<hr/>

<jsp:include page="/footer.jsp"></jsp:include>

<% } else { %>

<p> Failed to login!! </p>

<% } %>

</body>

</html>