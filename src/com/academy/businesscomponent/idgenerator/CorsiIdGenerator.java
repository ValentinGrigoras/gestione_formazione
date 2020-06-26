package com.academy.businesscomponent.idgenerator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.academy.architecture.dao.DAOConstants;
import com.academy.architecture.dao.DAOException;
import com.academy.architecture.dao.DBAccess;

public class CorsiIdGenerator implements IdGeneratorInterface, DAOConstants{
	private static CorsiIdGenerator istanza;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	private CorsiIdGenerator() throws DAOException, ClassNotFoundException, IOException {
		conn = DBAccess.getConnection();
	}
	public static CorsiIdGenerator getInstance() throws DAOException, ClassNotFoundException, IOException {
		if(istanza == null)
			istanza = new CorsiIdGenerator();
		return istanza;
	}
	
	@Override
	public long getNextId() throws DAOException, ClassNotFoundException, IOException {
		//lettura del valore della sequenza
		long id = 0;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_CORSI_SEQ);
			rs.next();
			id = rs.getLong(1);
		}catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return id;
	}
}
