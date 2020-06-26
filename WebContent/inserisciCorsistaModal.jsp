
<%
	 	String username = (String) session.getAttribute("username");
		 if (username == null)
		 	response.sendRedirect("login.jsp");
%>
<%@page import="com.academy.businesscomponent.model.Corsisti"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<div class="modal fade" id="insertModal" role="dialog">
	<div class="modal-dialog modal-md">
		<form
			action="/<%=application.getServletContextName()%>/inserisciCorsista"
			method="post" id="insertForm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Aggiungi corsista</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="nome">Nome</label> <input type="text"
							class="form-control" name="nome">
					</div>
					<div class="control-label" id="infoNome"></div>
					<div class="form-group">
						<label for="cognome">Cognome</label> <input type="text"
							class="form-control" name="cognome">
						<div class="control-label" id="infoCognome"></div>
					</div>
					<div class="form-group">
						<label for="precFormativi">Precedenti formativi</label>
						<div class="custom-control custom-radio custom-control-inline">
							<input type="radio" class="custom-control-input" id="radio1"
								name="precFormativi" value="1"> <label
								class="custom-control-label" for="radio1">Si</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
							<input type="radio" class="custom-control-input" id="radio2"
								name="precFormativi" value="0" checked> <label
								class="custom-control-label" for="radio2">No</label>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-success">Conferma</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Annulla</button>
				</div>
			</div>
		</form>
	</div>
</div>