package com.academy.businesscomponent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.academy.architecture.dao.CorsistiDAO;
import com.academy.architecture.dao.DAOException;
import com.academy.architecture.dao.DBAccess;
import com.academy.businesscomponent.idgenerator.CorsistiIdGenerator;
import com.academy.businesscomponent.model.Corsisti;

public class CorsistiBC {
	private Connection conn;
	private CorsistiIdGenerator idGen;

	public CorsistiBC() throws DAOException, ClassNotFoundException, IOException {
		conn = DBAccess.getConnection();
		idGen = CorsistiIdGenerator.getInstance();
	}
	
	public void create(Corsisti corsista) throws DAOException {
		try {
			CorsistiDAO.getFactory().create(conn, corsista);
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}
	
	public Corsisti[] getCorsisti() throws DAOException {
		try {
			return CorsistiDAO.getFactory().getAll(conn);
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}
	
	public Corsisti[] searchProdotti(String query) throws DAOException {
		ArrayList<Corsisti> lista = new ArrayList<Corsisti>();
		String[] criterioDiRicerca = query.toLowerCase().split(" ");

		for (Corsisti p : getCorsisti()) {
			for (String s : criterioDiRicerca) {
				if (p.getNomeCorsista().toLowerCase().contains(s) || p.getCognomeCorsista().toLowerCase().contains(s)) {
					lista.add(p);
				}
			}
		}
		Corsisti[] prodotti = new Corsisti[lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			prodotti[i] = lista.get(i);
		}
		return prodotti;
	}
	
	public Corsisti getById(long id) throws DAOException {
		try {
			return CorsistiDAO.getFactory().getById(conn, id);
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}
	
	public void createOrUpdate(Corsisti corsista) throws DAOException, ClassNotFoundException, IOException {
		try {
			if (corsista.getCodCorsista() > 0) {
				CorsistiDAO.getFactory().update(conn, corsista);
			} else {
				corsista.setCodCorsista(idGen.getNextId());
				CorsistiDAO.getFactory().create(conn, corsista);
			}
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	public void delete(long id) throws DAOException {
		try {
			CorsistiDAO.getFactory().delete(conn, CorsistiDAO.getFactory().getById(conn, id));
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}
	
}
