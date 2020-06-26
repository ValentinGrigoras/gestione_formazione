package com.academy.architecture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.academy.businesscomponent.model.Docenti;

public class DocentiDAO extends GenericDAOAdapter<Docenti> implements DAOConstants {
	private CachedRowSet rowSet;

	private DocentiDAO() throws DAOException {
		try {
			rowSet = RowSetProvider.newFactory().createCachedRowSet();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	public static DocentiDAO getFactory() throws DAOException {
		return new DocentiDAO();
	}

	@Override
	public void create(Connection conn, Docenti entity) throws DAOException {
		try {
			rowSet.setCommand(SELECT_DOCENTI);
			rowSet.execute(conn);
			rowSet.moveToInsertRow();
			rowSet.updateString(1, entity.getNomeDocente());
			rowSet.updateString(2, entity.getCognomeDocente());
			rowSet.updateString(3, entity.getCvDocente());
			rowSet.updateLong(4, entity.getCodDocente());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			rowSet.acceptChanges(conn);
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	@Override
	public Docenti getById(Connection conn, long id) throws DAOException {

		Docenti docente = null;

		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(SELECT_DOCENTE_BYKEY);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				docente = new Docenti();
				docente.setNomeDocente(rs.getString(1));
				docente.setCognomeDocente(rs.getString(2));
				docente.setCvDocente(rs.getString(3));
				docente.setCodDocente(rs.getLong(4));
			}
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return docente;
	}

	@Override
	public Docenti[] getAll(Connection conn) throws DAOException {
		Docenti[] docenti = null;
		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(SELECT_DOCENTI);
			rs.last();
			docenti = new Docenti[rs.getRow()];
			rs.beforeFirst();
			for (int i = 0; rs.next(); i++) {
				Docenti temp = new Docenti();
				temp.setNomeDocente(rs.getString(1));
				temp.setCognomeDocente(rs.getString(2));
				temp.setCvDocente(rs.getString(3));
				temp.setCodDocente(rs.getLong(4));
				docenti[i] = temp;
			}
			rs.close();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return docenti;
	}

}
