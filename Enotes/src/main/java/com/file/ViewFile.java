package com.file;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DB.DBConnect;

@WebServlet("/ViewFile")
public class ViewFile extends HttpServlet {
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		try {
		String name = request.getParameter("name");

		
		String path = "C:\\Users\\win 10\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Enotes\\file\\"+name;
			
		File file = new File(path);
		System.out.println(path);
		if(file.exists()) {
			Process pro = Runtime.getRuntime().exec("rundll32 url.dll , FileProtocolHandler "+path);
			pro.waitFor();
			response.sendRedirect("showFile.jsp");
		}
	}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
