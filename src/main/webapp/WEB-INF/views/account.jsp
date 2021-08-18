<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title><%=session.getAttribute("currentUser") %> Account</title>
	<link rel="stylesheet" type="text/css" href="css/default.css" >
	<link rel="stylesheet" type="text/css" href="css/notIndex.css" >
	<style>
		#account {
			background-color: gold;
			color: #1c1808;
		}
	</style>
</head>
<body>
	<div class="container">
		<h1>VVorlds</h1>
	</div>
	<div class="container">
		<%@ include file="html/menu.html" %>
	</div>
</body>
</html>