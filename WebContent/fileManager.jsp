<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.testModules.bean.DBXbean" %>

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
					<%
					ArrayList<Object> pathList = new ArrayList<Object>();
					if(session.getAttribute(DBXbean.NAME_PATH_LIST)!=null){
						pathList = (ArrayList<Object>)session.getAttribute(DBXbean.NAME_PATH_LIST);
						%>
						<ul class="breadcrumb">
						<%
						HashMap<String,Object> hmap = new HashMap<String, Object>();
						for(int i=0;i<pathList.size();i++){
						%>
						<li>
						<%hmap=(HashMap<String,Object>)pathList.get(i);%>
							<a href="${pageContext.request.contextPath}/DBservlet?<%=DBXbean.NAME_PATH%>=<%=hmap.get("path")%>">
								<%=hmap.get("name")%>
							</a>
							<span class="divider">/</span>
						</li>
						<%	
						}
					}
					%>
					</ul>
				</div>		
			</div>
			<div class="row-fluid">
				<div class="span9 offset3">
					
					<table class="table">
						<thead>
							<tr>
								<th>Name</th>
								<th>Kind</th>
							</tr>
						</thead>
						<%
						JSONArray arr;
						if(request.getAttribute("jsonResponse")!=null){
							arr=(JSONArray)request.getAttribute("jsonResponse");
					        
					        @SuppressWarnings("rawtypes")
							Iterator it = arr.iterator();
					        while(it.hasNext()){
					        	JSONObject objInner=(JSONObject)it.next();
					        	%>
						        	<tr>
						        		<td>
							        		<%if(objInner.getString("type").equals("Folder")) {%>
							        			<i class="icon-folder-close"></i>
								        	<%}
								        	else{}
								        	%>	
       										<a href="${pageContext.request.contextPath}/DBservlet?<%=DBXbean.NAME_PATH%>=<%=objInner.getString("path")%>"><%=objInner.getString("name")%></a>
   										</td>
						        		<td>
						        			<%=objInner.getString("type")%>
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

