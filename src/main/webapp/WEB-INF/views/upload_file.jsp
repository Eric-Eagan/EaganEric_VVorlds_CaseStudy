<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="ISO-8859-1">
	<title>Upload File</title>
	<link rel="stylesheet" type="text/css" href="/css/default.css" >
	<link rel="stylesheet" type="text/css" href="/css/notIndex.css" >
    <link rel="stylesheet" type="text/css" href="/css/selectForm.css" >
	<link rel="stylesheet" type="text/css" href="/css/form.css" >
	<style>
		input {
			padding-top: 5px;
			padding-bottom: 5px;
			margin-top: 5px;
			margin-bottom: 5px;
		}
		
		#FT {
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
		
		#file:hover {
			background-color: DarkGoldenrod;
		}
		
		#file:focus {
			background-color: DarkGoldenrod;
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
	<script type="text/javascript" src="/js/accountForms.js"></script>
	<script type="text/javascript" src="/js/fileUpload.js"></script>
	<script>
		var typeList = [];
		<c:forEach items="${fileTypes}" var="temp">
			typeList.push({id: "${temp.typeId}",
						   type: "${temp.type}"});	
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
		<div class="content">
			<div class="form_container">
				<h2 style="background-color: darkgoldenrod;
							box-shadow: 4px 4px 0px rgba(0, 0, 0, .5);
							border: 2px solid black;
							margin-left: 50px;
							margin-right: 50px;">
					UPLOAD FILE
				</h2>
				<form:form name="fileForm" id="fileForm" action="uploadFile" method="post"
				style="display:flex; flex-direction: column; padding-bottom:10px"
				modelAttribute="newFile" enctype="multipart/form-data">
					<div class="input_block">
						<form:label path="fileName">File Name</form:label>
						<form:input type="text" path="fileName" name="fileName" id="FN"/>
					</div>
					<div class="input_block">
						<label for="file">File </label>
						<div style="display: inline-block; position: relative;">
							<label for="file" id="browse">Browse... </label>
							<input type="file" id="file" name="file" style="width: 248px"/>	
						</div>
					</div>
					<div class="input_block">
						<form:label path="fileType">File Type</form:label>
						<form:select class="select-css" path="fileType" onchange="enableSub()" name="fileType" id="FT">
							<option value="" disabled selected>Select Type</option>
						</form:select>
					</div>
					<div class="input_block">
						<button name="submit" class="hastooltip" id="SBM" disabled="disabled">
							- Submit -
							<span class="tooltiptext">Must select a file type</span>
						</button>
					</div>
					<script>
						let subButton = document.querySelector("#SBM");
						subButton.style.setProperty("--submit-hover-bckgnd", "darkgoldenrod");
						subButton.style.setProperty("--submit-hover-brdr", "gray");
						
						addTypes();
					</script>
					<a style="background-color: DarkOliveGreen;" href="/files">Back</a>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>