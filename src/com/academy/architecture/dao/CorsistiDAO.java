package com.academy.architecture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.academy.businesscomponent.model.Corsisti;

public class CorsistiDAO extends GenericDAOAdapter<Corsisti> implements DAOConstants {

	private CachedRowSet rowSet;

	public static CorsistiDAO getFactory() throws DAOException {
		return new CorsistiDAO();
	}

	private CorsistiDAO() throws DAOException {
		try {
			rowSet = RowSetProvider.newFactory().createCachedRowSet();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	@Override
	public void create(Connection conn, Corsisti entity) throws DAOException {
		try {
			rowSet.setCommand(SELECT_CORSISTI);
			rowSet.execute(conn);
			rowSet.moveToInsertRow();
			rowSet.updateString(1, entity.getNomeCorsista());
			rowSet.updateString(2, entity.getCognomeCorsista());
			rowSet.updateLong(3, entity.getCodCorsista());
			rowSet.updateByte(4, entity.getPrecedentiFormativi());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			rowSet.acceptChanges(conn);
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	@Override
	public void update(Connection conn, Corsisti entity) throws DAOException {
		try {
			PreparedStatement ps = conn.prepareStatement(UPDATE_CORSISTI);
			ps.setString(1, entity.getNomeCorsista());
			ps.setString(2, entity.getCognomeCorsista());
			ps.setByte(3, entity.getPrecedentiFormativi());
			ps.setLong(4, entity.getCodCorsista());
			ps.execute();
			conn.commit();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}

	}

	@Override
	public void delete(Connection conn, Corsisti entity) throws DAOException {
		PreparedStatement ps;
		long id = entity.getCodCorsista();
		try {
			ps = conn.prepareStatement(DELETE_CORSISTI);
			ps.setLong(1, id);
			ps.execute();
			conn.commit();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	@Override
	public Corsisti getById(Connection conn, long id) throws DAOException {
		Corsisti corsista = null;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(SELECT_CORSISTI_BYKEY);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				corsista = new Corsisti();
				corsista.setNomeCorsista(rs.getString(1));
				corsista.setCognomeCorsista(rs.getString(2));
				corsista.setCodCorsista(rs.getLong(3));
				corsista.setPrecedentiFormativi(rs.getByte(4));
			}
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return corsista;
	}

	@Override
	public Corsisti[] getAll(Connection conn) throws DAOException {
		Corsisti[] corsisti = null;
		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(SELECT_CORSISTI_ORDINATI);
			rs.last();
			corsisti = new Corsisti[rs.getRow()];
			rs.beforeFirst();
			for (int i = 0; rs.next(); i++) {
				Corsisti c = new Corsisti();
				c.setNomeCorsista(rs.getString(1));
				c.setCognomeCorsista(rs.getString(2));
				c.setCodCorsista(rs.getLong(3));
				c.setPrecedentiFormativi(rs.getByte(4));
				corsisti[i] = c;
			}
			rs.close();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return corsisti;
	}
}