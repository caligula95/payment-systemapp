package com.webapp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Checks input strings on valid pattern uses for form validation
 */
public class StringValidator {

	public static boolean checkPhoneNumber(String number) {
		Pattern p = Pattern.compile("0\\d{9}");
		Matcher m = p.matcher(number);
		return m.matches();
	}

	public static boolean checkCardNumber(String number) {
		Pattern p = Pattern.compile("\\d{16}");
		Matcher m = p.matcher(number);
		return m.matches();
	}

	public static boolean checkAccountNumber(String number) {
		Pattern p = Pattern.compile("\\d{20}");
		Matcher m = p.matcher(number);
		return m.matches();
	}

	public static boolean checkCvv(String cvv) {
		Pattern p = Pattern.compile("\\d{3}");
		Matcher m = p.matcher(cvv);
		return m.matches();
	}

	public static boolean checkPassword(String password) {
		Pattern p = Pattern.compile(".{5,}");
		Matcher m = p.matcher(password);
		return m.matches();
	}
}
