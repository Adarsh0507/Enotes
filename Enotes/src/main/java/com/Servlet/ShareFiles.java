package com.Servlet;

import java.io.IOException;
import java.sql.Connection;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import com.DAO.FileDAO;
import com.DB.DBConnect;

import com.User.FileData;

@WebServlet("/ShareFiles")
public class ShareFiles extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		
		//get data by id 
		Connection conn = DBConnect.getConn();
		FileDAO f = new FileDAO(conn);
		
		FileData ff = f.getFilebyID(id);
		
		String fileName = ff.getFile_name();
		
		String to = "adarshsinghparihar0507@gmail.com";
		
		//get path of file 
		String path = "C:\\Users\\win 10\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Enotes\\file\\"+fileName;
		
		File file = new File(path); 
		
        // Set up the properties for the mail server
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        // Create a session with the properties and authenticate using your email account credentials
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("thakuradarsh195@gmail.com", "ntrwntdcfqjvakte");
            }
        });
        try {
        	Message message = new MimeMessage(session);
        	message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
        	message.setFrom(new InternetAddress("thakuradarsh195@gmail.com"));
        	message.setSubject("File Name is :" + fileName);
        	
        	MimeBodyPart part1 = new MimeBodyPart();
        	part1.setText("you are getting this file by Enotes project");
        	
        	MimeBodyPart part2 = new MimeBodyPart();
        	part2.attachFile(file);
        	
        	MimeMultipart mime = new MimeMultipart();
        	mime.addBodyPart(part1);
        	mime.addBodyPart(part2);
        	
        	message.setContent(mime);
        	
        	Transport.send(message);
        	
        	System.out.print("mail send successfully");
        	resp.sendRedirect("showFile.jsp");
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
       }

}
