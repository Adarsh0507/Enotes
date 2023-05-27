package com.Servlet;

import java.io.IOException;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.PostDAO;
import com.DB.DBConnect;
import com.User.Post;

@WebServlet("/ShareNote")
public class ShareNote extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		PostDAO po = new PostDAO(DBConnect.getConn());
		Post p = po.getDataByID(id);
		
		String title = p.getTitle();
		String body = p.getContent();
		
		
		////////////////////////////////////////
		
		
				String to = "adarshsinghparihar0507@gmail.com";
				
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
		            // Create a new message
		            Message message = new MimeMessage(session);
		            message.setFrom(new InternetAddress("thakuradarsh195@gmail.com"));
		            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		            message.setSubject(title);
		            message.setText(body);

		            // Send the email
		            Transport.send(message);
		            
		            resp.sendRedirect("showNotes.jsp");

		            System.out.println("Email sent successfully!");
		        } catch (MessagingException e) {
		            e.printStackTrace();
		        }
		    }
		

		
		
		
	}


