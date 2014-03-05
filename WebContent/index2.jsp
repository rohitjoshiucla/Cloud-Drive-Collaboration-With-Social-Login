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
		<script type="text/javascript">
			window.fbAsyncInit = function() {
			  FB.init({
			    appId      : '1425845537653977',
			    status     : true, // check login status
			    cookie     : true, // enable cookies to allow the server to access the session
			    xfbml      : true  // parse XFBML
			  });
			  FB.Event.subscribe('auth.authResponseChange', function(response) {
				    // Here we specify what we do with the response anytime this event occurs. 
				    if (response.status === 'connected') {
				      
				    } else if (response.status === 'not_authorized') {
				      
				    } else {
				      
				    }
			    FbButton(FB.getLoginStatus( function (response){
					 FbButton(response); 
				  }));
			  });
			};
			// Load the SDK asynchronously
			  (function(d){
			   var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
			   if (d.getElementById(id)) {return;}
			   js = d.createElement('script'); js.id = id; js.async = true;
			   js.src = "//connect.facebook.net/en_US/all.js";
			   ref.parentNode.insertBefore(js, ref);
			  }(document));
	
			FbLogin = function(){
				//var uid="";
				//var accessToken="";
				FB.getLoginStatus(function(response) {
					if (response.status == 'connected') {
					    //uid = response.authResponse.userID;
					    //accessToken = response.authResponse.accessToken;
				  	} 
					else {
					    FB.login(function(response) {
						    if (response.authResponse) {
						        //uid = response.authResponse.userID;
					    		//accessToken = response.authResponse.accessToken;
						    } else {
						    	alert("Login Dialog Cancelled");
						    }
						});
				  	}
				});
			};
				
			FbButton = function(response){
				alert(response.status);
				if (response.status === "connected") {
					alert("Inside if");
				    document.getElementById("btn-fb").className="btn btn-success btn-large";
				    document.getElementById("btn-fb").innerHTML="Logged in with Facebook";
				    document.getElementById("test").value=true;
				    <%session.setAttribute("fb-status", true);%>
			  	} 
				else {
					alert("Inside else");
					document.getElementById("btn-fb").className="btn btn-primary btn-large";
					document.getElementById("btn-fb").innerHTML="Log in with Facebook";
					<%session.setAttribute("fb-status", "hello");%>
				}
			};
		</script>		
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
					
						<%if(session.getAttribute("dbx-access-token")!=null){%>
						<button id="a-dbx" class="btn btn-success btn-large">
							Dropbox Synced
						</button>
						<%}else{%>
						<button id="a-dbx" class="btn btn-primary btn-large">
							Try Sync Dropbox
						</button>
						<%}%>
						<input type="text" value=<%=session.getAttribute("dbx-access-token")%>>
 						<input type="text" id="test" >
					</form>
				</div>
			</div>
			
		</div>
	</body>
</html>