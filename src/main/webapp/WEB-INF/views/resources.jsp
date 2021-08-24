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
	<link rel="stylesheet" type="text/css" href="css/form.css" >
    <script type="text/javascript" src="js/resources.js"></script>
</head>
<body>
	<div class="container">
		<h1>VVorlds</h1>
	</div>
	<div class="container">
		<%@ include file="html/menu.html" %>
	</div>
	<div class="container content-container">
		<div class="content" style="width: 100%; max-width: 1000px">
			<div class="vert_block" style="width: 100%;">
				<div style="display: flex; justify-content:center; margin: 20px 0px 15px 0px;">
					<div class="form_container" style="width: 250px;">
						<h2 style="background-color: darkgoldenrod;
									box-shadow: 4px 4px 0px rgba(0, 0, 0, .5);
									border: 2px solid black;
									margin: 10px 50px 10px 50px;">
							RESOURCES
						</h2>
						<div style="display: flex; justify-content:center;">
							<form name="resource_form">
								<select class="select-css" onchange="updateResources();" id="RS" name="resource_subject">
									<option value="" disabled selected>Select Topic</option>
								</select>
								<script>
									setupSelect();
								</script>
							</form>
						</div>
					</div>
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
