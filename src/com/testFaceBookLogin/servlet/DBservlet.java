package com.testFaceBookLogin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.testModules.bean.DBXbean;
import com.testModules.classes.DBXclass;

/**
 * Servlet implementation class DBservlet
 */
@WebServlet("/DBservlet")
public class DBservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			try{
				String path = request.getParameter(DBXbean.NAME_PATH);
				HttpSession session = request.getSession(true);
				DBXclass dbxClass = new DBXclass();
				
				if(path!=null){
					JSONArray jsonArr=dbxClass.getMETADATA(path, session);
			        request.setAttribute("jsonResponse", jsonArr);
			        getServletContext().getRequestDispatcher("/fileManager.jsp").forward(request, response);
				}
				else{
					response.sendRedirect(request.getContextPath()+"/index3.jsp");
				}
			}
			catch(Exception e){
				PrintWriter out = response.getWriter();
				out.write(e.toString());
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
