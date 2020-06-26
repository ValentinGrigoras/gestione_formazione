<%@page import="com.academy.businesscomponent.model.Docenti"%>
<%@page import="java.text.SimpleDateFormat"%>
<% 
	String username = (String) session.getAttribute("username");
if (username == null) {
	response.sendRedirect("login.jsp");

}
%>
<!DOCTYPE html>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.academy.businesscomponent.facade.AmministratoreFacade"%>
<%@page import="com.academy.businesscomponent.model.Corsi"%>
<%
String pattern = "dd/MM/yyyy";
SimpleDateFormat formato = new SimpleDateFormat(pattern);
%>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="cdn.html"%>
<title>Riepilogo corsi futuri</title>
</head>
<body>
	<jsp:include page="nav.jsp" />
	<jsp:include page="inserisciCorsistaModal.jsp" />
		<div class="container">
			<div class="page-header">
				<h3>Riepilogo corsi futuri</h3>
			</div>
			
			<%
			Corsi[] c = AmministratoreFacade.getInstance().getCorsiByDate(new Date());
			if(c.length != 0) {
			%>
			
			<div class="panel panel-default" style="margin-top: 50px;">
				<div class="panel-heading">
					<h4>
						<strong>Corsi</strong>
					</h4>
				</div>
				<div class="panel-body">


					<div class="table-responsive scrollable-tab">
						<table class="table table-hover" style="width: 100%;">
							<thead>
								<tr>
									<th style="width: 200px;">Cod</th>
									<th style="width: 200px;">Nome</th>
									<th style="width: 200px;">Data inizio</th>
									<th style="width: 200px;">Data fine</th>
									<th style="width: 200px;">Costo</th>
									<th style="width: 200px;">Commenti</th>
									<th style="width: 200px;">Aula</th>
									<th style="width: 200px;">Docente</th>
									<th style="width: 200px;">Rimuovi</th>
								</tr>
							</thead>
							<tbody>
								<%
								Docenti docente = new Docenti();
								for (int i = 0; i < c.length; i++) {
									docente = AmministratoreFacade.getInstance().getDocentiByID(c[i].getCodDocente());
								%>
								<tr>
									<td><%=c[i].getCodCorso()%></td>
									<td><%=c[i].getNomeCorso()%></td>
									<td><%=formato.format(c[i].getDataInizioCorso())%></td>
									<td><%=formato.format(c[i].getDataFineCorso())%></td>
									<td><%=String.format("%.2f", c[i].getCostoCorso())%>&nbsp;&euro;</td>
									<td><%=c[i].getCommentiCorso()%></td>
									<td><%=c[i].getAulaCorso()%></td>
									<td><%=docente.getNomeDocente() + " " + docente.getCognomeDocente()%></td>
									<td>
										<form
											action="/<%=application.getServletContextName()%>/rimuoviCorso?cod=<%=c[i].getCodCorso()%>"
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
			
			<%
			} else {
			%>
				<h4>Non ci sono corsi che devono ancora cominciare!</h4>
			<%
			}
			%>


		<input type="button" class="btn btn-default" value="Indietro"
			onclick="window.history.back()">
		<a class="btn btn-info mobile-button mobile-link-btn" href="home.jsp" role="button">Torna alla Home Page
				&nbsp;<span class="glyphicon glyphicon-home"></span>
			</a>
		</div>
		<footer class="footer">
		<div class="container">
			<%@ include file="footer.html"%>
		</div>
	</footer>
</body>
</html>