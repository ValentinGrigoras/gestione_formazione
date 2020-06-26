package com.academy.businesscomponent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import com.academy.architecture.dao.CorsiDAO;
import com.academy.architecture.dao.DAOException;
import com.academy.architecture.dao.DBAccess;
import com.academy.businesscomponent.idgenerator.CorsiIdGenerator;
import com.academy.businesscomponent.model.Corsi;

public class CorsiBC {
	private Connection conn;
	private CorsiIdGenerator idGen;

	public CorsiBC() throws DAOException, ClassNotFoundException, IOException {
		conn = DBAccess.getConnection();
		idGen = CorsiIdGenerator.getInstance();
	}

	public Corsi[] getCorsi() throws DAOException {
		return CorsiDAO.getFactory().getAll(conn);

	}

	public Corsi[] getCorsiByCodCorsista(long id) throws DAOException {
		return CorsiDAO.getFactory().getByCodCorsista(conn, id);

	}

	public void createOrUpdate(Corsi corso) throws DAOException, ClassNotFoundException, IOException {
		try {
			if (corso.getCodCorso() > 0)
				CorsiDAO.getFactory().update(conn, corso);
			else {
				corso.setCodCorso(idGen.getNextId());
				CorsiDAO.getFactory().create(conn, corso);
			}
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	public void delete(long id) throws DAOException {
		try {
			CorsiDAO.getFactory().delete(conn, this.getCorsiById(id));
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	public Corsi getCorsiById(long id) throws DAOException {
		return CorsiDAO.getFactory().getById(conn, id);
	}
	
	public Corsi[] getCorsiByDate(Date date) throws DAOException {
		return CorsiDAO.getFactory().getByDate(conn, date);
	}

}
