package test.com.academy.architecture.dao;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.academy.architecture.dao.DAOException;
import com.academy.architecture.dao.DBAccess;
import com.academy.architecture.dao.IscrizioniDAO;
import com.academy.businesscomponent.model.Iscrizioni;

class IscrizioniDAOTest {
	
	private static Iscrizioni iscrizione;
	private static Connection conn;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		conn = DBAccess.getConnection();
		iscrizione = new Iscrizioni();
		iscrizione.setAdminUsername("admin");
		iscrizione.setCodCorsista(1);
		iscrizione.setCodCorso(1);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		try {
			iscrizione = null;
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("Delete from iscrizioni where codcorso = 1 and codcorsista = 1");
			conn.commit();
			System.out.println("Pulito tabelle del DB");
			DBAccess.closeConnection();
		} catch(DAOException exc) {
			exc.printStackTrace();
			fail("Pulizia fallita");
		}
	}

	@Test
	void testCreate() {
		try {
			IscrizioniDAO.getFactory().create(conn, iscrizione);
			System.out.println(iscrizione);
			System.out.println("Iscrizione creata");
		} catch(DAOException exc) {
			exc.printStackTrace();
			fail("Inserimento fallito");
		}
	}

}
