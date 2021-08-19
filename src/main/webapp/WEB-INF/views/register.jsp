<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
		
		#SBM:active {
			background-color: DarkGoldenRod;
		}
	</style>
	<script type="text/javascript" src="js/accountForms.js"></script>
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
					REGISTER
				</h2>
				<form:form name="user_input" action="registerNewUser" method="post"
				style="display:flex; flex-direction: column; padding-bottom:10px"
				modelAttribute="newUser">
					<div class="input_block">
						<form:errors path="username" cssClass="error" /><br>
						<label>Username</label>
						<form:input type="text" path="username" name="username" id="UN" placeholder="Username" onKeyUp="verify()"/>
					</div>
					<div class="input_block">
						<form:errors path="password" cssClass="error" /><br>
						<label>Password</label>
						<form:input type="password" path="password" name="password" id="PW" placeholder="Password" onKeyUp="verify()"/>
					</div>
					<div class="input_block">
						<label>Confirm Password</label>
						<input type="password" name="passwordConfirm" id="PWC" placeholder="Confirm Password" onKeyUp="verify()">
					</div>
					<div class="input_block">
						<br>
						<label>First Name</label>
						<input type="text" name="firstName" id="FN" placeholder="First Name">
					</div>
					<div class="input_block">
						<label>Last Name</label>
						<input type="text" name="lastName" id="LN" placeholder="Last Name">
					</div>
					<div class="input_block">
						<label>Email</label>
						<input type="text" name="email" id="EM" placeholder="Email">
					</div>
					<div class="input_block">
						<label>Address</label>
						<input type="text" name="address" id="AD" placeholder="Address">
					</div>
					<div class="input_block">
						<label>Phone</label>
						<input type="text" name="phone" id="PN" placeholder="Phone">
					</div>
					<div class="input_block">
						<button name="submit" id="SBM" disabled>- Submit -</button>
					</div>
					<script>
						let subButton = document.querySelector("#SBM");
						subButton.style.setProperty("--submit-hover-bckgnd", "darkgoldenrod");
						subButton.style.setProperty("--submit-hover-brdr", "gray");
					</script>
				</form:form>
				<a href="./login" id="register">
					Already Have Account
				</a>
			</div>
		</div>
	</div>
</body>
</html>

