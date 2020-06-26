<%
	String username = (String) session.getAttribute("username");
if (username == null) {
	response.sendRedirect("login.jsp");

}
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page
	import="com.academy.businesscomponent.facade.AmministratoreFacade"%>
<%@page import="com.academy.businesscomponent.model.Corsisti"%>
<jsp:useBean id="corsista"
	class="com.academy.businesscomponent.model.Corsisti" scope="session" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="cdn.html"%>
<title>Home Page</title>

<script src="js/validazione.js"></script>
<!-- commentare la riga sopra per togliere la validazione nel modal -->

</head>
<body>
	<jsp:include page="nav.jsp" />
	<jsp:include page="inserisciCorsistaModal.jsp" />
		<div class="container">
			<div class="page-header">
				<h3>Home Page</h3>
			</div>

			<button type="button" class="btn btn-success mobile-button" data-toggle="modal"
				data-target="#insertModal">
				Aggiungi corsista&nbsp; <span class="glyphicon glyphicon-plus"></span>
			</button>
			<a class="btn btn-info mobile-button mobile-link-btn" href="report.jsp" role="button">Visualizza
				statistiche&nbsp;<span class="glyphicon glyphicon-stats"></span>
			</a> <a class="btn btn-info mobile-button mobile-link-btn" href="rimuovicorso.jsp" role="button">Visualizza corsi futuri&nbsp;<span class="glyphicon glyphicon-book"></span>
			</a>


			<div class="page-header">
				<h3>Riepilogo</h3>

				<%
					if (corsista.getCodCorsista() != 0L) {
				%>

				<div class="panel panel-info" style="margin-top: 50px;">
					<div class="panel-heading">
						<h4>
							<strong>Corsista appena inserito</strong>
						</h4>
					</div>
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-hover" style="width: 100%;">
								<thead>
									<tr>
										<th style="width: 200px;">Nome</th>
										<th style="width: 200px;">Cognome</th>
										<th style="width: 200px;">Codice</th>
										<th style="width: 200px;">Precedenti Formativi</th>
										<th style="width: 200px;">Rimuovi</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><%=corsista.getNomeCorsista()%></td>
										<td><%=corsista.getCognomeCorsista()%></td>
										<td><%=corsista.getCodCorsista()%></td>
										<td>
											<%
												if (corsista.getPrecedentiFormativi() == 1) {
											%> Si <%
												} else {
											%> No <%
												}
											%>
										</td>
										<td>
											<form
												action="/<%=application.getServletContextName()%>/rimuoviCorsista?cod=<%=corsista.getCodCorsista()%>"
												method="post">
												<button type="submit" class="btn btn-danger btn-sx">Rimuovi</button>
											</form>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>

			</div>
			<%
				}
			%>
			<div class="panel panel-default" style="margin-top: 50px;">
				<div class="panel-heading">
					<h4>
						<strong>Riepilogo corsisti</strong>
					</h4>
				</div>
				<div class="panel-body">
					<div class="table-responsive scrollable-tab">
						<table class="table table-hover" style="width: 100%;">
							<thead>
								<tr>
									<th style="width: 200px;">Nome</th>
									<th style="width: 200px;">Cognome</th>
									<th style="width: 200px;">Codice</th>
									<th style="width: 200px;">Precedenti Formativi</th>
									<th style="width: 200px;">Rimuovi</th>
								</tr>
							</thead>
							<tbody>
								<%
									Corsisti[] c = AmministratoreFacade.getInstance().getCorsisti();
								for (int i = 0; i < c.length; i++) {
								%>
								<tr>
									<td><a
										href="/<%=application.getServletContextName()%>/visualizza_corsi.jsp?cod=<%=c[i].getCodCorsista()%>"><%=c[i].getNomeCorsista()%></a></td>
									<td><%=c[i].getCognomeCorsista()%></td>
									<td><%=c[i].getCodCorsista()%></td>
									<td>
										<%
											if (c[i].getPrecedentiFormativi() == 1) {
										%> Si <%
											} else {
										%> No <%
											}
										%>
									</td>
									<td>
										<form
											action="/<%=application.getServletContextName()%>/rimuoviCorsista?cod=<%=c[i].getCodCorsista()%>"
											method="post">
											<button type="submit" class="btn btn-danger btn-sx">Rimuovi</button>
										</form>
									</td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<a id="back-top" href="#top"><span class="glyphicon glyphicon-circle-arrow-up"></span></a>
	<footer class="footer">
	<div class="container">
		<%@ include file="footer.html"%>
	</div>
	
	</footer>
</body>
</html>
<%
session.removeAttribute("corsista");
%>
<jsp:include page="inserisciCorsistaModal.jsp" />
