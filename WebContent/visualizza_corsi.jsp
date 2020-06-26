<%@page import="com.academy.businesscomponent.model.Docenti"%>
<%
	//recupero nome su cui ho cliccato
String id = request.getParameter("cod");
if (id != null && !id.isEmpty() && session.getAttribute("username") != null) {
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.academy.businesscomponent.model.Corsi"%>
<%@page import="com.academy.businesscomponent.model.Corsisti"%>
<%@page
	import="com.academy.businesscomponent.facade.AmministratoreFacade"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="cdn.html"%>
<title>Visualizza corsi</title>
</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="container">
		<div class="page-header">
			<h3>
				Riepilogo corsi di
				<%
				Corsi[] corsi = AmministratoreFacade.getInstance().getCorsiByCodCorsista(Long.parseLong(id));
			Corsisti c = AmministratoreFacade.getInstance().getCorsistaById(Long.parseLong(id));
			%>

				<%=c.getNomeCorsista() + " " + c.getCognomeCorsista()%>
			</h3>

		</div>
		<%
			if (corsi.length != 0) {
		%>
		<div class="panel panel-default" style="margin-top: 50px;">
			<div class="panel-heading">
				<h4>
					<strong>Iscritto a</strong>
				</h4>
			</div>
			<div class="panel-body">
				<div class="table-responsive scrollable-tab">
					<table class="table table-hover" style="width: 100%">
						<thead>
							<tr>
								<th style="width: 200px;">Codice</th>
								<th style="width: 200px;">Nome</th>
								<th style="width: 200px;">Data inizio</th>
								<th style="width: 200px;">Data fine</th>
								<th style="width: 200px;">Costo</th>
								<th style="width: 200px;">Commenti</th>
								<th style="width: 200px;">Aula</th>
								<th style="width: 200px;">Docente</th>
							</tr>
						</thead>
						<tbody>
							<%
								String pattern = "dd/MM/yyyy";
							SimpleDateFormat formatoData = new SimpleDateFormat(pattern);
							Docenti docente = new Docenti();
							for (int i = 0; i < corsi.length; i++) {
								docente = AmministratoreFacade.getInstance().getDocentiByID(corsi[i].getCodDocente());
							%>
							<tr>
								<td><%=corsi[i].getCodCorso()%></td>
								<td><%=corsi[i].getNomeCorso()%></td>
								<td><%=formatoData.format(corsi[i].getDataInizioCorso())%></td>
								<td><%=formatoData.format(corsi[i].getDataFineCorso())%></td>
								<td><%=String.format("%.2f", corsi[i].getCostoCorso())%>&nbsp;&euro;</td>
								<td><%=corsi[i].getCommentiCorso()%></td>
								<td><%=corsi[i].getAulaCorso()%></td>
								<td><%=docente.getNomeDocente() + " " + docente.getCognomeDocente()%></td>
								<td>
									<form
										action="/<%=application.getServletContextName()%>/rimuoviIscrizione?idcorso=<%=corsi[i].getCodCorso()%>&cod=<%=id%>"
										method="post">

										<button type="submit" class="btn btn-danger btn-xs">Rimuovi</button>
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
		<h4>Il corsista non è iscritto a nessun corso!</h4>
		<%
			}
		%>

		<input type="button" class="btn btn-default" value="Indietro"
			onclick="window.history.back()"> <a
			class="btn btn-info mobile-button mobile-link-btn" href="home.jsp"
			role="button">Torna alla Home Page &nbsp;<span
			class="glyphicon glyphicon-home"></span>
		</a>
	</div>
	<footer class="footer">
		<div class="container">
			<%@ include file="footer.html"%>
		</div>
	</footer>
</body>
</html>
<%
	} else {
	response.sendRedirect("login.jsp");
}
%>