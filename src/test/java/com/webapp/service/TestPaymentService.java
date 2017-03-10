package com.webapp.service;

import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import com.webapp.entity.Account;
import com.webapp.entity.Payment;

public class TestPaymentService {

	private static PaymentService paymentService = null;
	private static Payment payment1 = null;
	private static Account account1 = null;
	private static Account account2 = null;

	@BeforeClass
	public static void setUp() {
		paymentService = mock(PaymentService.class);
		payment1 = new Payment();
		payment1.setPaymentId(1);
		payment1.setAccountId(1);
		payment1.setAppointment("Appointment");
		payment1.setCardNumber("1234567890123456");
		payment1.setSum(new BigDecimal(123));

		account1 = new Account();
		account1.setAccountId(1);
		account1.setBalance(new BigDecimal(123));
		account1.setIsBlocked(false);
		account1.setNumber("12345678900987654321");
		account1.setUserId(1);

		account2 = new Account();
		account2.setAccountId(2);
		account2.setBalance(new BigDecimal(321));
		account2.setIsBlocked(false);
		account2.setUserId(2);

		when(paymentService.formPayment(new BigDecimal(9), payment1.getCardNumber(), payment1.getAccountId(),
				new Double(10), payment1.getAppointment())).thenReturn(payment1.getPaymentId());
	}

	@Test
	public void testCreatePament() {
		Integer paymentId = paymentService.formPayment(new BigDecimal(9), "1234567890123456", 1, new Double(10),
				"Appointment");
		assertNotNull(paymentId);
		assertEquals(new Integer(1), paymentId);
		
		
	}
}
