
<%
	String username = (String) session.getAttribute("username");
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error 404</title>
<%@include file="cdn.html"%>
<link href="https://fonts.googleapis.com/css?family=Poppins:400,700"
	rel="stylesheet">
<link rel="stylesheet" href="css/errorstyle.css">
</head>
<body>
	<jsp:include page="nav.jsp" />

	<main class="main">
		<div class="error">404</div>
		<img src="img/404dog.png" />
		<h2>Risorsa non disponibile</h2>
		<h6>
			Siamo spiacenti, risolveremo al più presto.<br /> Per segnalare
			problema contattare l'amministratore<br /> <a
				href="mailto:admin@tin.it">admin@tin.it</a>
		</h6>
		<%
			if (username != null) {
		%>
		<div>
			<input type="button" class="btn btn-default mobile-button"
				value="Indietro" onclick="window.history.back()"> <a
				class="btn btn-info mobile-button mobile-link-btn" href="home.jsp"
				role="button"><i class="glyphicon glyphicon-home"></i>&nbsp;Home</a>
		</div>
		<%
			}
		%>
	</main>

	<footer class="footer">
		<div class="container">
			<%@ include file="footer.html"%>
		</div>
	</footer>
</body>
</html>