package com.testFaceBookLogin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.testModules.bean.DBXbean;
import com.testModules.classes.DBXclass;


/**
 * Servlet implementation class DBXservlet
 */
@WebServlet("/DBXservlet")
public class DBXservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBXservlet() {
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
			DBXclass dbxClass = new DBXclass();
			
			//check if called by dropbox
			if(code != null){
				String token = dbxClass.getTOKEN(code);
				session.setAttribute(DBXbean.NAME_TOKEN, token);
				response.sendRedirect(request.getContextPath()+"/index3.jsp");				
			}
			else{
				session = request.getSession(true);
				//log out
				if(session.getAttribute(DBXbean.NAME_TOKEN) != null){
					session.removeAttribute(DBXbean.NAME_TOKEN);
					response.sendRedirect(request.getContextPath()+"/index3.jsp");
				}
				//log in
				else{
					dbxClass.AUTHORIZE(response);
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
