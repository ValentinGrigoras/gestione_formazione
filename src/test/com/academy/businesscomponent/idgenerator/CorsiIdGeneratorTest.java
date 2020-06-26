package test.com.academy.businesscomponent.idgenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.academy.architecture.dao.DAOException;
import com.academy.businesscomponent.idgenerator.CorsiIdGenerator;

class CorsiIdGeneratorTest {

	@Test
	void testGetNextId() {
		try {
			CorsiIdGenerator idGen = CorsiIdGenerator.getInstance();
			assertNotNull(idGen, "Istanza non creata correttamente");
			long valore = idGen.getNextId();
			assertEquals(idGen.getNextId() - 1, valore);

		} catch (ClassNotFoundException | DAOException | IOException exc) {
			exc.printStackTrace();
			fail();
		}
	}

}
