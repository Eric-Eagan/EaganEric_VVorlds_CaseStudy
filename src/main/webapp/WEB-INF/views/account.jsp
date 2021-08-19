<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title><%=session.getAttribute("currentUser") %>'s Account</title>
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
		
		button {
			float: right;
		}
		
		#account {
			background-color: gold;
			color: #1c1808;
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
					ACCOUNT
				</h2>
				<form:form name="user_display" id="user_display" action="" method="post"
				style="display:flex; flex-direction: column; padding-bottom:10px"
				modelAttribute="User">
					<div class="input_block">
						<form:label path="username">Username</form:label>
						<form:input type="text" path="username" name="username" id="UN" class="lockedInput" readonly="true"/>
					</div>
					<div class="input_block">
						<label>Password</label>
						<input type="password" name="password" id="PW" class="lockedInput" placeholder="****" readonly/>
					</div>
					<div class="input_block">
						<button id="changePass" form="" onclick="location.href = './updatePassword';">- Change Password -</button>
					</div>
				</form:form>
				<form:form name="account_display" id="account_display" action="updateAccount" method="post"
				style="display:flex; flex-direction: column; padding-bottom:10px"
				modelAttribute="Account">
					<div class="input_block">
						<br>
						<label>First Name</label>
						<form:input type="text" path="firstName" name="firstName" id="FN" class="lockedInput" placeholder="" readonly="true"/>
					</div>
					<div class="input_block">
						<label>Last Name</label>
						<form:input type="text" path="lastName" name="lastName" id="LN" class="lockedInput" placeholder="" readonly="true"/>
					</div>
					<div class="input_block">
						<label>Email</label>
						<form:input type="text" path="email" name="email" id="EM" class="lockedInput" placeholder="" readonly="true"/>
					</div>
					<div class="input_block">
						<label>Address</label>
						<form:input type="text" path="address" name="address" id="AD" class="lockedInput" placeholder="" readonly="true"/>
					</div>
					<div class="input_block">
						<label>Phone</label>
						<form:input type="text" path="phone" name="phone" id="PN" class="lockedInput" placeholder="" readonly="true"/>
					</div>
					<div class="input_block">
						<button form="" name="submit" onclick="unlockAccount()" id="SBM">- Update Info -</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>