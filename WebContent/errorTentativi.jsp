<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	if (session.getAttribute("username") == null && session.getAttribute("tentativi") != null) {
	Integer tentativi = (Integer) session.getAttribute("tentativi");
	if (tentativi >= 5) {
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="cdn.html"%>
<title>ErroreTentativi</title>
</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="container">

		<div class="page-header">
			<h3>Credenziali insirite errate</h3>
		</div>
		<div class="panel panel-danger">
			<div class="panel-heading">
				<h4>Numero tentativi esauriti per effettuare il login</h4>
			</div>
		</div>
		<div class="panel-body">
			<p>Controlla le tue credenziali e riprova pi&ugrave; tardi.</p>
			<a class="btn btn-default mobile-button mobile-link-btn" href="login.jsp" role="button">Login</a>
			<br>
			<br>
		</div>
	</div>
	<footer class="footer">
		<div class="container">
			<%@ include file="footer.html"%>
		</div>
	</footer>
</body>
</html>

<%
	}
} else {
response.sendRedirect("home.jsp");
}
%>