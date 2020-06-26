package com.academy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.academy.architecture.dao.DAOException;
import com.academy.businesscomponent.facade.AmministratoreFacade;
import com.academy.businesscomponent.model.Corsisti;

@WebServlet("/inserisciCorsista")
public class InserisciCorsista extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Corsisti corsista = new Corsisti();
		String nomeCorsista = (String) request.getParameter("nome");
		String cognomeCorsista = (String) request.getParameter("cognome");
		byte precFormativi = Byte.parseByte(request.getParameter("precFormativi"));

		if (nomeCorsista != null && !nomeCorsista.equals("") && cognomeCorsista != null
				&& !cognomeCorsista.equals("")) {

			corsista.setNomeCorsista(nomeCorsista);
			corsista.setCognomeCorsista(cognomeCorsista);
			corsista.setPrecedentiFormativi(precFormativi);
			try {
				AmministratoreFacade.getInstance().createOrUpdateCorsisti(corsista);
				session.setAttribute("corsista", corsista);
			} catch (DAOException | ClassNotFoundException | IOException e) {
				throw new ServletException(e.getMessage());
			}
		}
		response.sendRedirect("home.jsp");
	}
}
