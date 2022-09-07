<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.*"%>
<%@ page import="dao.*"%>
<%@ page import="context.*"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/css/cart.css">
<title>Order and payment</title>
</head>


<body>
	
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>

<hr style="margin-top: 4vh; margin-bottom: 1vw"/>
<% JDBC_Access conn = new JDBC_Access(); Connection cnt = conn.getConn(); 
	OrderDAO odao = new OrderDAO(cnt); odao.showOrder(session); ListProduct pid = new ListProduct(cnt);%>

<main>

	<% if (session.getAttribute("user_orders") != null) {
			ArrayList<Orders> UserOrders = (ArrayList<Orders>)session.getAttribute("user_orders");
			if (!UserOrders.isEmpty()) { %>
		
		<c:forEach items="${user_orders}" var="order">
			<b>Order No.${order.orderId}</b>
			<table><tbody>
						
						<tr>
							<th width="72%"><p>Product:</p></th>
							<th width="12%"><p>Quantity:</p></th>
							<th width="12%"><p>Price:</p></th>
						</tr>
							
						<c:forEach items="${order.listOfProducts}" var="product">
						
							<c:set value="${product.productId}" var="tid"/>
								<% int c = (int)pageContext.getAttribute("tid");
									Product prdt = pid.getProduct(c); 
									session.setAttribute("tmpPrd", prdt); %>
							
							<tr>
									
								<td width="72%"><img alt="Product number ${tmpPrd.id}" src="${tmpPrd.img_src}" style="width: 16vw; float: left; margin: 3vw;"> 
										<p style="margin-top: 10%"><c:out value="${tmpPrd.name}"/></p>
										<p> <c:out value="${tmpPrd.description}"/> </p></td>
								<td width="12%"><c:out value="${product.amount}"/></td>
								<td width="12%"><c:out value="${product.price}"/></td>
								
							</tr>
							
							
						</c:forEach>
						
					</tbody></table>
					<br/>
					<br/>
			</c:forEach>
		
	<% } else { %>
		<p>Hiện bạn không có đơn hàng nào</p>
	<% } } else { %>
		<p>Hiện bạn không có đơn hàng nào</p>
	<% } %>
	
</main>

<hr style="margin-top: 4vh; margin-bottom: 4vh"/>

<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>
	
</body>

</html>