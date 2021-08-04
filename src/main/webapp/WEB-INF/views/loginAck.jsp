<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logging In...</title>
<link rel="stylesheet" type="text/css" href="css/default.css" >
<link rel="stylesheet" type="text/css" href="css/notIndex.css" >
</head>
<body>
	<div class="container">
		<h1><%= request.getAttribute("username") %>, Successful Login</h1>
	</div>
	<div class="container" style="margin-top: 110px">
		<button onclick="location.href = './index.html';">Return</button>
	</div>
	
	<script>
		console.log("running script");
		if(<%= request.getAttribute("success") %> == 0) {
			document.querySelector("h1").textContent = "Unsuccessful Login, Try again"
			document.querySelector("button").setAttribute("onclick", "location.href = './login.html';"); 
		}
	</script>
</body>
</html>