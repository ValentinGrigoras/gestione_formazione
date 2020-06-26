package com.academy.architecture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.academy.businesscomponent.model.Corsi;

public class CorsiDAO extends GenericDAOAdapter<Corsi> implements DAOConstants {

	private CachedRowSet rowSet;

	public static CorsiDAO getFactory() throws DAOException {
		return new CorsiDAO();
	}

	private CorsiDAO() throws DAOException {
		try {
			rowSet = RowSetProvider.newFactory().createCachedRowSet();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	@Override
	public void create(Connection conn, Corsi entity) throws DAOException {
		try {
			rowSet.setCommand(SELECT_CORSI);
			rowSet.execute(conn);
			rowSet.moveToInsertRow();
			rowSet.updateLong(1, entity.getCodCorso());
			rowSet.updateString(2, entity.getNomeCorso());
			rowSet.updateDate(3, new java.sql.Date(entity.getDataInizioCorso().getTime()));
			rowSet.updateDate(4, new java.sql.Date(entity.getDataFineCorso().getTime()));
			rowSet.updateDouble(5, entity.getCostoCorso());
			rowSet.updateString(6, entity.getAulaCorso());
			rowSet.updateLong(7, entity.getCodDocente());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			rowSet.acceptChanges(conn);
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	@Override
	public void update(Connection conn, Corsi entity) throws DAOException {
		try {
			PreparedStatement ps = conn.prepareStatement(UPDATE_CORSI);
			ps.setString(1, entity.getNomeCorso());
			ps.setDate(2, new java.sql.Date(entity.getDataInizioCorso().getTime()));
			ps.setDate(3, new java.sql.Date(entity.getDataFineCorso().getTime()));
			ps.setDouble(4, entity.getCostoCorso());
			ps.setString(5, entity.getCommentiCorso());
			ps.setString(6, entity.getAulaCorso());
			ps.setLong(7, entity.getCodDocente());
			ps.setLong(8, entity.getCodCorso());
			ps.execute();
			conn.commit();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	@Override
	public void delete(Connection conn, Corsi entity) throws DAOException {
		PreparedStatement ps;
		long id = entity.getCodCorso();
		try {
			ps = conn.prepareStatement(DELETE_CORSI);
			ps.setLong(1, id);
			ps.execute();
			conn.commit();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	@Override
	public Corsi getById(Connection conn, long id) throws DAOException {
		Corsi corso = null;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(SELECT_CORSI_BYKEY);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				corso = new Corsi();
				corso.setCodCorso(rs.getLong(1));
				corso.setNomeCorso(rs.getString(2));

				corso.setDataInizioCorso(new java.util.Date(rs.getDate(3).getTime()));
				corso.setDataFineCorso(new java.util.Date(rs.getDate(4).getTime()));

				corso.setCostoCorso(rs.getDouble(5));
				corso.setCommentiCorso(rs.getString(6));
				corso.setAulaCorso(rs.getString(7));
				corso.setCodDocente(rs.getLong(8));
				
			
			}
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return corso;
	}

	
	@Override
	public Corsi[] getAll(Connection conn) throws DAOException {
		Corsi[] corsi= null;
		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(SELECT_CORSI);
			rs.last();
			corsi = new Corsi[rs.getRow()];
			rs.beforeFirst();
			for (int i = 0; rs.next(); i++) {
				Corsi c = new Corsi();
				System.out.println("GetAll - rs 1 " + rs.getString(1));
				c.setCodCorso(rs.getLong(1));
				c.setNomeCorso(rs.getString(2));

				c.setDataInizioCorso(new java.util.Date(rs.getDate(3).getTime()));
				c.setDataFineCorso(new java.util.Date(rs.getDate(4).getTime()));

				c.setCostoCorso(rs.getDouble(5));
				c.setCommentiCorso(rs.getString(6));
				c.setAulaCorso(rs.getString(7));
				c.setCodDocente(rs.getLong(8));
				
				corsi[i] = c;
			}
			rs.close();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return corsi;
	}
	
	public Corsi[] getByCodCorsista(Connection conn, long id) throws DAOException {

		Corsi[] corsi = null;
		Corsi corso = null;
		PreparedStatement ps;
		try {

			ps = conn.prepareStatement(SELECT_ISCRIZIONI_BYCORSISTA, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			rs.last();
			corsi = new Corsi[rs.getRow()];

			rs.beforeFirst();
			for (int i = 0; rs.next(); i++) {
	
				corso = new Corsi();
				corso.setCodCorso(rs.getLong(1));
			
				corso.setNomeCorso(rs.getString(2));

				corso.setDataInizioCorso(new java.util.Date(rs.getDate(3).getTime()));
				corso.setDataFineCorso(new java.util.Date(rs.getDate(4).getTime()));

				corso.setCostoCorso(rs.getDouble(5));
				corso.setCommentiCorso(rs.getString(6));
				corso.setAulaCorso(rs.getString(7));
				corso.setCodDocente(rs.getLong(8));

				corsi[i] = corso;
			}
			rs.close();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return corsi;
	}
	
	public Corsi[] getByDate(Connection conn, Date date) throws DAOException {
		Corsi[] corsi = null;
		Corsi corso = null;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(SELECT_CORSI_BYDATE, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setDate(1, new java.sql.Date(date.getTime()));
			ResultSet rs = ps.executeQuery();
			rs.last();
			corsi = new Corsi[rs.getRow()];
			rs.beforeFirst();
			for(int i = 0; rs.next(); i++) {
				corso = new Corsi();
				corso.setCodCorso(rs.getLong(1));
			
				corso.setNomeCorso(rs.getString(2));

				corso.setDataInizioCorso(new java.util.Date(rs.getDate(3).getTime()));
				corso.setDataFineCorso(new java.util.Date(rs.getDate(4).getTime()));

				corso.setCostoCorso(rs.getDouble(5));
				corso.setCommentiCorso(rs.getString(6));
				corso.setAulaCorso(rs.getString(7));
				corso.setCodDocente(rs.getLong(8));

				corsi[i] = corso;
			}
			
			rs.close();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		
		return corsi;
	}

}
