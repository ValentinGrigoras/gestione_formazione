package com.academy.businesscomponent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.academy.architecture.dao.DAOException;
import com.academy.architecture.dao.DBAccess;
import com.academy.architecture.dao.IscrizioniDAO;
import com.academy.businesscomponent.model.Iscrizioni;

public class IscrizioniBC {
	private Connection conn;

	public IscrizioniBC() throws DAOException, ClassNotFoundException, IOException {
		conn = DBAccess.getConnection();
	}

	public void create(Iscrizioni iscrizione) throws DAOException {
		try {
			IscrizioniDAO.getFactory().create(conn, iscrizione);
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	public Iscrizioni[] getIscrizioni() throws DAOException {
		try {
			return IscrizioniDAO.getFactory().getAll(conn);
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	public void delete(long idCorso, long idCorsista) throws DAOException {
		try {
			Iscrizioni iscrizione = new Iscrizioni();
			iscrizione.setCodCorso(idCorso);
			iscrizione.setCodCorsista(idCorsista);
			IscrizioniDAO.getFactory().delete(conn, iscrizione);
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

}
