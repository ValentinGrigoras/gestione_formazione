<%@page import="com.academy.architecture.dao.DAOException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Integer tentativi = (Integer) session.getAttribute("tentativi");

if (tentativi == null) {
	session.setAttribute("tentativi", 0);
	tentativi = (Integer) session.getAttribute("tentativi");
} else {
	if (tentativi >= 5) {
		response.sendRedirect("errorTentativi.jsp");
	}
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="cdn.html"%>
<title>Log In</title>
<script src="js/validazione.js"></script>
</head>
<body id="login">

	<jsp:include page="nav.jsp" />
	<div class="container">

		<div class="container login ">

			<div class="row">
				<div class="page-header">
					<h3>Login amministratore</h3>
				</div>

				<form
					action="/<%=application.getServletContextName()%>/controlloLogin"
					method="post" id="userform"
					class="form-horizontal bg-secondary text-white">

					<div class="form-group">
						<label class="col-md-4 control-label">Username</label>
						<div class="col-md-8 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-user"></i>
								</span> <input type="text" name="username" id="username"
									placeholder="Inserisci Username" class="form-control">
							</div>
							<div class="col-md-7 control-label" id="infoUsername"></div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label">Password</label>
						<div class="col-md-8 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-lock"></i>
								</span> <input type="password" name="password" id="password"
									placeholder="Inserisci Password" class="form-control"
									autocomplete="new-password">
							</div>
							<div class="col-md-7 control-label" id="infoPassword"></div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-4 col-md-offset-1">
							<button type="submit" class="btn btn-light">
								Log In <span class="glyphicon glyphicon-log-in"></span>
							</button>
						</div>
					</div>


				</form>
				<%
					if (tentativi > 0) {
				%>
				<div class="alert alert-danger error-msg" role="alert">
					Username o password errati, hai ancora
					<%=5 - tentativi%>
					tentativi
				</div>
				<%
					}
				%>
			</div>

		</div>


	</div>
	<footer class="footer">
		<div class="container">
			<%@ include file="footer.html"%>
		</div>
	</footer>
</body>
</html>