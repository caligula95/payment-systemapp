package com.webapp.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.webapp.entity.Account;
import com.webapp.entity.CreditCard;
import com.webapp.persistence.dao.AccountDao;
import com.webapp.persistence.dao.CreditCardDao;
import com.webapp.persistence.factory.MySqlDaoFactory;

/**
 * Provides service methods for AccountDao. Layout between DAO and Command
 * 
 * @see AccountDao
 */
public class AccountService {

	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = LogManager.getLogger(AccountService.class);

	private AccountDao accountDao = MySqlDaoFactory.createAccountDao();
	private CreditCardDao creditCardDao = MySqlDaoFactory.createCreditCardDao();

	private AccountService() {

	}

	/**
	 * Singleton instance
	 */
	private static AccountService instance = null;

	public static synchronized AccountService getInstance() {
		if (instance == null)
			instance = new AccountService();
		return instance;
	}

	/**
	 * Finds account by id and unblock it
	 * 
	 * @param accountId
	 * @return 0 if error and accountId if success
	 */
	public int unblockAccount(Integer accountId) {
		Account account = null;
		int status = 0;
		if (accountId != null) {
			account = accountDao.findAccountById(accountId);
			List<CreditCard> creditCards = creditCardDao.findCardsByAccountId(accountId);
			for (CreditCard creditCard : creditCards) {
				creditCard.setIsActive(true);
				creditCardDao.update(creditCard);
			}
			account.setIsBlocked(false);
			status = accountDao.update(account);
		}
		return status;
	}

	/**
	 * Finds account by id and blocks it. Blocks all credit cards that belong to
	 * this account
	 * 
	 * @param accountId
	 * @return 0 if error and accountId if success
	 */
	public int blockAccont(Integer accountId) {
		Account account = null;
		int status = 0;
		if (accountId != null) {
			account = accountDao.findAccountById(accountId);
			List<CreditCard> creditCards = creditCardDao.findCardsByAccountId(accountId);
			for (CreditCard creditCard : creditCards) {
				creditCard.setIsActive(false);
				creditCardDao.update(creditCard);
			}
			account.setIsBlocked(true);
			status = accountDao.update(account);
		}
		return status;
	}

	/**
	 * Checks if account isn't blocked and adds funds
	 * 
	 * @param accountId
	 * @param sum
	 * @return 0 if error and accountId if success
	 */
	public int addFunds(Integer accountId, BigDecimal sum) {
		int status = 0;
		Account account = null;
		if (accountId != null) {
			account = accountDao.findAccountById(accountId);
			if (!account.getIsBlocked()) {
				account.setBalance(account.getBalance().add(sum));
				status = accountDao.update(account);
			} else
				LOGGER.info("Attemption to add funds to blocked account");
		}
		return status;
	}

	/**
	 * Creates new account
	 * 
	 * @param number
	 * @param userId
	 * @param balance
	 * @return id of created account of 0 if error occurred
	 */
	public int createAccount(String number, Integer userId, BigDecimal balance) {
		int status = 0;
		if (number != null && userId != null && balance != null) {
			Account account = new Account();
			account.setNumber(number);
			account.setUserId(userId);
			account.setIsBlocked(false);
			account.setBalance(balance);
			status = accountDao.create(account);
		}
		return status;
	}

	/**
	 * Finds all accounts by userId. This method is used in order to avoid dao
	 * layer in commands
	 * 
	 * @param userId
	 * @return
	 */
	public List<Account> findAllAccountsByUserId(Integer userId) {
		return accountDao.findAllAccountsOfUser(userId);
	}
}
