package com.mundotaci.projetotaci.security.jwt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class UtilCriptografia {

	public static final Random r = new Random();

	private static char[] hexCodes(byte[] text) {

		char[] hexOutput = new char[text.length * 2];

		String hexString;

		for (int i = 0; i < text.length; i++) {

			hexString = "00" + Integer.toHexString(text[i]);

			hexString.toUpperCase().getChars(hexString.length() - 2, hexString.length(), hexOutput, i * 2);
		}

		return hexOutput;

	} // hexCodes

	/**
	 * Aplica um algoritmo md5 na string informada
	 *
	 * @param pwd A string
	 *
	 * @return O MD5 da string informada.
	 */
	public static String md5(String pwd) {

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			if (md != null) {

				return new String(hexCodes(md.digest(pwd.getBytes())));

			}
		} catch (NoSuchAlgorithmException ex) {
			return null;
		}

		return null;

	}
}