package com.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.PostDAO;
import com.DB.DBConnect;

@WebServlet("/DeleteFile")
public class DeleteFile extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer id = Integer.parseInt(request.getParameter("note_id"));
		Connection conn = DBConnect.getConn();
		
		boolean f = false;
		try {
			String qu = "Delete from file_details where id = ?";
			PreparedStatement ps = conn.prepareStatement(qu);
			ps.setInt(1, id);
			int i = ps.executeUpdate();
			if(i == 1) {
				f=true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		HttpSession session = null;
		System.out.println(f);
		if(f) {
			session = request.getSession();
			session.setAttribute("updateMsg", "Note Deleted Sucesssfully....");
			response.sendRedirect("showFile.jsp");
		}
		else {
			session = request.getSession();
			session.setAttribute("wrongMsg" , "Something went wrong.... Try Again!!!");
			response.sendRedirect("showFile.jsp");
		}

}
}
