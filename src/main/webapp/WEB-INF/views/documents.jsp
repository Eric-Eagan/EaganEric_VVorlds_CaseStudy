<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Documents</title>
    <link rel="stylesheet" type="text/css" href="css/default.css" >
    <link rel="stylesheet" type="text/css" href="css/notIndex.css" >
	<link rel="stylesheet" type="text/css" href="css/form.css" >
	<style>
		img {
			width: 135px;
		}
	
		.head-button {
			padding: 5px;
			margin: 5px;
		}
		
		.document-spacer {
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
			font-family: Palanquin Dark, cursive;
			padding: 4px;
			width: 135px;
		}
		
		.document-label {
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
		
		.document-button {
			padding: 0px 10px 0px 10px;
			margin: 10px;
		}
		
		.document-title {
			display: -webkit-box;
			-webkit-box-orient: vertical;
			overflow: hidden;
			text-overflow: ellipsis;
			-webkit-line-clamp: 2;
			line-height: 20px;
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
							YOUR DOCUMENTS
						</h2>
						<div style="display: flex; justify-content:center;">
							<button class="head-button">Your Documents</button>
							<button class="head-button">Shared Documents</button>
						</div>
						<div style="display: flex; justify-content:center;">
							<button class="head-button">New Document</button>
						</div>
					</div>
				</div>
				<div class="container form_container" style="flex-wrap: wrap;">
				
					<div class="document-spacer"> 
						<div class="document-container">
							<div class="container">
								<div class="box-container visible-box">
									<div class="image-container">
										<img src="img/document.png" alt="icon"> 
									</div> 
								</div>
							</div>
							<div class="document-label visible-box">
								<span class="document-title">This is an exceptionally long Title</span>
								<div class="button-container">
									<button class="document-button">Download</button>
									<button class="document-button">Delete</button>
								</div>
							</div>
						</div>
					</div>
					
					<div class="document-spacer"> 
						<div class="document-container">
							<div class="container">
								<div class="box-container visible-box">
									<div class="image-container">
										<img src="img/image.png" alt="icon">
									</div> 
								</div>
							</div>
							<div class="document-label visible-box">
								<span class="document-title">Title</span>
								<div class="button-container">
									<button class="document-button">Download</button>
									<button class="document-button">Delete</button>
								</div>
							</div>
						</div>
					</div>
					
					<div class="document-spacer"> 
						<div class="document-container">
							<div class="container">
								<div class="box-container visible-box">
									<div class="image-container">
										<img src="img/document.png" alt="icon"> 
									</div> 
								</div>
							</div>
							<div class="document-label visible-box">
								<span class="document-title">This is an exceptionally long Title even longer than the others</span>
								<div class="button-container">
									<button class="document-button">Download</button>
									<button class="document-button">Delete</button>
								</div>
							</div>
						</div>
					</div>
					
					<div class="document-spacer"> 
						<div class="document-container">
							<div class="container">
								<div class="box-container visible-box">
									<div class="image-container">
										<img src="img/document.png" alt="icon"> 
									</div> 
								</div>
							</div>
							<div class="document-label visible-box">
								<span class="document-title">This is an exceptionally long Title even longer than the others</span>
								<div class="button-container">
									<button class="document-button">Download</button>
									<button class="document-button">Delete</button>
								</div>
							</div>
						</div>
					</div>
					
					<div class="document-spacer"> 
						<div class="document-container">
							<div class="container">
								<div class="box-container visible-box">
									<div class="image-container">
										<img src="img/document.png" alt="icon"> 
									</div> 
								</div>
							</div>
							<div class="document-label visible-box">
								<span class="document-title">This is an exceptionally long Title even longer than the others</span>
								<div class="button-container">
									<button class="document-button">Download</button>
									<button class="document-button">Delete</button>
								</div>
							</div>
						</div>
					</div>
					
					<div class="document-spacer"> 
						<div class="document-container">
							<div class="container">
								<div class="box-container visible-box">
									<div class="image-container">
										<img src="img/document.png" alt="icon"> 
									</div> 
								</div>
							</div>
							<div class="document-label visible-box">
								<span class="document-title">This is an exceptionally long Title even longer than the others</span>
								<div class="button-container">
									<button class="document-button">Download</button>
									<button class="document-button">Delete</button>
								</div>
							</div>
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>