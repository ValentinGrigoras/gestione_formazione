package test.com.academy.architecture.dao;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.academy.architecture.dao.DAOException;
import com.academy.architecture.dao.DBAccess;
import com.academy.architecture.dao.DocentiDAO;
import com.academy.businesscomponent.model.Docenti;

class DocentiDAOTest {

	private static Docenti docente;
	private static Connection conn;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		conn = DBAccess.getConnection();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		conn.close();
	}
	
	@Test
	void testGetById() {

		try {
			docente = DocentiDAO.getFactory().getById(conn, 1);
			System.out.println(docente);
			
		} catch (DAOException exc) {
			exc.printStackTrace();
			fail("getById fallito");
		}
	}

	@Test
	void testGetAll() {
		try {
			Docenti[] docenti = DocentiDAO.getFactory().getAll(conn);
			for(int i = 0; i < docenti.length; i++) 
				System.out.println(docenti[i]);
			assertNotNull(docenti);
		} catch(DAOException exc) {
			exc.printStackTrace();
			fail("getAll fallito");
		}
	}

}
