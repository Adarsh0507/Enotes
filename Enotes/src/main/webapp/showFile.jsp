<%@page import="com.User.UserDetails"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
UserDetails user1 = (UserDetails) session.getAttribute("userD");

if (user1 == null) {
	response.sendRedirect("login.jsp");
	session.setAttribute("login-error", "Please Login!!");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show File</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<%@ include file="all_component/allcss.jsp"%>
</head>
<body>

	<%
	UserDetails us = (UserDetails) session.getAttribute("userD");
	if (us != null) {
	%>
	<input type="hidden" value="<%=us.getId()%>" name="uid">
	<%
	}
	%>

	<%@include file="all_component/navbar.jsp"%>
	
	<%
		String subject = request.getParameter("subject");
	
		
		
		
	%>
	
	<div class="container text-center my-2">
		<form action="showFile.jsp" class="form-inline">
			<div class="form-group">
				<input type = "text" class="form-control" placeholder="Search..." name="subject">
			</div>
			<button type="submit" class="btn btn-primary mx-1">Search</button>
		</form>
	
	</div>

	<div class="container-fluid">

		<table class="table">
			<thead>
				<tr>
					<th scope="col">File</th>
					<th scope="col">File Type</th>
					<th scope="col">Remark</th>
					<th scope="col">Actions</th>


				</tr>
			</thead>
			
			<% if(subject == null){%>
			<tbody>

				<%
				Connection conn = DBConnect.getConn();
				PreparedStatement ps = conn.prepareStatement("select * from file_details where user = ?  order by id desc");
				ps.setInt(1, us.getId());
			

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
				%>
				<tr>

					<%
					if (rs.getString("file_name").endsWith(".pdf")) {
					%>
					<th scope="row"><img alt="" src="file/pdf.jpg" width="200px"
						height="150px"></th>
					<td>PDF</td>
					<%
					} else if (rs.getString("file_name").endsWith(".java")) {
					%>
					<th scope="row"><img alt="" src="file/java.png" width="200px"
						height="150px"></th>
					<td>Java file</td>
					<%
					} else if (rs.getString("file_name").endsWith(".txt")) {
					%>
					<th scope="row"><img alt="" src="file/txt.png" width="200px"
						height="150px"></th>
					<td>Text File</td>
					<%
					} else {
					%>

					<th scope="row"><img alt=""
						src="file/<%=rs.getString("file_name")%>" width="200px"
						height="150px"></th>
					<td>Image</td>
					<%
					}
				
					%>
					
					

					<td><%=rs.getString("remark")%></td>
					<td><a href="DeleteFile?note_id=<%=rs.getString("id")%>"
						class="btn btn-danger">Delete</a> <a
						href="ViewFile?name=<%=rs.getString("file_name")%>"
						class="btn btn-primary">View</a> <a
						href="ShareFiles?id=<%=rs.getString("id")%>"
						class="btn btn-primary">Share</a></td>
				</tr>
				
				</tbody>
				<%
				}
			}else{%>
			
						<tbody>

				<%
				Connection conn = DBConnect.getConn();
				PreparedStatement ps = conn.prepareStatement("select * from file_details where user = ? and subject =? order by id desc");
				ps.setInt(1, us.getId());
				ps.setString(2, subject);

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
				%>
				<tr>

					<%
					if (rs.getString("file_name").endsWith(".pdf")) {
					%>
					<th scope="row"><img alt="" src="file/pdf.jpg" width="200px"
						height="150px"></th>
					<td>PDF</td>
					<%
					} else if (rs.getString("file_name").endsWith(".java")) {
					%>
					<th scope="row"><img alt="" src="file/java.png" width="200px"
						height="150px"></th>
					<td>Java file</td>
					<%
					} else if (rs.getString("file_name").endsWith(".txt")) {
					%>
					<th scope="row"><img alt="" src="file/txt.png" width="200px"
						height="150px"></th>
					<td>Text File</td>
					<%
					} else {
					%>

					<th scope="row"><img alt=""
						src="file/<%=rs.getString("file_name")%>" width="200px"
						height="150px"></th>
					<td>Image</td>
					<%
					}
				
					%>
					
					

					<td><%=rs.getString("remark")%></td>
					<td><a href="DeleteFile?note_id=<%=rs.getString("id")%>"
						class="btn btn-danger">Delete</a> <a
						href="ViewFile?name=<%=rs.getString("file_name")%>"
						class="btn btn-primary">View</a> <a
						href="ShareFiles?id=<%=rs.getString("id")%>"
						class="btn btn-primary">Share</a></td>
				</tr>
				</tbody>
				<%}
				}%>
		</table>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
		integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
		integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
		crossorigin="anonymous"></script>
</body>
</html>