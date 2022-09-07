<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/css/mainbody.css" />
<title>Search results</title>

</head>


<body>
	
	<header>
		<jsp:include page="header.jsp"></jsp:include>
	</header>
	
	<main>
	
		<% if (session.getAttribute("productResult") != null) {
			
			List<Product> a = (List<Product>)session.getAttribute("productResult");
			
			if (!a.isEmpty()) { %>
		
				<div class="mid-column" style="padding-left: 15vw; float: none;">
					<c:forEach items="${productResult}" var="product">
						
						<a href="info?action=info&productID=${product.id}">
						
							<div class="card-back">
								<h2>${product.name}</h2>
								<h4>${product.description}</h4>
								<div class="img" style="height: 20vw; width: 20vw; margin: auto;">
									<img style="height: 20vw" alt="${product.name}" src="${product.img_src}">
								</div>
								<p> Giá: ${product.price} triệu </p>
							</div>
						
						</a>
						
					</c:forEach>
				</div>
		
		<% } else { %> 
			
			<% if (session.getAttribute("nullMess") != null) { %>
				<h4>${nullMess}</h4>
			<% } else { %>
				<h4>Không tìm thấy sản phẩm nào</h4>
			<% } %>
			
		<% } } else { %>
						
			<% if (session.getAttribute("nullMess") != null) { %>
				<h4>${nullMess}</h4>
			<% } else { %>
				<h4>Không tìm thấy sản phẩm nào</h4>
			<% } %>
			
		<% } %>
		
	</main>
	
	<hr/>
	
	<footer>
		<jsp:include page="footer.jsp"></jsp:include>
	</footer>
	
</body>

</html>