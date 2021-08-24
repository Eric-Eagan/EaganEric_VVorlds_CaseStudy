<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Files</title>
    <link rel="stylesheet" type="text/css" href="css/default.css" >
    <link rel="stylesheet" type="text/css" href="css/notIndex.css" >
	<link rel="stylesheet" type="text/css" href="css/form.css" >
    <script>
    	function Document(id, path, typeId) {
    		this.id = id;
    		
    	}
    	
    	var ownedList = [];
    	var shareList = [];
    	<c:forEach items="${ownedFiles}" var="temp">
    		ownedList.push({id: "${temp.fileId}", 
    						path: "${temp.path}", 
    						title: "${temp.fileName}", 
    						fileType: "${temp.fileType.imgPath}"});	
    	</c:forEach>
    	
    	<c:forEach items="${shareFiles}" var="temp">
    		shareList.push({id: "${temp.fileId}", 
    						path: "${temp.path}", 
    						title: "${temp.fileName}", 
    						fileType: "${temp.fileType.imgPath}"});
		</c:forEach>
    	
    </script>
    <script type="text/javascript" src="js/files.js"></script>
	<style>
		img {
			width: 135px;
		}
	
		.head-button {
			padding: 5px;
			margin: 5px;
		}
		
		.file-spacer {
			width: 240px;
			height: 275px;
			display: flex;
			align-content: center;
			padding: 4px;
			justify-content: center;
		}
		
		.image-container {
			height: 179px;
			display: flex;
			align-items: center;
		}
		
		.box-container {
			position: relative;
			font-family: Palanquin Dark, cursive;
			padding: 4px;
			width: 135px;
		}
		
		.file-label {
			width: 200px;
			z-index: 1;
			position: relative;
			margin-top: -20px;
			padding: 4px;
		}
		
		.button-container {
			display: flex;
			justify-content: center;
		}
		
		.file-button {
			padding: 0px 10px 0px 10px;
			margin: 10px;
		}
		
		.file-title {
			display: -webkit-box;
			-webkit-box-orient: vertical;
			overflow: hidden;
			text-overflow: ellipsis;
			-webkit-line-clamp: 2;
			line-height: 20px;
		}
		
		.corner-box {
			position: absolute;
			padding: 0px;
			width: 22px;
			height: 22px;
			right: 0;
			top: 0;
		}
		
		#template {
			display: none;
		}
		
		#docs {
			background-color: gold;
			color: #1c1808;
		}
	</style>
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
					<div class="form_container" style="width: 280px;">
						<h2 style="background-color: darkgoldenrod;
									box-shadow: 4px 4px 0px rgba(0, 0, 0, .5);
									border: 2px solid black;
									margin: 10px;">
							YOUR FILES
						</h2>
						<div style="display: flex; justify-content:center;">
							<button class="head-button" onclick="showOwnedFiles()">Your Files</button>
							<button class="head-button" onclick="showSharedFiles()">Shared Files</button>
						</div>
						<div style="display: flex; justify-content:center;">
							<button class="head-button" onclick="location.href = '/uploadFile'">New File</button>
						</div>
					</div>
				</div>
				<div class="container form_container" id="file-block" style="flex-wrap: wrap;">
				</div>
			</div>
		</div>
	</div>
	<div class="file-spacer" id="template"> 
		<div class="file-container">
			<div class="container">
				<div class="box-container visible-box">
					<button class="corner-box">
						<img src="img/x.png" style="width: 18px; height: 18px;" alt="X">
					</button>
					<div class="image-container">
						<img src="" alt="icon"> 
					</div> 
				</div>
			</div>
			<div class="file-label visible-box">
				<span class="file-title">Title</span>
				<div class="button-container">
					<button class="file-button">Download</button>
					<button class="file-button">Share</button>
				</div>
			</div>
		</div>
	</div>
	<script>showOwnedFiles();</script>
</body>
</html>