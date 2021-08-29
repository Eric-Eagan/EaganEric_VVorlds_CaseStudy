<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>File Types</title>
    <link rel="stylesheet" type="text/css" href="/css/default.css" >
    <link rel="stylesheet" type="text/css" href="/css/notIndex.css" >
	<link rel="stylesheet" type="text/css" href="/css/form.css" >
	<style>
		#fileTypes {
			background-color: gold;
			color: #1c1808;
		}
		
		table {
			background-color: gold;
		}
		
		table, th, td {
			background-color: gold;
			border: 2px solid black;
		}
		
		th, td {
			background-color: darkgoldenrod;
			text-align: center;
			padding: 10px;
		}
		
		input {
			padding-top: 5px;
			padding-bottom: 5px;
			margin-top: 5px;
			margin-bottom: 5px;
		}
		
		#TN {
			display: inline;
			margin: 0px;
			width: 272px;
			margin: 5px 10px 5px 10px;
		}
		
		#browse {
			position: absolute;
			left: 11px;
			top: 12px;
			width: 60px;
			height: 21px;
			font-size: 13px;
			box-shadow: none;
		}
		
		#browse:hover {
			background-color: gold;
		}
		
		#image:hover {
			background-color: DarkGoldenrod;
		}
		
		#image:focus {
			background-color: DarkGoldenrod;
		}
	</style>
    <script type="text/javascript" src="/js/fileTypes.js"></script>
	<script>
		var typeList = [];
		<c:forEach items="${fileTypes}" var="temp">
			typeList.push({id: "${temp.typeId}",
				   		   imgPath: "${temp.imgPath}",
						   name: "${temp.type}"});	
		</c:forEach>
	</script>
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
					<div class="form_container" style="width: 350px;">
						<h2 style="background-color: darkgoldenrod;
									box-shadow: 4px 4px 0px rgba(0, 0, 0, .5);
									border: 2px solid black;
									margin: 10px 50px 10px 50px;" id="mydesc">
							CHANGE FILE TYPES
						</h2>
					</div>
				</div>
				
				<table style="width: 100%;" aria-describedby="mydesc">
					<colgroup>
					    <col span="1" style="width: 135px">
				    	<col span="1" style="width: auto;">
				    	<col span="1" style="width: 145px">
				    </colgroup>
			        <thead>
				        <tr>
				    		<th scope="col">Image</th>
				   			<th scope="col">File Type</th>
				   			<th scope="col"></th>
				        </tr>
			        </thead>
			        <tbody>
				        <tr>
							<td></td>
							<td style="padding-bottom: 10px;"></td>
							<td style="padding-bottom: 10px;"></td>
						</tr>
					</tbody>
			    </table>
			    <script>loadFileTypes();</script>
			    
				<div style="display: flex; justify-content:center; margin: 20px 0px 15px 0px;">
					<div class="form_container" style="width: 350px;">
						<form name="typeForm" id="typeForm" action="changeFileTypes/add" method="post"
							style="display:flex; flex-direction: column; padding-bottom:10px"
							enctype="multipart/form-data">
							<div class="input_block">
								<label for="typeName">Type Name</label>
								<input type="text" name="typeName" id="TN"/>
							</div>
							<div class="input_block">
								<label for="image">File Type Icon</label>
								<div style="display: inline-block; position: relative;">
									<label for="image" id="browse">Browse... </label>
									<input type="file" id="image" name="image" style="width: 272px"/>	
								</div>
							</div>
							<div class="input_block">
								<button name="submit" id="SBM">
									- Submit -
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
  	<div class="container">
  		<%@ include file="html/admin_menu.html" %>
  	</div>
</body>
</html>
