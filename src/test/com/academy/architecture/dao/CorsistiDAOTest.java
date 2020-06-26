package test.com.academy.architecture.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.academy.architecture.dao.CorsistiDAO;
import com.academy.architecture.dao.DAOException;
import com.academy.architecture.dao.DBAccess;
import com.academy.businesscomponent.model.Corsisti;

class CorsistiDAOTest {
	private static Corsisti corsista;
	private static Connection conn;
	private static long i = 999;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		conn = DBAccess.getConnection();

		corsista = new Corsisti();
		corsista.setCodCorsista(i);
		corsista.setNomeCorsista("Nome");
		corsista.setCognomeCorsista("Cognome");
		corsista.setPrecedentiFormativi((byte) 1);

		System.out.println(corsista.toString());
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		corsista = null;
		try {
			Statement stmt = conn.createStatement();
			stmt.execute("Delete from corsisti where codcorsista = " + i);
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
			CorsistiDAO.getFactory().create(conn, corsista);
			System.out.println("Corsista creato");
		} catch (DAOException exc) {
			exc.printStackTrace();
			fail("Create fallito: " + exc.getMessage());
		}
	}

	@Test
	void testUpdate() {
		try {
			corsista = new Corsisti();
			corsista.setCodCorsista(i);
			corsista.setNomeCorsista("NomeModificato");
			corsista.setCognomeCorsista("CognomeModificato");
			corsista.setPrecedentiFormativi((byte) 0);

			CorsistiDAO.getFactory().update(conn, corsista);
			System.out.println("Corsista modificato");

			Corsisti c = CorsistiDAO.getFactory().getById(conn, corsista.getCodCorsista());
			System.out.println("Nuovi dati corsista: " + c.toString());
		} catch (DAOException exc) {
			exc.printStackTrace();
			fail("Update fallito: " + exc.getMessage());
		}
	}

	@Test
	void testGetAll() {
		try {
			Corsisti[] corsisti = CorsistiDAO.getFactory().getAll(conn);
			assertNotNull(corsisti);
		} catch (DAOException exc) {
			exc.printStackTrace();
			fail("GetAll fallito: " + exc.getMessage());
		}
	}
	
}
