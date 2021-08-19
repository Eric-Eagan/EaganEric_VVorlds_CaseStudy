<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css/default.css" >
    <link rel="stylesheet" type="text/css" href="css/notIndex.css" >
    <link rel="stylesheet" type="text/css" href="css/form.css" >
</head>
<body>
	<div class="container">
		<h1>VVorlds</h1>
	</div>
	<div class="container">
		<%@ include file="html/menu.html" %>
	</div>
	<div class="container content-container">
		<div class="content">
			<div class="form_container">
				<h2 style="background-color: darkgoldenrod;
							box-shadow: 4px 4px 0px rgba(0, 0, 0, .5);
							border: 2px solid black;
							margin-left: 50px;
							margin-right: 50px;">
					LOG IN
				</h2>
				<p>
					<c:if test="${not empty param['error']}"> 
						<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" /> 
					</c:if> 
				</p>
				<form name="user_input" action="login" method="post"
				style="display:flex; flex-direction: column; padding-bottom:10px">
					<div class="input_block">
						<input type="text" name="username" id="UN" placeholder="Username">
					</div>
					<div class="input_block">
						<input type="password" name="password" id="PW" placeholder="Password">
					</div>
					<div class="input_block">
						<input type="submit" name="submit" value="- Log In -">
					</div>
				</form>
				<a href="./register" id="register">
					Create Account
				</a>
			</div>
		</div>
	</div>
</body>
</html>

