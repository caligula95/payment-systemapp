package com.webapp.dao;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import com.webapp.entity.Payment;
import com.webapp.persistence.dao.PaymentDao;
import com.webapp.persistence.dao.impl.PaymentDaoImpl;

public class TestPaymentDao {

	private static PaymentDao paymentDao = null;
	private static Payment payment = null;

	@BeforeClass
	public static void setUp() {
		paymentDao = mock(PaymentDaoImpl.class);
		payment = new Payment();
		payment.setPaymentId(1);
		payment.setAccountId(1);
		payment.setAppointment("Appointment");
		payment.setCardNumber("1234567890123456");
		payment.setSum(new BigDecimal(123));

		when(paymentDao.create(payment)).thenReturn(payment.getPaymentId());
		when(paymentDao.findAllPaymentsByAccountId(payment.getAccountId())).thenReturn(Arrays.asList(payment));
		when(paymentDao.findPaymentByPaymentId(payment.getPaymentId())).thenReturn(payment);
	}

	@Test
	public void testCreatePayment() {
		Integer paymentId = paymentDao.create(payment);
		assertEquals(paymentId, payment.getPaymentId());
	}

	@Test
	public void testFindAllPaymentsByAccountId() {
		List<Payment> payments = paymentDao.findAllPaymentsByAccountId(1);
		assertEquals(1, payments.size());
		assertEquals("Appointment", payments.get(0).getAppointment());
	}

	@Test
	public void testFindPaymentByPaymentId() {
		Payment payment1 = paymentDao.findPaymentByPaymentId(1);
		Payment payment2 = paymentDao.findPaymentByPaymentId(3);
		assertNull(payment2);
		assertEquals("1234567890123456", payment1.getCardNumber());
	}
}
