
<%
	String username = (String) session.getAttribute("username");
if (username != null) {
	session.invalidate();
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="cdn.html"%>
<meta charset="ISO-8859-1">
<title>Logout</title>
<link href="https://fonts.googleapis.com/css?family=Poppins:400,700"
	rel="stylesheet">
<link rel="stylesheet" href="css/errorstyle.css">
</head>
<body>
	<jsp:include page="nav.jsp" />

	<main class="main">
		<img src="img/out.png" />
		<h2>Arrivederci</h2>
		<h6>
			Hai appena effettuato il Logout<br /> Clicca su Login per effettuare
			nuovamente l'accesso
		</h6>
		<div>
			<a class="btn btn-info mobile-button mobile-link-btn"
				href="login.jsp" role="button"><i
				class="glyphicon glyphicon-log-in"></i>&nbsp;Login</a>
		</div>
	</main>

	<footer class="footer">
		<div class="container">
			<%@ include file="footer.html"%>
		</div>
	</footer>
</body>
<%
	} else {
	response.sendRedirect("error404.jsp");
}
%>