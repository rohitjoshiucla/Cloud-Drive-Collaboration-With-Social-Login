<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.dropbox.core.*" %>
<%@ page import="java.awt.Desktop" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.Locale" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
		<title>Welcome</title>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/facebook.js"></script>		
	</head>

	<body>
		<div id="fb-root"></div>
		
		<div class="container-fluid">
		
			<div class ="row-fluid hero-unit">
				<div class="span3">
					<div class ="row-fluid">
						<div class="span4">
							<img alt="Dropbox" src="images/dropbox.jpg" class="img-rounded pull-right">
						</div>
						<div class="span4">
							<img alt="Skydrive" src="images/skydrive.png" class="img-rounded pull-right">
						</div>
					</div>			
				</div>
				<div class="span9">
					<h1>Universal File Hosting Platform</h1>
				</div>
			</div>
			
			<div class="row-fluid">
				<div class="span3 offset3">
					<button id="btn-fb" class="btn btn-primary btn-large" onclick="FbLogin()">Log in with Facebook
					</button>
				</div>
			</div>
			
			<div class="row-fluid">
				<div class="span3 offset3">
					<form action="${pageContext.request.contextPath}/DBXservlet" method="GET">
						<a href=<%="https://www.dropbox.com/1/oauth2/authorize?locale=en_US&client_id=wke6spdwao027uj&response_type=code&redirect_uri=http://localhost:8080/testFacebookLogin/DBXservlet&response_type=code"
						%> 
						id="a-dbx" class="btn btn-primary btn-large">Try Sync Dropbox
						</a>
					</form>
				</div>
			</div>
			
		</div>
	</body>
</html>