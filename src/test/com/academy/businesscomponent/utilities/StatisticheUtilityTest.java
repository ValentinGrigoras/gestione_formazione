package test.com.academy.businesscomponent.utilities;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.academy.architecture.dao.DAOException;
import com.academy.businesscomponent.model.Corsi;
import com.academy.businesscomponent.model.Corsisti;
import com.academy.businesscomponent.utilities.StatisticheUtility;

class StatisticheUtilityTest {
	static StatisticheUtility su;
	static Corsi corso;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		su = new StatisticheUtility();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetCountCorsisti() {
		try {
			int numeroCorsisti = su.getNumeroCorsisti();
			System.out.println("Numero corsisti:" + numeroCorsisti);
			assert (numeroCorsisti > 0);
		} catch (DAOException e) {
			e.printStackTrace();
			fail("getCountCorsisti fail" + e.getMessage());
		}
	}

	@Test
	void testGetCorsoPiuFrequentato() {
		try {
			Vector<String[]> result = su.getCorsoPiuFrequentato();
			assertNotNull(result);
			System.out.print("Corso più frequentato: ");
			for (String s[] : result)
				System.out.println(s[0] + "\t" + s[1]);
		} catch (DAOException e) {
			e.printStackTrace();
			fail("getCorsoPiuFrequentato fail" + e.getMessage());
		}
	}

	@Test
	void testGetUltimoCorsoIniziato() {
		try {
			Corsi corso = su.getUltimoCorsoIniziato();
			assertNotNull(corso);
			System.out.println("Ultimo corso iniziato: " + corso);
		} catch (DAOException e) {
			e.printStackTrace();
			fail("getUltimoCorsoIniziato fail" + e.getMessage());
		}
	}

	@Test
	void testGetDurataMediaCorsi() {
		try {
			int durataMedia = su.getDurataMediaCorsi();
			assert (durataMedia > 0);
			System.out.println("Durata media: " + durataMedia);
		} catch (DAOException e) {
			e.printStackTrace();
			fail("getDurataMediaCorsi fail" + e.getMessage());
		}
	}

	@Test
	void testIsGiornoLavorativo() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		assertFalse(su.isGiornoLavorativo(calendar));
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		assert (su.isGiornoLavorativo(calendar));
	}

	@Test
	void testCalcolaGiorniLavorativi() {
		Calendar inizio = GregorianCalendar.getInstance();
		inizio.set(2020, 6, 1, 0, 0, 0); // 1-LUG-2020
		Calendar fine = GregorianCalendar.getInstance();
		fine.set(2020, 6, 31, 0, 0, 0);// 31-LUG-2020
		// 23 gg lavorativi
		assert (su.calcolaGiorniLavorativi(inizio.getTime(), fine.getTime()) == 23);
	}

	@Test
	void testGetNumeroCommenti() {
		try {
			assert (su.getNumeroCommenti() > 0);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testGetInfoCorsisti() {
		try {
			List<Corsisti> corsisti = su.getInfoCorsisti();
			assertNotNull(corsisti);
			assert (corsisti.size() > 0);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testGetCorsiPostiDisponibili() {
		try {
			Vector<String[]> corsiDisponibili = su.getCorsiPostiDisponibili();
			for (String[] riga : corsiDisponibili) {
				for (int i = 0; i < riga.length; i++) {
					System.out.print(riga[i] + "\t");
				}
				System.out.println();
			}
			assertNotNull(corsiDisponibili);
			assert (corsiDisponibili.size() > 0);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

}
