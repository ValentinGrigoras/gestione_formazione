package test.com.academy.businesscomponent.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.academy.businesscomponent.security.ConvertiMD5;

class ConvertiMD5Test {

	@Test
	void testGeneraMD5() {
		String pass = ConvertiMD5.generaMD5("Pass01");
		assertNotNull(pass);
		System.out.println(pass);
	}

}
