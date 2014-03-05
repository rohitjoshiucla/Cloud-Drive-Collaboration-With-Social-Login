package com.testFaceBookLogin.servlet;


import java.io.IOException;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Servlet implementation class FBservlet
 */
@WebServlet("/FBservlet")
public class FBservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FBservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			//check if called by facebook
			if(request.getParameter("code") != null){
				
				//get access-token
				final String APP_KEY = "1425845537653977";
				final String APP_SECRET = "e59286d679895924f75a5586453b5fba";
				
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet();
				
			    httpGet.setURI(new URI("https://graph.facebook.com/oauth/access_token?code="+request.getParameter("code")+"&client_id="+APP_KEY+"&client_secret="+APP_SECRET+"&redirect_uri=http://localhost:8080/testFacebookLogin/FBservlet"));
				HttpResponse httpResp = httpClient.execute(httpGet);
			    HttpEntity respEntity = httpResp.getEntity();
			    
			    String temp = EntityUtils.toString(respEntity);
			    String arr[]=temp.split("&");
			    
			    String token[]=arr[0].split("=");
			    String expiry[]=arr[1].split("=");
			    
			    //set session
		        HttpSession session = request.getSession(true);
		        session.setAttribute("fb-access-token", token[1]);
		        session.setAttribute("fb-access-token-expiry", expiry[1]);
		        response.sendRedirect("/testFacebookLogin/index3.jsp");
				
			}
			else{
				HttpSession session = request.getSession(true);
				//log out
				if(session.getAttribute("fb-access-token") != null){
					session.removeAttribute("fb-access-token");
					session.removeAttribute("fb-access-token-expiry");
					response.sendRedirect("/testFacebookLogin/index3.jsp");
				}
				//log in
				else{
					response.sendRedirect("https://graph.facebook.com/oauth/authorize?client_id=1425845537653977&redirect_uri=http://localhost:8080/testFacebookLogin/FBservlet&response_type=code");
				}	
			}
		}
		catch(Exception e){
			response.sendRedirect("/testFacebookLogin/index3.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}