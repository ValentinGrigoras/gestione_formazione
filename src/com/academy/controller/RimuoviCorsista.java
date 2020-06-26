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

@WebServlet("/rimuoviCorsista")
public class RimuoviCorsista extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		long codCorsista = Long.parseLong(request.getParameter("cod"));
		try {
			if (codCorsista != 0) {
				AmministratoreFacade.getInstance().deleteCorsisti(codCorsista);
				Corsisti corsista = new Corsisti();
				corsista.setCodCorsista(0L);
				session.setAttribute("corsista", corsista);
			}
			response.sendRedirect("home.jsp");
		} catch (DAOException | ClassNotFoundException | IOException exc) {
			exc.printStackTrace();
			throw new ServletException(exc.getMessage());
		}
	}

}
