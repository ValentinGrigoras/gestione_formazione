package com.academy.businesscomponent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.academy.architecture.dao.DAOException;
import com.academy.architecture.dao.DBAccess;
import com.academy.architecture.dao.DocentiDAO;
import com.academy.businesscomponent.model.Docenti;

public class DocentiBC {
	private Connection conn;
	
	public DocentiBC() throws DAOException, ClassNotFoundException, IOException {
		conn = DBAccess.getConnection();
	}
	
	public void create(Docenti docente) throws DAOException, ClassNotFoundException, IOException {
		try {
			DocentiDAO.getFactory().create(conn, docente);
			
		} catch(SQLException sql) {
			throw new DAOException(sql);
		}
	}
	
	public void delete(long id) throws DAOException {
		try {
			//DocentiDAO.getFactory().delete(conn, id);
			DocentiDAO.getFactory().delete(conn, getById(id));
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}
	public Docenti getById(long id) throws DAOException {
		try {
			return DocentiDAO.getFactory().getById(conn, id);
		} catch(SQLException sql) {
			throw new DAOException(sql);
		}
	}
	
	public Docenti[] getDocenti() throws DAOException {
		try {
			return DocentiDAO.getFactory().getAll(conn);
		} catch(SQLException sql) {
			throw new DAOException(sql);
		}
	}

}
