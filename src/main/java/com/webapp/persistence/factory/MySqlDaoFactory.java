package com.webapp.persistence.factory;

import com.webapp.persistence.dao.AccountDao;
import com.webapp.persistence.dao.CreditCardDao;
import com.webapp.persistence.dao.PaymentDao;
import com.webapp.persistence.dao.UserDao;
import com.webapp.persistence.dao.impl.AccountDaoImpl;
import com.webapp.persistence.dao.impl.CreditCardDaoImpl;
import com.webapp.persistence.dao.impl.PaymentDaoImpl;
import com.webapp.persistence.dao.impl.UserDaoImpl;

/**
 * MySqlDaoFactory returns new instances of data access objects
 *
 */
public class MySqlDaoFactory {

	/**
	 * @return UserDao
	 */
	public static UserDao createUserDao() {
		return UserDaoImpl.getInstance();
	}

	/**
	 * @return PaymentDao
	 */
	public static PaymentDao createPaymentDao() {
		return PaymentDaoImpl.getInstance();
	}

	/**
	 * @return CreditCardDao
	 */
	public static CreditCardDao createCreditCardDao() {
		return CreditCardDaoImpl.getInstance();
	}

	/**
	 * @return AccountDao
	 */
	public static AccountDao createAccountDao() {
		return AccountDaoImpl.getInstance();
	}

}
