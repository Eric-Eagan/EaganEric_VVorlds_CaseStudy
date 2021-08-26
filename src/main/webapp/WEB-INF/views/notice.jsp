<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Notice</title>
<link rel="stylesheet" type="text/css" href="/css/default.css" >
<link rel="stylesheet" type="text/css" href="/css/notIndex.css" >
<style type="text/css">
	button {
		background-color: DarkGoldenRod;
		padding: 0px 20px 0px 20px;
		border: 2px solid black;
		box-shadow: 4px 4px 0px rgba(0, 0, 0, .5);
		font-family: Palanquin Dark, cursive;
		font-size: xx-large;
	}
	
	button:hover {
		background-color: gold;
	}
	
	button:active {
		text-decoration: none;
	}
</style>
</head>
<body>
	<div class="container">
		<h1 style="padding: 15px;"><%= request.getAttribute("msg")%></h1>
	</div>
	<div class="container" style="margin-top: 110px">
		<button onclick=<%= "location.href='/"+request.getAttribute("send")+"';"%>>
			<%= request.getAttribute("sendName") %>
		</button>
	</div>
</body>
</html>