package com.webapp.persistence.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.webapp.entity.Payment;
import com.webapp.persistence.dao.PaymentDao;

/**
 * Realizes methods from PaymentDao interface
 * 
 * @see PaymentDao
 */
public class PaymentDaoImpl implements PaymentDao {

	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = LogManager.getLogger(PaymentDaoImpl.class);

	/**
	 * QueryExecutor instance
	 * 
	 * @see QueryExecutor
	 */
	private QueryExecutor executor = QueryExecutor.getInstance();

	/**
	 * SQL queries
	 */
	private static final String CREATE_PAYMENT = "insert into payment(account_id, summa, appointment, date, cond, card_number)"
			+ " values(?,?,?,?,?,?)";
	private static final String FIND_PAYMENTS = "select * from payment where account_id=?";
	private static final String FIND_PAYMENT_BY_ID = "select * from payment where payment_id=?";

	private PaymentDaoImpl() {
	}

	/**
	 * Singleton instance
	 */
	private static PaymentDaoImpl instance = null;

	public static synchronized PaymentDaoImpl getInstance() {
		if (instance == null) {
			instance = new PaymentDaoImpl();
		}
		return instance;
	}

	@Override
	public int create(Payment entity) {
		Integer entityId = null;
		Object[] args = { entity.getAccountId(), entity.getSum(), entity.getAppointment(), entity.getDate(),
				entity.getCondition(), entity.getCardNumber() };
		entityId = executor.executeStatement(CREATE_PAYMENT, args);
		return entityId;
	}

	@Override
	public List<Payment> findAllPaymentsByAccountId(Integer accountId) {
		Payment payment = null;
		List<Payment> payments = new ArrayList<>();
		try {
			ResultSet rs = executor.getResultSet(FIND_PAYMENTS, accountId);
			while (rs.next()) {
				payment = createEntity(rs);
				payments.add(payment);
			}
		} catch (SQLException e) {
			LOGGER.error("SQL exception " + e.getMessage());
		}
		return payments;
	}

	@Override
	public Payment findPaymentByPaymentId(Integer paymentId) {
		Payment payment = null;
		try {
			ResultSet rs = executor.getResultSet(FIND_PAYMENT_BY_ID, paymentId);
			if (rs.next())
				payment = createEntity(rs);
		} catch (SQLException e) {
			LOGGER.error("SQL exception " + e.getMessage());
		}
		return payment;

	}

	/**
	 * Creates entity from result set
	 * 
	 * @param rs
	 * @return entity
	 */
	private Payment createEntity(ResultSet rs) {
		Payment payment = new Payment();
		try {
			payment.setPaymentId(rs.getInt("payment_Id"));
			payment.setAccountId(rs.getInt("account_id"));
			payment.setAppointment(rs.getString("appointment"));
			payment.setCondition(rs.getBoolean("cond"));
			payment.setDate(rs.getString("date"));
			payment.setSum(rs.getBigDecimal("summa"));
			payment.setCardNumber(rs.getString("card_number"));
		} catch (SQLException e) {
			LOGGER.error("SQL exception " + e.getMessage());
		}
		return payment;
	}

}
