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

import com.testModules.bean.BXbean;
import com.testModules.bean.DBXbean;
import com.testModules.classes.BXclass;
import com.testModules.classes.DBXclass;

/**
 * Servlet implementation class DB2servlet
 */
@WebServlet("/DB2servlet")
public class DB2servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DB2servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			String dbx_path = request.getParameter(DBXbean.NAME_PATH);
			String bx_path = request.getParameter(BXbean.NAME_PATH);
			
			HttpSession session = request.getSession(true);
			DBXclass dbxClass = new DBXclass();
			BXclass bxClass = new BXclass();
			
			JSONArray dbx_jsonArr = new JSONArray();
			JSONArray bx_jsonArr = new JSONArray();
			JSONArray merged = new JSONArray();
			
			if(dbx_path!=null || bx_path!=null){
				if(dbx_path!=null){
					dbx_jsonArr=dbxClass.getMETADATA(dbx_path, session);
					request.setAttribute("dbx_jsonResponse", dbx_jsonArr);
					merged = dbxClass.merge(merged, dbx_jsonArr);
				}
				if(bx_path!=null){
					bx_jsonArr=bxClass.getMETADATA(bx_path, session);
					request.setAttribute("bx_jsonResponse", bx_jsonArr);
					merged = bxClass.merge(merged, bx_jsonArr);
				}
				
				request.setAttribute("jsonResponse", merged);
		        getServletContext().getRequestDispatcher("/fileManager2.jsp").forward(request, response);
			}
			else{
				//response.sendRedirect(request.getContextPath()+"/index3.jsp");
			}
		}
		catch(Exception e){
			PrintWriter out = response.getWriter();
			out.write(e.toString());
			//response.sendRedirect(request.getContextPath()+"/index3.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
