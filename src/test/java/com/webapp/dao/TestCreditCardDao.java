package com.webapp.dao;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.webapp.entity.CreditCard;
import com.webapp.persistence.dao.CreditCardDao;
import com.webapp.persistence.dao.impl.CreditCardDaoImpl;

import static org.junit.Assert.*;

public class TestCreditCardDao {

	private static CreditCardDao creditCardDao = null;
	private static CreditCard creditCard = null;

	@BeforeClass
	public static void setUp() {
		creditCardDao = mock(CreditCardDaoImpl.class);
		creditCard = new CreditCard();
		creditCard.setAccountId(1);
		creditCard.setCardId(1);
		creditCard.setCvv("333");
		creditCard.setNumber("1234567890123456");

		when(creditCardDao.create(creditCard)).thenReturn(creditCard.getCardId());
		when(creditCardDao.delete(creditCard.getCardId())).thenReturn(creditCard.getCardId());
		when(creditCardDao.findCardsByAccountId(creditCard.getAccountId())).thenReturn(Arrays.asList(creditCard));
		when(creditCardDao.findCreditCardByCardNumber(creditCard.getNumber())).thenReturn(creditCard);
	}

	@Test
	public void testCreateCreditCard() {
		Integer cardId = creditCardDao.create(creditCard);
		assertNotNull(cardId);
		Integer id = creditCard.getCardId();
		assertEquals(id, cardId);
	}

	@Test
	public void testDeleteCreditCard() {
		Integer cardId = creditCardDao.delete(creditCard.getCardId());
		assertNotNull(cardId);
		Integer id = creditCard.getCardId();
		assertEquals(id, cardId);
	}

	@Test
	public void testFindCardsByAccountId() {
		List<CreditCard> creditCards = creditCardDao.findCardsByAccountId(creditCard.getCardId());
		assertEquals(1, creditCards.size());
		assertEquals("333", creditCards.get(0).getCvv());
	}

	@Test
	public void testFindCreditCardByCardNumber() {
		CreditCard creditCard1 = creditCardDao.findCreditCardByCardNumber(creditCard.getNumber());
		CreditCard creditCard2 = creditCardDao.findCreditCardByCardNumber("12345");
		assertEquals(1, creditCard1.getAccountId());
		assertNull(creditCard2);
	}

}
