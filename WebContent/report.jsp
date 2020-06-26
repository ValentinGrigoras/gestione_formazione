
<%
	String username = (String) session.getAttribute("username");
if (username == null) {
	response.sendRedirect("login.jsp");
}
%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.List"%>
<%@page
	import="com.academy.businesscomponent.utilities.StatisticheUtility"%>
<%@page import="com.academy.businesscomponent.model.Corsi"%>
<%@page import="com.academy.businesscomponent.model.Docenti"%>
<%@page import="com.academy.businesscomponent.model.Corsisti"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page
	import="com.academy.businesscomponent.facade.AmministratoreFacade"%>
<%@page import="com.academy.architecture.dao.DAOException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	StatisticheUtility statistiche = new StatisticheUtility();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="cdn.html"%>
<title>Report</title>
<script src="js/validazione.js"></script>
</head>
<body id="login">
	<jsp:include page="nav.jsp" />
	<div class="container">
		<div class="page-header">
			<h3>Report Statistiche</h3>
		</div>
		<div class="table-responsive ">
			<table style="width: 100%;" class="table table-hover table-bordered">
				<tr>
					<!-- numero corsisti -->
					<td width="40%" class="text-right">
						<p style="font-weight: bold;">Numero corsisti totali</p>
					</td>
					<td width="60%">
						<p class="font-weight-bold"><%=statistiche.getNumeroCorsisti()%></p>
					</td>
				</tr>
				<tr>
					<!-- nome corso + frequentato -->
					<td width="40%" class="text-right">
						<p style="font-weight: bold;">Corso pi&ugrave; frequentato</p>
					</td>
					<td width="60%">
						<%
							Vector<String[]> corsi = statistiche.getCorsoPiuFrequentato();
						if (corsi.size() > 0) {
							for (String[] corso : corsi) {
						%>
						<p class="font-weight-bold"><%=corso[0] + " (" + corso[1] + " iscritti)"%></p>
						<%
							}
						} else {
						%>
						<p class="font-weight-bold">Nessun dato</p> <%
 	}
 %>
					</td>
				</tr>
				<tr>
					<!-- data inizio ultimo corso -->
					<td width="40%" class="text-right">
						<p style="font-weight: bold;">Data di inizio dell'ultimo corso</p>
					</td>
					<td width="60%">
						<%
							SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
						Corsi ultimo = statistiche.getUltimoCorsoIniziato();
						if (ultimo != null) {
						%>
						<p class="font-weight-bold">
							<%=formatoData.format(ultimo.getDataInizioCorso()) + " (" + ultimo.getNomeCorso() + ")"%>
						</p> <%
 	} else {
 %>
						<p class="font-weight-bold">Nessun dato</p> <%
 	}
 %>

					</td>
				</tr>

				<tr>
					<!-- durata media dei corsi -->
					<td width="40%" class="text-right">
						<p style="font-weight: bold;">Durata media dei corsi</p>
					</td>
					<td width="60%">
						<p class="font-weight-bold">
							<%=statistiche.getDurataMediaCorsi()+" giorni"%>
						</p>
					</td>
				</tr>

				<tr>
					<!-- numero commenti presenti -->
					<td width="40%" class="text-right">
						<p style="font-weight: bold;">Numero commenti presenti</p>
					</td>
					<td width="60%">
						<p class="font-weight-bold">
							<%=statistiche.getNumeroCommenti()%>
						</p>
					</td>
				</tr>

				<tr>
					<td width="40%" class="text-right">
						<p style="font-weight: bold;">Elenco corsisti</p>
					</td>
					<td width="60%">
						<p class="font-weight-bold">
							<a href="#elencocorsisti">Vai alla tabella</a>
						</p>
					</td>
				</tr>

				<tr>
					<td width="40%" class="text-right">
						<p style="font-weight: bold;">Elenco docenti con pi&ugrave; di
							un corso</p>
					</td>
					<td width="60%">
						<p class="font-weight-bold">
							<a href="#docentipiucorso">Vai alla tabella</a>
						</p>
					</td>
				</tr>

				<tr>
					<td width="40%" class="text-right">
						<p style="font-weight: bold;">Elenco corsi con posti
							disponibili</p>
					</td>
					<td width="60%">
						<p class="font-weight-bold">
							<a href="#postidisponibili">Vai alla tabella</a>
						</p>
					</td>
				</tr>
			</table>
		</div>

		<div class="panel panel-default" style="margin-top: 50px;">
			<div class="panel-heading">
				<h4 id="elencocorsisti">
					<strong>Elenco corsisti</strong>
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
							</tr>
						</thead>
						<tbody>
							<%
								Corsisti[] c = AmministratoreFacade.getInstance().getCorsisti();
							if (c.length > 0) {
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
							</tr>
							<%
								}
							} else {
							%>
							<tr>
								<td colspan="4"><p class="font-weight-bold">Nessun dato</p></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>


		<div class="panel panel-default" style="margin-top: 50px;">
			<div class="panel-heading">
				<h4 id="docentipiucorso">
					<strong>Elenco docenti con pi&ugrave; di un corso</strong>
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
								<th style="width: 200px;">Curriculum Vitae</th>
							</tr>
						</thead>
						<tbody>
							<%
								List<Docenti> docenti = statistiche.getDecentiPiuCorsi();
							if (docenti.size() > 0) {
								for (Docenti d : docenti) {
							%>
							<tr>
								<td><%=d.getNomeDocente()%></td>
								<td><%=d.getCognomeDocente()%></td>
								<td><%=d.getCodDocente()%></td>
								<td><a style="width: 90%; margin-left: 5%; margin-right: 5%;" class="btn btn-primary" href="<%=d.getCvDocente()%>"
									role="button" target="_blank"><i
										class="glyphicon glyphicon-briefcase"></i>&nbsp;CV&nbsp;<%=d.getNomeDocente() + " " + d.getCognomeDocente()%></a></td>
							</tr>
							<%
								}
							} else {
							%>
							<tr>
								<td colspan="4"><p class="font-weight-bold">Nessun dato</p></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<div class="panel panel-default" style="margin-top: 50px;">
			<div class="panel-heading">
				<h4 id="postidisponibili">
					<strong>Elenco corsi con posti disponibili</strong>
				</h4>
			</div>
			<div class="panel-body">
				<div class="table-responsive scrollable-tab">
					<table class="table table-hover" style="width: 100%;">
						<thead>
							<tr>
								<th style="width: 200px;">Iscritti attuali</th>
								<th style="width: 200px;">Posti disponibili</th>
								<th style="width: 200px;">Corso</th>
								<th style="width: 200px;">Data inizio</th>
								<th style="width: 200px;">Data fine</th>
								<th style="width: 200px;">Costo</th>
								<th style="width: 200px;">Commenti</th>
								<th style="width: 200px;">Aula</th>
								<th style="width: 200px;">Docente</th>
								<th style="width: 200px;">Codice</th>
							</tr>
						</thead>
						<tbody>
							<%
								Vector<String[]> corsiPosti = statistiche.getCorsiPostiDisponibili();
							if (corsiPosti.size() > 0) {
								for (String[] d : corsiPosti) {
									Corsi corso = AmministratoreFacade.getInstance().getCorsiByCod(Long.parseLong(d[0]));
									Docenti docente = AmministratoreFacade.getInstance().getDocentiByID(corso.getCodDocente());
									int disponibili = 12 - (Integer.parseInt(d[1]));
							%>
							<tr>
								<th scope="row" class="text-center"><%=d[1]%></th>
								<th scope="row" class="text-center"><%=disponibili%></th>
								<td><%=corso.getNomeCorso()%></td>
								<td><%=formatoData.format(corso.getDataInizioCorso())%></td>
								<td><%=formatoData.format(corso.getDataFineCorso())%></td>
								<td><%=String.format("%.2f", corso.getCostoCorso())%>&nbsp;&euro;</td>
								<td><%=corso.getCommentiCorso()%></td>
								<td><%=corso.getAulaCorso()%></td>
								<td><%=docente.getNomeDocente() + " " + docente.getCognomeDocente()%></td>
								<td><%=corso.getCodCorso()%></td>
							</tr>
							<%
								}
							} else {
							%>
							<tr>
								<td colspan="10"><p class="font-weight-bold">Nessun
										dato</p></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>


		<div style="margin-top: 50px;">
			<input type="button" class="btn btn-default mobile-button" value="Indietro"
				onclick="window.history.back()"> <a
				class="btn btn-info mobile-button mobile-link-btn" href="home.jsp"
				role="button"><i class="glyphicon glyphicon-home"></i>&nbsp;Torna
				alla Home Page </a>
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