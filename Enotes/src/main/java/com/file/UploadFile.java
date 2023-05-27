package com.file;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.DB.DBConnect;

@WebServlet("/UploadFile")
@MultipartConfig
public class UploadFile extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Getting all the content from AddFile form 
		Part p = req.getPart("file");
		String fileName = p.getSubmittedFileName();
		
		String subject = req.getParameter("subject");
		
		String remark = req.getParameter("remark");
		
		int uid = Integer.parseInt(req.getParameter("uid"));
		
		try {
			Connection conn = DBConnect.getConn();
			
			PreparedStatement ps = conn.prepareStatement("insert into file_details(file_name , remark , user , subject) values(?,?,?,?)");
			
			ps.setString(1, fileName);
			ps.setString(2, remark);
			ps.setInt(3, uid);
			ps.setString(4, subject);
			
			int i = ps.executeUpdate();
			
			if(i==1) {
				String path = getServletContext().getRealPath("")+"file";
				
				System.out.println(path);
				
				File file = new File(path);
				
				p.write(path+File.separator+fileName);
				
				resp.sendRedirect("showFile.jsp");
			}
					
					
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
