package com.academy.businesscomponent.facade;

import java.io.IOException;
import java.util.Date;

import com.academy.architecture.dao.DAOException;
import com.academy.businesscomponent.CorsiBC;
import com.academy.businesscomponent.CorsistiBC;
import com.academy.businesscomponent.DocentiBC;
import com.academy.businesscomponent.IscrizioniBC;
import com.academy.businesscomponent.model.Corsi;
import com.academy.businesscomponent.model.Corsisti;
import com.academy.businesscomponent.model.Docenti;
import com.academy.businesscomponent.model.Iscrizioni;

public class AmministratoreFacade {
	private static AmministratoreFacade cF;
	private CorsistiBC corsistiBC;
	private DocentiBC docentiBC;
	private IscrizioniBC iscrizioniBC;
	private CorsiBC corsiBC;

	private AmministratoreFacade() throws DAOException, ClassNotFoundException, IOException {
		corsistiBC = new CorsistiBC();
		docentiBC = new DocentiBC();
		iscrizioniBC = new IscrizioniBC();
		corsiBC = new CorsiBC();
	}

	public static AmministratoreFacade getInstance() throws DAOException, ClassNotFoundException, IOException {
		if (cF == null)
			cF = new AmministratoreFacade();
		return cF;
	}

	public Docenti getDocentiByID(long id) throws DAOException {
		return docentiBC.getById(id);
	}

	public void createCorsisti(Corsisti corsisti) throws DAOException, ClassNotFoundException, IOException {
		corsistiBC.create(corsisti);
	}

	public void createOrUpdateCorsisti(Corsisti corsisti) throws DAOException, ClassNotFoundException, IOException {
		corsistiBC.createOrUpdate(corsisti);
	}

	public void deleteCorsisti(long id) throws DAOException, ClassNotFoundException, IOException {
		corsistiBC.delete(id);
	}

	public void deleteCorso(long id) throws DAOException, ClassNotFoundException, IOException {
		corsiBC.delete(id);
	}

	public Corsisti[] searchCorsisti(String query) throws DAOException, ClassNotFoundException, IOException {
		return corsistiBC.searchProdotti(query);
	}

	public Corsisti[] getCorsisti() throws DAOException {
		return corsistiBC.getCorsisti();
	}

	public Corsisti getCorsistaById(long id) throws DAOException {
		return corsistiBC.getById(id);
	}

	public void createIscrizioni(Iscrizioni iscrizioni) throws DAOException, ClassNotFoundException, IOException {
		iscrizioniBC.create(iscrizioni);
	}

	public void deleteIscrizioni(long idCorso, long idCorsista)
			throws DAOException, IOException, ClassNotFoundException {
		iscrizioniBC.delete(idCorso, idCorsista);
	}

	public Iscrizioni[] getIscrizioni() throws DAOException {
		return iscrizioniBC.getIscrizioni();
	}

	public Corsi[] getCorsiByCodCorsista(long codCorsista) throws DAOException {
		return corsiBC.getCorsiByCodCorsista(codCorsista);
	}

	public Corsi getCorsiByCod(long codCorsi) throws DAOException {
		return corsiBC.getCorsiById(codCorsi);
	}
	
	public Corsi[] getCorsiByDate(Date date) throws DAOException {
		return corsiBC.getCorsiByDate(date);
	}

}
