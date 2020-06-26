package com.academy.businesscomponent.idgenerator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.academy.architecture.dao.DAOConstants;
import com.academy.architecture.dao.DAOException;
import com.academy.architecture.dao.DBAccess;

public class CorsistiIdGenerator implements IdGeneratorInterface, DAOConstants {
	private static CorsistiIdGenerator istanza;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	private CorsistiIdGenerator() throws DAOException, ClassNotFoundException, IOException {
		conn = DBAccess.getConnection();
	}

	public static CorsistiIdGenerator getInstance()
			throws DAOException, ClassNotFoundException, IOException {
		if(istanza == null)
			istanza = new CorsistiIdGenerator();
		return istanza;
	}

	@Override
	public long getNextId() 
			throws DAOException, ClassNotFoundException, IOException {
		long id = 0;
		try {
			stmt = conn.createStatement();	
			rs = stmt.executeQuery(SELECT_CORSISTI_SEQ);
			rs.next();
			id = rs.getLong(1);	
		}catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return id;
	}
}