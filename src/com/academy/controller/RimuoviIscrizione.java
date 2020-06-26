package com.academy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.academy.architecture.dao.DAOException;
import com.academy.businesscomponent.facade.AmministratoreFacade;

@WebServlet("/rimuoviIscrizione")
public class RimuoviIscrizione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long codCorso = Long.parseLong(request.getParameter("idcorso"));
		long codCorsista = Long.parseLong(request.getParameter("cod"));
		
		try {
			if(codCorso != 0 && codCorsista != 0) {
				AmministratoreFacade.getInstance().deleteIscrizioni(codCorso, codCorsista);;
			}
			request.setAttribute("cod", request.getAttribute("cod"));
			response.sendRedirect("visualizza_corsi.jsp?cod=" + request.getParameter("cod"));
		}catch(DAOException | ClassNotFoundException | IOException exc ) {
			exc.printStackTrace();
			throw new ServletException(exc.getMessage());
		}
		
	}

}
