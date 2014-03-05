<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.dropbox.core.*" %>
<%@ page import="java.awt.Desktop" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.Locale" %>
<%@ page import="com.testModules.bean.BXbean" %>
<%@ page import="com.testModules.bean.DBXbean" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
		<title>Welcome</title>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</head>

	<body>
		<div class="container-fluid">
		
			<div class ="row-fluid hero-unit">
				<div class="span3">
					<div class ="row-fluid">
						<div class="span4">
							<img alt="Dropbox" src="img/dropbox.jpg" class="img-rounded pull-right">
						</div>
						<div class="span4">
							<img alt="Skydrive" src="img/skydrive.png" class="img-rounded pull-right">
						</div>
					</div>			
				</div>
				<div class="span9">
					<h1>Universal File Hosting Platform</h1>
				</div>
			</div>
			
			<div class="row-fluid">
				<div class="span3 offset3">
					<form action="${pageContext.request.contextPath}/FBservlet" method="GET">
					<%if(session.getAttribute("fb-access-token")!=null){%>
							<button id="btn-fb" class="btn btn-success btn-large">
								Log out with Facebook
							</button>
						<%}else{%>
							<button id="btn-fb" class="btn btn-primary btn-large">
								Log in with Facebook
							</button>
						<%}%>
						<input type="text" value=<%=session.getAttribute("fb-access-token")%>>
 						<input type="text" value=<%=session.getAttribute("fb-access-token-expiry")%>>	
					</form>
				</div>
			</div>
			
			<div class="row-fluid">
				<div class="span3 offset3">
					<form action="${pageContext.request.contextPath}/DBXservlet" method="GET">					
						<%if(session.getAttribute(DBXbean.NAME_TOKEN)!=null){%>
							<button id="btn-dbx" class="btn btn-success btn-large">
								Log out DropBox
							</button>
						<%}else{%>
							<button id="btn-dbx" class="btn btn-primary btn-large">
								Log in DropBox
							</button>
						<%}%>
						<input type="text" value=<%=session.getAttribute(DBXbean.NAME_TOKEN)%>>
					</form>
				</div>
				<div class="span3">
					<form action="${pageContext.request.contextPath}/BXservlet" method="GET">					
						<%if(session.getAttribute(BXbean.NAME_TOKEN)!=null){%>
							<button id="btn-bx" class="btn btn-success btn-large">
								Log out Box
							</button>
						<%}else{%>
							<button id="btn-bx" class="btn btn-primary btn-large">
								Log in Box
							</button>
						<%}%>
						<input type="text" value=<%=session.getAttribute(BXbean.NAME_TOKEN)%>>
					</form>
				</div>
			</div>
			
			<div class="row-fluid">
				<div class="span3 offset3">
					<form action="${pageContext.request.contextPath}/DBservlet" method="GET">
						<%
						session.removeAttribute(DBXbean.NAME_PATH_LIST);
						if(session.getAttribute(DBXbean.NAME_TOKEN)!=null){							
						%>
							<button id="btn-db" name=<%=DBXbean.NAME_PATH%> value="/" class="btn btn-success btn-large">
								File Manager
							</button>
						<%}else{
							session.removeAttribute(DBXbean.NAME_TOKEN);
						%>
							<button id="btn-db" name=<%=DBXbean.NAME_PATH%> value=null class="btn btn-primary btn-large">
								File Manager
							</button>
						<%}%>
					</form>
				</div>
				
				<div class="span3">
					<form action="${pageContext.request.contextPath}/DB1servlet" method="GET">
						<%
						session.removeAttribute(BXbean.NAME_PATH_LIST);
						if(session.getAttribute(BXbean.NAME_TOKEN)!=null){							
						%>
							<button id="btn-db1" name=<%=BXbean.NAME_PATH%> value="0" class="btn btn-success btn-large">
								File Manager
							</button>
						<%}else{
							session.removeAttribute(BXbean.NAME_TOKEN);
						%>
							<button id="btn-db1" name=<%=BXbean.NAME_PATH%> value=null class="btn btn-primary btn-large">
								File Manager
							</button>
						<%}%>
					</form>
				</div>
				
				<div class="span3">
					<form action="${pageContext.request.contextPath}/DB2servlet" method="GET">
						<%
						session.removeAttribute(BXbean.NAME_PATH_LIST);
						session.removeAttribute(DBXbean.NAME_PATH_LIST);
						%>
						<input type="hidden" name=<%=DBXbean.NAME_PATH%> value="/">
						<input type="hidden" name=<%=BXbean.NAME_PATH%> value="0">
						<button id="btn-db2" class="btn btn-primary btn-large">
							File Manager
						</button>
					
					</form>
				</div>
			</div>
			
		</div>
	</body>
</html>