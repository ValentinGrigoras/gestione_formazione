package com.academy.businesscomponent.security;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ConvertiMD5 {
	public static String generaMD5(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(password.getBytes(Charset.forName("UTF-8")));
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < array.length; i++) {
				buffer.append(String.format("%02x", array[i]));
			}
			return buffer.toString();
		} catch (NoSuchAlgorithmException exc) {
			System.out.println(exc.getMessage());
		}
		return null;
	}
}
