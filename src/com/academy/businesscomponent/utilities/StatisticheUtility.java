package com.academy.businesscomponent.utilities;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import com.academy.architecture.dao.DAOConstants;
import com.academy.architecture.dao.DAOException;
import com.academy.architecture.dao.DBAccess;
import com.academy.businesscomponent.model.Corsi;
import com.academy.businesscomponent.model.Corsisti;
import com.academy.businesscomponent.model.Docenti;

public class StatisticheUtility implements DAOConstants {
	private Connection conn;

	public StatisticheUtility() throws DAOException, ClassNotFoundException, IOException {
		conn = DBAccess.getConnection();
	}

	public int getNumeroCorsisti() throws DAOException {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(COUNT_CORSISTI);
			if (rs.next())
				return rs.getInt(1);
			return 0;

		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	public Vector<String[]> getCorsoPiuFrequentato() throws DAOException {
		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(SELECT_CORSI_FREQUENZA);

			int numeroColonne = rs.getMetaData().getColumnCount();
			Vector<String[]> dati = new Vector<String[]>();
			rs.beforeFirst();
			while (rs.next()) {
				String[] riga = new String[numeroColonne];
				for (int i = 0; i < numeroColonne; i++)
					riga[i] = rs.getString(i + 1);
				dati.add(riga);
			}
			return dati;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public Corsi getUltimoCorsoIniziato() throws DAOException {
		try {
			PreparedStatement ps = conn.prepareStatement(DATA_ULTIMO_CORSO);
			ps.setDate(1, new java.sql.Date(new java.util.Date().getTime()));

			ResultSet rs = ps.executeQuery();
			Corsi c = null;
			// usando l'if leggo solo la prima riga ovvero il corso più frequentato
			if (rs.next()) {
				c = new Corsi();
				c.setCodCorso(rs.getLong(1));
				c.setNomeCorso(rs.getString(2));
				c.setDataInizioCorso(new java.util.Date(rs.getDate(3).getTime()));
				c.setDataFineCorso(new java.util.Date(rs.getDate(4).getTime()));
				c.setCostoCorso(rs.getDouble(5));;
				c.setCommentiCorso(rs.getString(6));
				c.setAulaCorso(rs.getString(7));
				c.setCodDocente(rs.getLong(8));		
			}
			return c;
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	public int getDurataMediaCorsi() throws DAOException {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_DURATA_CORSI);

			int totaleGiorniLavorativi = 0;
			int numeroCorsi = 0;
			while (rs.next()) {
				numeroCorsi++;
				totaleGiorniLavorativi += calcolaGiorniLavorativi(rs.getDate(1), rs.getDate(2));
			}
			if (numeroCorsi != 0)
				return totaleGiorniLavorativi / numeroCorsi;
			else
				return 0;
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	// modificatore public per poter testatre il metodo singolarmente
	public int calcolaGiorniLavorativi(Date dataInizio, Date dataFine) {
		Calendar inizio = new GregorianCalendar();
		inizio.setTimeInMillis(dataInizio.getTime());
		Calendar fine = new GregorianCalendar();
		fine.setTimeInMillis(dataFine.getTime());

		int giorniLavorativi = 0;

		while (inizio.getTimeInMillis() <= fine.getTimeInMillis()) {
			if (isGiornoLavorativo(inizio)) {
				giorniLavorativi++;
			}
			inizio.add(Calendar.DAY_OF_MONTH, 1);
		}
		return giorniLavorativi;

	}

	// modificatore public per poter testatre il metodo singolarmente
	public boolean isGiornoLavorativo(Calendar inizio) {
		int day = inizio.get(Calendar.DAY_OF_WEEK);
		return (day != Calendar.SATURDAY && day != Calendar.SUNDAY);
	}

	public int getNumeroCommenti() throws DAOException {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(COUNT_COMMENTI);
			if (rs.next())
				return rs.getInt(1);
			return 0;

		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	public List<Corsisti> getInfoCorsisti() throws DAOException {
		List<Corsisti> listaCorsisti = new ArrayList<Corsisti>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_INFO_CORSISTI);

			while (rs.next()) {
				Corsisti c = new Corsisti();
				c.setNomeCorsista(rs.getString(1));
				c.setCognomeCorsista(rs.getString(2));
				c.setCodCorsista(rs.getLong(3));
				listaCorsisti.add(c);
			}
			return listaCorsisti;
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	public List<Docenti> getDecentiPiuCorsi() throws DAOException {
		List<Docenti> listaDocenti = new ArrayList<Docenti>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_DOCENTI_PIU_CORSI);

			while (rs.next()) {
				Docenti d = new Docenti();
				d.setNomeDocente(rs.getString(1));
				d.setCognomeDocente(rs.getString(2));
				d.setCvDocente(rs.getString(3));
				d.setCodDocente(rs.getLong(4));
				listaDocenti.add(d);
			}
			return listaDocenti;
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	public Vector<String[]> getCorsiPostiDisponibili() throws DAOException {
		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(SELECT_CORSI_DISPONIBILI);

			int numeroColonne = rs.getMetaData().getColumnCount();
			Vector<String[]> dati = new Vector<String[]>();
			rs.beforeFirst();
			while (rs.next()) {
				String[] riga = new String[numeroColonne];
				for (int i = 0; i < numeroColonne; i++)
					riga[i] = rs.getString(i + 1);
				dati.add(riga);
			}
			return dati;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

}
