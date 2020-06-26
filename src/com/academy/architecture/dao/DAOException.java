package com.academy.architecture.dao;

import java.sql.SQLException;

public class DAOException extends SQLException {

	private static final long serialVersionUID = -7340209935378856559L;

	private final static int ORA1017 = 1017; // username o password errati
	private final static int ORA17002 = 17002; // connesione fallita, input output error
	private final static int ORA00001 = 0; // constraint violata

	private String message;

	@Override
	public String getMessage() {
		return message;
	}

	public DAOException(SQLException sql) {
		String chiave = "";
		if (sql != null) {
			switch (sql.getErrorCode()) {
			case ORA1017:
				chiave = "Username o password errati. Controlla le credenziali.";
				log(sql);
				break;
			case ORA17002:
				chiave = "I/O error di Oracle. Impossibile ottenere la connessione al DB.";
				log(sql);
				break;
			case ORA00001:
				chiave = "Vincolo della tabella violato.";
				log(sql);
				break;
			default:
				chiave = "Eccezione SQL non prevista.";
				log(sql);
			}
		}
		message = chiave;
	}

	private void log(SQLException sql) {
		sql.printStackTrace();
		System.err.println("Motivo: " + sql.getMessage());
		System.err.println("Stato app: " + sql.getSQLState());
		System.err.println("Codice errore: " + sql.getErrorCode());
		System.err.println("Causa del problema: " + sql.getCause());
	}
}
