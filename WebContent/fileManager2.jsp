<%@page import="com.testModules.bean.DBXbean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.testModules.bean.BXbean" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Welcome</title>
		<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script src="js/bootstrap.js"></script>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row-fluid">		
				<div class="span9 offset3">
					
				</div>		
			</div>
			<div class="row-fluid">
				<div class="span9 offset3">
					<%=request.getAttribute("jsonResponse")%>
					<table class="table">
						<thead>
							<tr>
								<th>Name</th>
								<th>Kind</th>
								<th>Owner</th>
							</tr>
						</thead>
						<%
						JSONArray arr;
						if(request.getAttribute("jsonResponse")!=null){
							arr=(JSONArray)request.getAttribute("jsonResponse");
					        
					        @SuppressWarnings("rawtypes")
							Iterator it = arr.iterator();
					        
					        JSONObject name,value,bx,dbx;
					        String dName=null,dType=null, dOwner=null;
					        String bxFolderId=null, dbxPath=null;
					        
					        while(it.hasNext()){
					        	dType=null;dOwner=null;
						        bxFolderId=null; dbxPath=null;
						        
					        	name=(JSONObject)it.next();
					        	dName= name.getString("name");
					        	value = (JSONObject)name.get("value");
					        	
					        	if(value.containsKey("bx")){
					        		bx = (JSONObject)value.get("bx");
					  				dType =(dType==null)?bx.getString("type"):dType;
					  				bxFolderId = bx.getString("folder-id");
					  				dOwner = (dOwner==null)?"Box ":dOwner.concat("Box ");					  				
					        	}
					        	if(value.containsKey("dbx")){
					        		dbx = (JSONObject)value.get("dbx");
					        		dType =(dType==null)?dbx.getString("type"):dType;
					        		dbxPath = dbx.getString("path");
					        		dOwner = (dOwner==null)?"DropBox ":dOwner.concat("DropBox ");
					        	}
					        	%>
						        	<tr>
						        		<td>
							        		<%if(dType.equals("folder")) {%>
							        			<i class="icon-folder-close"></i>
								        	<%}
								        	else{}
								        	%>	
       										<a href="${pageContext.request.contextPath}/DB2servlet?<%=BXbean.NAME_PATH%>=<%=bxFolderId%>&<%=DBXbean.NAME_PATH%>=<%=dbxPath%>"><%=dName%></a>
   										</td>
						        		<td>
						        			<%=dType%>
						        		</td>
						        		<td>
						        			<%=dOwner%>
						        		</td>
						        	</tr>
		        			<%}
			        		
	        			}%>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>

