package com.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.PostDAO;
import com.DB.DBConnect;

/**
 * Servlet implementation class deleteServlet
 */
@WebServlet("/deleteServlet")
public class deleteServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer id = Integer.parseInt(request.getParameter("note_id"));
		PostDAO dao = new PostDAO(DBConnect.getConn());
		
		boolean f = dao.deleteNote(id);
		
		HttpSession session = null;
		System.out.println(f);
		if(f) {
			session = request.getSession();
			session.setAttribute("updateMsg", "Note Deleted Sucesssfully....");
			response.sendRedirect("showNotes.jsp");
		}
		else {
			session = request.getSession();
			session.setAttribute("wrongMsg" , "Something went wrong.... Try Again!!!");
			response.sendRedirect("showNotes.jsp");
		}
		
		
	}
}
