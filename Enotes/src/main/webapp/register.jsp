<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="ISO-8859-1">
<title>Register Page</title>
<%@include file="all_component/allcss.jsp"%>

</head>
<body>
	<%@include file="all_component/navbar.jsp"%>
	<div class="continer-fluid  div-color">
		<div class="row">
			<div class="col-md-1 offset-md-4"></div>
			<div class="card mt-5">
				<div class="card-header text-center text-white bg-custom">
					<i class="fa fa-user-plus fa-3x" aria-hidden="true"></i>
					<h4>Registration</h4>
				</div>




				<%
					String regMsg=(String)session.getAttribute("reg-sucess");
					if(regMsg!=null){%>
				<div class="alert alert-success" role="alert">
					<%=regMsg%>Login<a href="login.jsp">Click Here </a>
				</div>
				<%
				session.removeAttribute("reg-sucess");
					}
					
					
					%>

				<%
					String FailedMsg=(String)session.getAttribute("failed-sucess");
					if(FailedMsg!=null){%>
				<div class="alert alert-danger" role="alert"><%=FailedMsg %></div>
				<% 
				session.removeAttribute("failed-sucess");
					}%>
				
				
				
				<div class="card-body">
					<form action="UserServlet" method="post">
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Enter
								Full Name </label> <input type="text" class="form-control"
								id="exampleInputEmail1" aria-describedby="emailHelp"
								name="fname">

						</div>
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">Email
								address</label> <input type="email" class="form-control"
								id="exampleInputEmail1" aria-describedby="emailHelp"
								name="uemail">

						</div>
						<div class="mb-3">
							<label for="exampleInputPassword1" class="form-label">Password</label>
							<input type="password" class="form-control"
								id="exampleInputPassword1" name="upassword">
						</div>

						<button type="submit" class="btn btn-primary badge-pill btn-block">Register</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@include file="all_component/footer.jsp"%>
</body>
</html>