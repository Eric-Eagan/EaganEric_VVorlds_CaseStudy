<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="css/default.css" >
    <link rel="stylesheet" type="text/css" href="css/notIndex.css" >
    <link rel="stylesheet" type="text/css" href="css/form.css" >
	<style>
		input {
			padding-top: 5px;
			padding-bottom: 5px;
			margin-top: 5px;
			margin-bottom: 5px;
		}
		
		#SBM {
			border-color: gray;
		}
		
		#SBM:hover {
			background-color: var(--submit-hover-bckgnd);
			border-color: var(--submit-hover-brdr);
		}
	</style>
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
			<div class="form_container">
				<h2 style="background-color: darkgoldenrod;
							box-shadow: 4px 4px 0px rgba(0, 0, 0, .5);
							border: 2px solid black;
							margin-left: 50px;
							margin-right: 50px;">
					REGISTER
				</h2>
				<form name="user_input" action="register" method="post"
				style="display:flex; flex-direction: column; padding-bottom:10px">
					<input type="text" name="userName" id="UN" placeholder="Username" onKeyUp="verify()">
					<input type="password" name="password" id="PW" placeholder="Password" onKeyUp="verify()">
					<input type="password" name="passwordConfirm" id="PWC" placeholder="Confirm Password" onKeyUp="verify()">
					<input type="text" name="firstName" id="FN" placeholder="First Name">
					<input type="text" name="lastName" id="LN" placeholder="Last Name">
					<input type="text" name="email" id="EM" placeholder="Email">
					<input type="text" name="address" id="AD" placeholder="Address">
					<input type="text" name="phone" id="PN" placeholder="Phone">
					<input type="submit" name="submit" id="SBM" disabled value="- Submit -">
					<script>
						let subButton = document.querySelector("#SBM");
						subButton.style.setProperty("--submit-hover-bckgnd", "darkgoldenrod");
						subButton.style.setProperty("--submit-hover-brdr", "gray");
					</script>
				</form>
				<a href="./login.html" id="register">
					Already Have Account
				</a>
			</div>
		</div>
	</div>
</body>
</html>

