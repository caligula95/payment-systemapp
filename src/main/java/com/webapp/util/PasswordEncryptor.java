package com.webapp.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

/**
 * Encrypt password in MD5 algorithm
 *
 */
public class PasswordEncryptor {
	/**
	 * LOG for this class.
	 */
	private static final Logger LOGGER = Logger.getLogger(PasswordEncryptor.class);
	/**
	 * ALGORYTHM name.
	 */
	private static final String ALGORITHM = "MD5";
	/**
	 * CHARSET.
	 */
	private static final String CHARSET = "utf-8";

	/**
	 * encrypts passwords in md5.
	 * 
	 * @param password
	 * @return hashed password
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public String encode(String password) {
		MessageDigest md5;
		String md5StrWithoutZerous;
		StringBuilder md5StrWithZerous = new StringBuilder(32);

		try {
			md5 = MessageDigest.getInstance(ALGORITHM);
			md5.reset();

			// sends byte-code of string to MessageDigest
			md5.update(password.getBytes(CHARSET));

			// gets MD5-hash without zeros in front
			md5StrWithoutZerous = new BigInteger(1, md5.digest()).toString(16);

			// adds zeros in front if it is necessary
			for (int i = 0, count = 32 - md5StrWithoutZerous.length(); i < count; i++) {
				md5StrWithZerous.append("0");
			}

			md5StrWithZerous.append(md5StrWithoutZerous);

		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("Cannot get instance of MessageDigest", e);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Cannot generate byte-code of string", e);
		}
		return md5StrWithZerous.toString();
	}
}
