<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page import="model.*"%>

<!DOCTYPE html>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/css/mainbody.css" />

	<% Product p = (Product)session.getAttribute("product_info");
			if (request.getParameter("productID") != null && p != null) { %>
		
		<title>${product_info.name}</title>
		
	<% } else { %>
	
		<title>No product found</title>
	
	<% } %>

</head>


<body>

	<header>
		<jsp:include page="header.jsp"></jsp:include>
	</header>
	
	<hr style="margin-top: 4vh; margin-bottom: 1vw"/>

	<main>
	
		<% if (request.getParameter("productID") != null && p != null) { %>
		
			<div class="card-back" style="margin-top: 8vh;">
				<h2>${product_info.name}</h2>
				<h4>${product_info.description}</h4>
				<div class="img" style="height: 20vw; width: 20vw; margin: auto;">
					<img style="height: 20vw" alt="${product_info.name}" src="${product_info.img_src}">
				</div>
				<p> Giá: ${product_info.price} triệu </p>
			</div>
			
			<a href="info?action=addToCart&productID=${product_info.id}">
				<button type="submit" style="width: 12vw; height: 5vw; color: black; font-size: 1.2vw; background-color: red; margin-left: 45%;"><b>Thêm vào giỏ hàng</b></button>
			</a>
							
		<% } else { %>
			<b style="padding-left: 43.5vw; font-size: 1.5vw;"> No product found! </b>
		<% } %>
		
	</main>
	
	<hr style="margin-top: 4vh; margin-bottom: 4vh"/>
	
	<footer>
		<jsp:include page="footer.jsp"></jsp:include>
	</footer>

</body>

</html>