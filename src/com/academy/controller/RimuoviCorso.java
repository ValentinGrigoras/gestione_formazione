package com.academy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.academy.architecture.dao.DAOException;
import com.academy.businesscomponent.facade.AmministratoreFacade;

@WebServlet("/rimuoviCorso")
public class RimuoviCorso extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("cod");
		try {
			if(id!=null && !id.isEmpty()) {
				AmministratoreFacade.getInstance().deleteCorso(Long.parseLong(id));
			}
			response.sendRedirect("rimuovicorso.jsp");
		}catch(DAOException | ClassNotFoundException | IOException exc ) {
			exc.printStackTrace();
			throw new ServletException(exc.getMessage());
		}
	}
}
