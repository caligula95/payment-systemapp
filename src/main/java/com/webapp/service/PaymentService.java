package com.webapp.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.webapp.entity.Account;
import com.webapp.entity.CreditCard;
import com.webapp.entity.Payment;
import com.webapp.persistence.dao.AccountDao;
import com.webapp.persistence.dao.CreditCardDao;
import com.webapp.persistence.dao.PaymentDao;
import com.webapp.persistence.factory.MySqlDaoFactory;

/**
 * Provides service methods for PaymentDao. Layout between DAO and Command
 * 
 * @see PaymentDao
 */
public class PaymentService {

	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = LogManager.getLogger(PaymentService.class);

	private AccountDao accountDao = MySqlDaoFactory.createAccountDao();
	private CreditCardDao creditCardDao = MySqlDaoFactory.createCreditCardDao();
	private PaymentDao paymentDao = MySqlDaoFactory.createPaymentDao();

	private PaymentService() {

	}

	/**
	 * Singleton instance
	 */
	private static PaymentService instance = null;

	public static synchronized PaymentService getInstance() {
		if (instance == null)
			instance = new PaymentService();
		return instance;
	}

	/**
	 * Checks if card of receiver is available and returns it
	 * 
	 * @param number
	 * @return creditCard
	 */
	private CreditCard checkAvailableCard(String number) {
		CreditCard creditCard = creditCardDao.findCreditCardByCardNumber(number);
		if (creditCard != null) {
			if (!creditCard.getIsActive())
				creditCard = null;
		}
		return creditCard;
	}

	/**
	 * Checks and form final sum with percent for payment
	 * 
	 * @param sum
	 * @param account
	 * @param percent
	 * @return
	 */
	private BigDecimal checkAvailableSum(BigDecimal sum, Account account, Double percent) {
		BigDecimal finalSum = null;
		BigDecimal balance = account.getBalance();
		BigDecimal summa = sum.add(sum.multiply(BigDecimal.valueOf(percent)));
		if (summa.compareTo(balance) == -1 || summa.compareTo(balance) == 0)
			finalSum = summa;
		return finalSum;

	}

	/**
	 * Checks if account isn't blocked and withdraw funds from account
	 * 
	 * @param account
	 * @param balance
	 */
	private void withdrawFunds(Account account, BigDecimal balance) {
		if (!account.getIsBlocked()) {
			account.setBalance(balance);
			accountDao.update(account);
		} else
			LOGGER.info("Attempt to withdraw funds in block account");
	}

	/**
	 * Set new balance to account who receives funds
	 * 
	 * @param account
	 * @param balance
	 */
	private void addFunds(Account account, BigDecimal balance) {
		account.setBalance(balance);
		accountDao.update(account);
	}

	/**
	 * Checks all conditions, forms payment and adds is to database
	 * 
	 * @param sum
	 * @param number
	 * @param accountId
	 * @param percent
	 * @param appointment
	 * @return 0 if error and paymentId if success
	 */
	public int formPayment(BigDecimal sum, String number, Integer accountId, Double percent, String appointment) {
		Payment payment = new Payment();
		payment.setAccountId(accountId);
		payment.setCardNumber(number);
		payment.setSum(sum);
		payment.setAppointment(appointment);
		payment.setDate(new Date().toString());
		Account accountFrom = accountDao.findAccountById(accountId);
		CreditCard receiverCard = checkAvailableCard(number);
		BigDecimal suma = checkAvailableSum(sum, accountFrom, percent);
		if (receiverCard != null && suma != null) {
			Account accountTo = accountDao.findAccountById(receiverCard.getAccountId());
			withdrawFunds(accountFrom, accountFrom.getBalance().subtract(suma));
			addFunds(accountTo, accountTo.getBalance().add(sum));
			payment.setCondition(true);
		} else {
			payment.setCondition(false);
		}
		return paymentDao.create(payment);
	}

	/**
	 * Finds all payments by account id. This method is used in order to avoid
	 * dao layer in commands
	 * 
	 * @param accountId
	 * @return
	 */
	public List<Payment> findAllPaymentsByAccountId(Integer accountId) {
		return paymentDao.findAllPaymentsByAccountId(accountId);
	}
}
