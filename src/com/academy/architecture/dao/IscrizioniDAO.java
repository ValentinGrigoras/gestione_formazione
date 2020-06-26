package com.academy.architecture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.academy.businesscomponent.model.Iscrizioni;

public class IscrizioniDAO extends GenericDAOAdapter<Iscrizioni> implements DAOConstants {
	private CachedRowSet rowSet;

	public static IscrizioniDAO getFactory() throws DAOException {
		return new IscrizioniDAO();
	}

	private IscrizioniDAO() throws DAOException {
		try {
			rowSet = RowSetProvider.newFactory().createCachedRowSet();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void create(Connection conn, Iscrizioni entity) throws DAOException {
		try {
			rowSet.setCommand(SELECT_ISCRIZIONI);
			rowSet.execute(conn);
			rowSet.moveToInsertRow();
			rowSet.updateLong(1, entity.getCodCorso());
			rowSet.updateLong(2, entity.getCodCorsista());
			rowSet.updateString(3, entity.getAdminUsername());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			rowSet.acceptChanges(conn);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void delete(Connection conn, Iscrizioni entity) throws DAOException {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(DELETE_ISCRIZIONI);
			ps.setLong(1, entity.getCodCorso());
			ps.setLong(2, entity.getCodCorsista());
			ps.execute();
			conn.commit();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	@Override
	public Iscrizioni[] getAll(Connection conn) throws DAOException {
		Iscrizioni[] iscrizioni = null;
		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(SELECT_ISCRIZIONI);
			rs.last();
			iscrizioni = new Iscrizioni[rs.getRow()];
			rs.beforeFirst();
			for (int i = 0; rs.next(); i++) {
				Iscrizioni iscrizione = new Iscrizioni();
				iscrizione.setCodCorso(rs.getLong(1));
				iscrizione.setCodCorsista(rs.getLong(2));
				iscrizione.setAdminUsername(rs.getString(3));
				iscrizioni[i] = iscrizione;
			}
			rs.close();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return iscrizioni;
	}

}