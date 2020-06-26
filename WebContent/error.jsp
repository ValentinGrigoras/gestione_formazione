<%@page import="java.io.PrintWriter"%>
<%@page import="com.academy.architecture.dao.DAOException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="cdn.html"%>
<title>Error</title>
</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="container">

		<div class="page-header">
			<h3>Si &egrave; verificato un problema interno al server</h3>
		</div>

		<div class="panel panel-danger">
			<div class="panel-heading">
				<%
					String exc;
				if (exception instanceof ClassNotFoundException) {
					exc = exception.getMessage();
				%>
				<h4>Driver non trovato</h4>
				<%
					} else if (exception instanceof ServletException) {
					exc = exception.getMessage();
				%>
				<h4>Errore del controller</h4>
				<%
					} else if (exception instanceof DAOException) {
					exc = ((DAOException) exception).getMessage();
				%>
				<h4>Eccezione SQL</h4>
				<%
					} else {
					exc = exception.getMessage();
				%>
				<h4>
					Eccezione:&nbsp;<%=exception.getClass().getSimpleName()%></h4>

				<%
					}
				%>
			</div>
			<div class="panel-body">
				<p>
					Motivo:
					<%=exc%></p>
				<p>Per segnalare problema contattare l'amministratore</p>
				<a href="mailto:admin@tin.it">admin@tin.it</a><br><br>
				<p>
					<%
						exception.printStackTrace(new PrintWriter(out));
					%>
				</p>

				<div style="margin-top: 50px;">
					<input type="button" class="btn btn-default mobile-button"
						value="Indietro" onclick="window.history.back()"> <a
						class="btn btn-info mobile-button mobile-link-btn" href="home.jsp"
						role="button"><i class="glyphicon glyphicon-home"></i>&nbsp;Home</a>
				</div>
			</div>
		</div>

	</div>
	<footer class="footer"><%@include file="footer.html"%></footer>
</body>
</html>