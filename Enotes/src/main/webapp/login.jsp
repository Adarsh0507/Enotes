<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
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
					<h4>Login Page</h4>
				</div>


				<%
				String invalidMsg = (String) session.getAttribute("login-failed");
				if (invalidMsg != null) {
				%><div class="alert alert-danger" role="alert"><%=invalidMsg%></div>
				<%
				session.removeAttribute("login-failed");
				}
				%>

				<%
				String withoutLogin = (String) session.getAttribute("login-error");
				if (withoutLogin != null) {
				%>

				<div class="alert alert-danger" role="alert"><%=withoutLogin%></div>

				<%
				session.removeAttribute("login-error");
				}
				%>
				<%
				String lgMsg = (String)session.getAttribute("logoutmsg");
				if(lgMsg != null){%>
				
				
				<div class="alert alert-success" role="alert"><%=lgMsg%></div>
				
				
				<%
				session.removeAttribute("logoutmsg");
				}
				%>

				<div class="card-body">
					<form action="loginServlet" method="post">
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

						<button type="submit" class="btn btn-primary badge-pill btn-block">Login</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@include file="all_component/footer.jsp"%>
</body>
</html>