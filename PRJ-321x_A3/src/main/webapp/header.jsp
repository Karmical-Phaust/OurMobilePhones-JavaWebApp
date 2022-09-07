<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8; width=device-width, initial-scale=1" name="viewport">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/css/header.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title></title>

</head>

<body>

	<div id="index">

		<h1>
			Welcome to <i> Our EDs - Electronic Devices </i>
		</h1>

		<div class="navbar" id="repnav">
			<a href="${pageContext.servletContext.contextPath}/our_mobile_phones_homepage"> Home </a> 
			<% if (session.getAttribute("user") != null) { %>
			<a href="${pageContext.servletContext.contextPath}/user_orders"> Your Order </a>
			<% } %>
			<div class="section">
				<button class="sb">Products</button>
				<div class="content">
					<a href=""> PCs </a> <a href=""> Laptops </a> <a href="">
						Smartphones </a>
				</div>
			</div>
			<div class="section">
				<button class="sb">Others</button>
				<div class="content">
					<a href=""> Contact us! </a> <a href=""> Join our membership! </a>
				</div>
			</div>
			
			<div id="searchSection">
				<form method="post" action="search">
					<input id="searchBar" type="search" name="name" placeholder="Tìm sản phẩm...">
					<button id="searchIcon" type="submit"><i class="fa fa-search"></i></button>
				</form>
			</div>
			
			<% if (session.getAttribute("user") == null) { %>
				<a href="/PRJ-321x_A3/login_page" id="loginButton"> Login </a>
			<% } else if (session.getAttribute("user") != null && session.getAttribute("role").equals(0)) { %>
				<div  id="userExisted">
					<button class="sb"> <%= session.getAttribute("user") %> </button>
					<div class="content">
						<form action="logout" method="post"> <input id="logout" type="submit" value="Log Out"> </form>
					</div>
				</div>
			<% } else { %>
				<div id="userAdminExisted">
					<button class="sb"> <%= session.getAttribute("user") %> </button>
					<div class="content">
						<form action="logout" method="post"> 
							<input id="logout" type="submit" value="Log Out"> 
							<a href="/PRJ-321x_A3/admin_page">To admin page</a> 
						</form>
					</div>
				</div>
			<% } %>
			
			<a href="javascript:void(0);" class="icon" onclick="responsive()">
				<i class="fa fa-bars"></i>
			</a>
		</div>

	</div>

	<script src="assets/js/head-nav-resp.js"></script>
</body>

</html>