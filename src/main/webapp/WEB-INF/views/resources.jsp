<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resources</title>
    <link rel="stylesheet" type="text/css" href="css/default.css" >
    <link rel="stylesheet" type="text/css" href="css/notIndex.css" >
    <link rel="stylesheet" type="text/css" href="css/resources.css" >
    <script type="text/javascript" src="js/resources.js"></script>
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
		<div class="content" style="width: 100%; max-width: 1000px">
			<div class="vert_block" style="width: 100%;">
				<h2>Resources</h2>
				<div class="container">
					<form name="resource_form">
						<select class="select-css" onchange="updateResources();" id="RS" name="resource_subject">
							<option value="" disabled selected>Select Topic</option>
						</select>
						<script>
							setupSelect();
						</script>
					</form>
				</div>
				<table style="width: 100%;">
					<colgroup>
					    <col span="1" style="width: 5%;">
				    	<col span="1" style="width: 95%;">
				    </colgroup>
			        <thead>
				        <tr>
				    		<th>Description</th>
				   			<th>Video</th>
				        </tr>
			        </thead>
			        <tbody>
				        <tr>
							<td></td>
							<td style="padding-bottom: 10px;">No Topic Selected</td>
						</tr>
					</tbody>
			    </table>
			</div>
		</div>
	</div>
</body>
</html>
