package test.com.academy.architecture.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.academy.architecture.dao.CorsiDAO;
import com.academy.architecture.dao.DAOException;
import com.academy.architecture.dao.DBAccess;
import com.academy.businesscomponent.model.Corsi;

class CorsiDAOTest {
	private static Corsi corso;
	private static Connection conn;
	private static long i = 999;
	private static GregorianCalendar calendar;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		conn = DBAccess.getConnection();

		calendar = new GregorianCalendar();
		Date today = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date tomorrow = calendar.getTime();

		corso = new Corsi();
		corso.setCodCorso(i);
		corso.setNomeCorso("Nome");
		corso.setDataInizioCorso(today);
		corso.setDataFineCorso(tomorrow);
		corso.setCostoCorso(200);
		corso.setCommentiCorso("Commento");
		corso.setAulaCorso("aula1");
		corso.setCodDocente(1);

		System.out.println(corso.toString());
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		corso = null;
		try {
			Statement stmt = conn.createStatement();
			stmt.execute("Delete from corsi where codcorso = " + 999);
			conn.commit();
			System.out.println("Pulizia completata");
		} catch (DAOException exc) {
			exc.printStackTrace();
			fail("Pulizia fallita: " + exc.getMessage());
		}
	}

	@Test
	void testCreate() {
		try {
			CorsiDAO.getFactory().create(conn, corso);
			System.out.println("Corso creato");
		} catch (DAOException exc) {
			exc.printStackTrace();
			fail("Create fallito: " + exc.getMessage());
		}
	}

	@Test
	void testUpdate() {
		try {
			corso.setNomeCorso("NomeModificato");
			calendar.add(Calendar.DAY_OF_MONTH, 2);
			Date after_tomorrow = calendar.getTime();
			corso.setDataFineCorso(after_tomorrow);
			corso.setCostoCorso(100);

			CorsiDAO.getFactory().update(conn, corso);
			System.out.println("Corso modificato");

			Corsi c = CorsiDAO.getFactory().getById(conn, corso.getCodCorso());

			System.out.println("Nuovi dati corso " + c.toString());
		} catch (DAOException exc) {
			exc.printStackTrace();
			fail("Update fallito: " + exc.getMessage());
		}
	}

	@Test
	void testGetAll() {
		try {
			Corsi[] corsi = CorsiDAO.getFactory().getAll(conn);
			assertNotNull(corsi);
		} catch (DAOException exc) {
			exc.printStackTrace();
			fail("GetAll fallito: " + exc.getMessage());
		}
	}

	@Test
	void testGetCorsiByCorsista() {
		try {
			long id = 1;
			Corsi[] corsi = CorsiDAO.getFactory().getByCodCorsista(conn, id);
			assert (corsi.length > 0);
		} catch (DAOException exc) {
			exc.printStackTrace();
			fail("GetCorsoByCorsista fallito: " + exc.getMessage());
		}
	}
	
	@Test
	void testGetCorsiByDate() {
		try {
			Corsi[] corsi = CorsiDAO.getFactory().getByDate(conn, new Date());
			assert (corsi.length > 0);
		} catch (DAOException exc) {
			exc.printStackTrace();
			fail("GetCorsoByCorsista fallito: " + exc.getMessage());
		}
	}
		
}
