<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page import="model.*"%>

<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="${pageContext.servletContext.contextPath}/assets/css/mainbody.css" />
<title></title>
</head>

<body>
	
	<sql:setDataSource var="ds" driver="com.mysql.jdbc.Driver" user="Phaust" password="The Next SQL 2004" url="jdbc:mysql://localhost:3306/shoppingdb" scope="page" />
	<sql:query var="products" dataSource="${ds}" sql="select * from products order by product_id;"></sql:query>
	
	<div class="main">
	
		<div class="left-column">
			
			<div class="card-back">
				<!-- Quảng cáo -->
				<div class="img">
					<p>Banner</p>
				</div>
				<div class="img">
					<p>Banner</p>
				</div>
				<div class="img">
					<p>Banner</p>
				</div>
			</div>
			
		</div>

		<div class="mid-column">
		
		<c:forEach var="product" items="${products.rows}" varStatus="row">

			<a href="info?action=info&productID=${product.product_id}">
				<div class="card-back">
					<h2>${product.product_name}</h2>
					<h4>${product.product_des}</h4>
					<div class="img" style="height: 20vw; width: 20vw; margin: auto;">
						<img style="height: 20vw" alt="${product.product_name}" src="${product.product_img_source}">
					</div>
					<p> Giá: ${product.product_price} triệu </p>
				</div>
			</a>
			
			</c:forEach>

		</div>

		<div class="right-column">

			<div class="card-back">
								
				<% if (session.getAttribute("user") != null) { %>
				
					<h3>Giỏ hàng của ${user}:</h3>
					
					<% if (session.getAttribute("cart") != null) {
						Cart a = (Cart)session.getAttribute("cart");
						if (!a.isEmpty()) { %>
							
							<table style="border-collapse: collapse;">
								<c:forEach items="${cart.items}" var="product">
									<tr>
										<td width="45%"><p> ${product.name} </p></td>
										<td><p> ${product.price} </p></td>
										<td><p> ${product.numbersOfProduct} cái</p></td>
									</tr>
								</c:forEach>
							</table>
						
					
					<% } else { %>
					
						<b>Không có sản phẩm nào!</b>
						<br/>
						
					<% } %>
						
					<% } else { %>
					
						<b>Không có sản phẩm nào!</b>
						<br/>
					
					<% } %>
					
					<a href="${pageContext.servletContext.contextPath}/user_cart"><button>Đi tới giỏ hàng của bạn</button></a>
				
				<% } else { %>
				
					<h3>Hãy đăng nhập để dùng giỏ hàng!</h3>
				
				<% } %>
				
			</div>

		</div>

	</div>

</body>

</html>