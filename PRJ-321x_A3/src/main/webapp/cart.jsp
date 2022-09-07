<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.*"%>

<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/css/cart.css">

<% if (session.getAttribute("user") != null) { %>

<title>${user}'s cart</title>

<% } else { %>

<title>Please login to use cart!</title>

<% } %>

</head>


<body>

<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>

<hr style="margin-top: 4vh; margin-bottom: 1vw"/>

<main>

	<% if (session.getAttribute("user") != null) { %>
	
		<% if (session.getAttribute("product_info") != null && (session.getAttribute("cart") != null)) { %>
			
			<% Cart a = (Cart)session.getAttribute("cart");
				if (!a.isEmpty()) { %>
				
				<table><tbody>
					
					<tr>
						<th width="16%"><p>Product name:</p></th>
						<th width="42%"><p>Description:</p></th>
						<th><p>Price:</p></th>
						<th><p>Quantity:</p></th>
					</tr>
					
					<c:forEach items="${cart.items}" var="product">
					
						<tr>

							<td width="16%"><p> ${product.name} </p></td>
							<td width="42%"><p> ${product.description} </p></td>
							<td><p> ${product.price} </p></td>
							<td><p> ${product.numbersOfProduct} </p></td>
																		
							<td class="btn"><form action="cart_controller" method="post">
								<input type="hidden" name="p_id" value="${product.id}">
								<input type="hidden" name="job" value="add">
								<button type="submit" style="font-size: 0.95vw;">Thêm sản phẩm</button>
							</form></td>
							
							<td class="btn"><form action="cart_controller" method="post">
								<input type="hidden" name="p_id" value="${product.id}">
								<input type="hidden" name="job" value="remove">
								<button type="submit" style="font-size: 0.95vw;">Xóa sản phẩm</button>
							</form></td>
							
						</tr>
						
					</c:forEach>
					
				</tbody></table>
					
				<form action="cart_controller" method="post">
						<input type="hidden" name="job" value="removeAll">
						<button type="submit" style="margin-top: 1.2vw; float: left; font-size: 1vw;">Xóa tất cả sản phẩm</button>
				</form>
				
				<br/>
				<br/>
				<br/>
				
				<form action="pay_controller" method="post" style="font-size: 1.4vw;">
						<label for="coupon"> Discount code here: 
						<input type="text" name="coupon" id="coupon" style="height: 1.5vw; width: 20vw; font-size: 1.2vw;">
						</label>
						<button type="submit" style="margin-top: 1.2vw; margin-left: 4vw; font-size: 1vw;">Thanh toán</button>
				</form>
			
			<% } else { %>
			
				<b>Không có sản phẩm nào!</b>
				
			<% } %>
			
		<% } else { %>
		
			<b>Không có sản phẩm nào!</b>
		
		<% } %>
	
	<% } else { %>
	
		<b style="text-align: center; font-size: 1.4vw;">Hãy đăng nhập để lưu đơn hàng và thanh toán!</b>
	
	<% } %>
	
</main>

<hr style="margin-top: 4vh; margin-bottom: 4vh"/>

<footer>
	<jsp:include page="footer.jsp"></jsp:include>
</footer>

</body>

</html>