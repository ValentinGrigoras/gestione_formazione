package com.academy.businesscomponent.utilities;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.academy.architecture.dao.DAOConstants;
import com.academy.architecture.dao.DAOException;
import com.academy.architecture.dao.DBAccess;

public class LoginUtility implements DAOConstants {
	private Connection conn;
	
	public LoginUtility() throws DAOException, ClassNotFoundException, IOException {
		conn = DBAccess.getConnection();
	}
	
	public String getPass(String username) throws DAOException {
		try {
			PreparedStatement stmt = conn.prepareStatement(SELECT_ADMINPASS);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				return rs.getString(1);
			return null;
			
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
}
}
