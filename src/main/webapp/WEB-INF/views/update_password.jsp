<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Update Password</title>
	<link rel="stylesheet" type="text/css" href="/css/default.css" >
	<link rel="stylesheet" type="text/css" href="/css/notIndex.css" >
	<link rel="stylesheet" type="text/css" href="/css/form.css" >
	<style>
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
	<script type="text/javascript" src="/js/accountForms.js"></script>
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
					UPDATE PASSWORD
				</h2>
				<p>
					<c:if test="${not empty errorMessage}">
					   <c:out value="${errorMessage}" />
					</c:if>
				</p>
				<form name="user_display" action="updatePassword" method="post"
				style="display:flex; flex-direction: column; padding-bottom:10px">
					<div class="input_block">
						<label>Old Password</label>
						<input type="password" name="oldPass" id="PWO"/>
					</div>
					<div class="input_block">
						<label>New Password</label>
						<input type="password" name="newPass" id="PW" onKeyUp="verify()"/>
					</div>
					<div class="input_block">
						<label>Confirm Password</label>
						<input type="password" name="confPass" id="PWC" onKeyUp="verify()"/>
					</div>
					<div class="input_block">
						<button name="submit" id="SBM" disabled>- Submit -</button>
					</div>
					<script>
						let subButton = document.querySelector("#SBM");
						subButton.style.setProperty("--submit-hover-bckgnd", "darkgoldenrod");
						subButton.style.setProperty("--submit-hover-brdr", "gray");
					</script>
				</form>
			</div>
		</div>
	</div>
</body>
</html>