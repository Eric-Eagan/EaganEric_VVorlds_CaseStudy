<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css/default.css" >
    <link rel="stylesheet" type="text/css" href="css/notIndex.css" >
    <link rel="stylesheet" type="text/css" href="css/login.css" >
    <script type="text/javascript" src="js/login.js"></script>
</head>
<body>
	<div class="container">
		<h1>VVorlds</h1>
	</div>
	<div class="container">
		<div class="menu">
			<div class="menu_item" id="logo">
	        	<a href="./index.html">
					<img src="img/map.jpg" alt="Logo">
				</a>
	        </div>
			<div class="menu_item">
				<nav>
					<ul>
						<li>
							<a id="home" href="./index.html">Home</a>
						</li>
						<li>
							<a id="docs" href="#">Documents</a>
						</li>
						<li>
							<a id="res" href="./resources.html">Resources</a>
						</li>
						<li>
							<a id="about" href="#">About</a>
						</li>
						<li>
							<a id="login" href="./login.html">Login</a>
						</li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
	<div class="container" style="margin-top:-70px;">
		<div class="content">
			<div class="login_container">
				<h2 style="background-color: darkgoldenrod;
							box-shadow: 4px 4px 0px rgba(0, 0, 0, .5);
							border: 2px solid black;
							margin-left: 50px;
							margin-right: 50px;">
					LOG IN
				</h2>
				<form name="user_input" method="post"
				style="display:flex; flex-direction: column; padding-bottom:10px">
					<input type="text" name="userName" id="UN" placeholder="Username">
					<input type="password" name="password" id="PW" placeholder="Password">
					<input type="submit" name="login" onclick="logMeIn()" value="Log In">
				</form>
				<a href="#" id="register">
					Create Account
				</a>
			</div>
		</div>
	</div>
</body>
</html>
