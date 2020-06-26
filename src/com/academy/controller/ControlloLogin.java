package com.academy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.academy.architecture.dao.DAOException;
import com.academy.businesscomponent.security.ConvertiMD5;
import com.academy.businesscomponent.utilities.LoginUtility;

@WebServlet("/controlloLogin")
public class ControlloLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		String password = ConvertiMD5.generaMD5(request.getParameter("password"));

		if (username != null && password != null) {
			try {
				LoginUtility loginUtility = new LoginUtility();
				String adminPassword = loginUtility.getPass(username);

				if (adminPassword != null && adminPassword.equals(password)) {
					session.setAttribute("username", username);
					session.removeAttribute("tentativi.jsp");
					response.sendRedirect("home.jsp");
					return;
				}
				Integer tentativi = (Integer) session.getAttribute("tentativi");
				if (tentativi == null) {
					response.sendRedirect("login.jsp");
					return;
				}

				session.setAttribute("tentativi", tentativi + 1);

				if (tentativi.equals(5))
					response.sendRedirect("errorTentativi.jsp");
				else {
					response.sendRedirect("login.jsp");
				}
			} catch (DAOException | ClassNotFoundException e) {
				e.printStackTrace();
				throw new ServletException(e.getMessage());
			}
		}
	}
}
