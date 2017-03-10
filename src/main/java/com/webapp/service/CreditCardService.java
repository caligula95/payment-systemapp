package com.webapp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.webapp.entity.CreditCard;
import com.webapp.persistence.dao.CreditCardDao;
import com.webapp.persistence.dao.impl.CreditCardDaoImpl;
import com.webapp.persistence.factory.MySqlDaoFactory;

/**
 * Provides service methods for CreditCardDao. Layout between DAO and Command
 * 
 * @see CreditCardDao
 */
public class CreditCardService {

	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = LogManager.getLogger(CreditCardDaoImpl.class);

	private CreditCardDao creditCardDao = MySqlDaoFactory.createCreditCardDao();
	/**
	 * Singleton instance
	 */
	private static CreditCardService instance = null;

	private CreditCardService() {

	}

	public static synchronized CreditCardService getInstance() {
		if (instance == null)
			instance = new CreditCardService();
		return instance;
	}

	/**
	 * Checks if card id not null and deletes it
	 * 
	 * @param cardId
	 */
	public void deleteCardById(Integer cardId) {
		if (cardId != null)
			creditCardDao.delete(cardId);
	}

	/**
	 * Adds new card to the user account
	 * 
	 * @param number
	 * @param cvv
	 * @param validity
	 * @param accountId
	 * @return 0 if error or cardId if success
	 */
	public int addNewCard(String number, String cvv, String validity, String accountId) {
		int status = 0;
		if (number != null && cvv != null && validity != null && accountId != null) {
			if (creditCardDao.findCreditCardByCardNumber(number) != null) {
				LOGGER.info("Attemption to add existing card");
			} else {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try {
					date = formatter.parse(validity);
				} catch (ParseException e) {
					LOGGER.error("ParceException " + e.getMessage());
				}
				CreditCard creditCard = new CreditCard();
				creditCard.setAccountId(Integer.parseInt(accountId));
				creditCard.setCvv(cvv);
				creditCard.setIsActive(true);
				creditCard.setNumber(number);
				creditCard.setValidity(date);
				status = creditCardDao.create(creditCard);
			}
		}
		return status;
	}

	/**
	 * This method is used in order to avoid dao layer in commands
	 * 
	 * @param accountId
	 * @return
	 */
	public List<CreditCard> findCardsByAccountId(Integer accountId) {
		return creditCardDao.findCardsByAccountId(accountId);

	}

	/**
	 * Blocks credit card by number
	 * 
	 * @param number
	 * @return 0 if error of cardId if success;
	 */
	public int blockCreditCard(String number) {
		int status = 0;
		CreditCard creditCard = null;
		if (number != null) {
			creditCard = creditCardDao.findCreditCardByCardNumber(number);
			creditCard.setIsActive(false);
			status = creditCardDao.update(creditCard);
		}
		return status;
	}

	/**
	 * Unblocks credit card by number
	 * 
	 * @param number
	 * @return 0 if error of cardId if success;
	 */
	public int unblockCreditCard(String number) {
		int status = 0;
		CreditCard creditCard = null;
		if (number != null) {
			creditCard = creditCardDao.findCreditCardByCardNumber(number);
			creditCard.setIsActive(true);
			status = creditCardDao.update(creditCard);
		}
		return status;
	}

}
