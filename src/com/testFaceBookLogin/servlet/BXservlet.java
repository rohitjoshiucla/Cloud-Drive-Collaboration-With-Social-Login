package com.testFaceBookLogin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.testModules.bean.BXbean;
import com.testModules.classes.BXclass;

/**
 * Servlet implementation class BXservlet
 */
@WebServlet("/BXservlet")
public class BXservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BXservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try{
			String code = request.getParameter("code");
			HttpSession session = request.getSession(true);
			BXclass bxClass = new BXclass();
			//check if called by box
			if(code!= null){
				//get access token
			    String token =bxClass.getTOKEN(code);
			    session.setAttribute(BXbean.NAME_TOKEN,token);
		        response.sendRedirect(request.getContextPath()+"/index3.jsp");
			}
			else{
				session = request.getSession(true);
				//log out
				if(session.getAttribute(BXbean.NAME_TOKEN) != null){
					session.removeAttribute(BXbean.NAME_TOKEN);
					response.sendRedirect(request.getContextPath()+"/index3.jsp");
				}
				//log in
				else{					
					bxClass.AUTHORIZE(response);
				}	
			}
		}
		catch(Exception e){
			response.sendRedirect(request.getContextPath()+"/index3.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
