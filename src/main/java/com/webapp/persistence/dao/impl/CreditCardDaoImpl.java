package com.webapp.persistence.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.webapp.entity.CreditCard;
import com.webapp.persistence.dao.CreditCardDao;

/**
 * Realizes methods from CreditCardDao interface
 * 
 * @see CreditCardDao
 */
public class CreditCardDaoImpl implements CreditCardDao {
	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = LogManager.getLogger(CreditCardDaoImpl.class);

	/**
	 * QueryExecutor instance
	 * 
	 * @see QueryExecutor
	 */
	private QueryExecutor executor = QueryExecutor.getInstance();

	/**
	 * SQL queries
	 */
	private static final String FIND_CARDS_BY_ACCOUNT_ID = "select * from credit_card where account_Id=?";
	private static final String FIND_CARD_BY_NUMBER = "select * from credit_card where number = ?";
	private static final String DELETE_CARD = "delete from credit_card where card_Id = ?";
	private static final String CREATE_CARD = "insert into credit_card(number, cvv, validity, is_active, account_Id)"
			+ " values(?,?,?,?,?)";
	private static final String UPDATE = "update credit_card set is_active=? where card_Id=?";

	private CreditCardDaoImpl() {
	}

	/**
	 * Singleton instance
	 */
	private static CreditCardDaoImpl instance = null;

	public static synchronized CreditCardDaoImpl getInstance() {
		if (instance == null) {
			instance = new CreditCardDaoImpl();
		}
		return instance;
	}

	@Override
	public List<CreditCard> findCardsByAccountId(Integer accountId) {
		CreditCard creditCard = null;
		List<CreditCard> creditCards = new ArrayList<>();
		try {
			ResultSet rs = executor.getResultSet(FIND_CARDS_BY_ACCOUNT_ID, accountId);
			while (rs.next()) {
				creditCard = createEntity(rs);
				creditCards.add(creditCard);
			}
		} catch (SQLException e) {
			LOGGER.error("SQL exception " + e.getMessage());
		}
		return creditCards;
	}

	@Override
	public CreditCard findCreditCardByCardNumber(String number) {
		CreditCard creditCard = null;
		try {
			ResultSet rs = executor.getResultSet(FIND_CARD_BY_NUMBER, number);
			if (rs.next()) {
				creditCard = createEntity(rs);
			}
		} catch (SQLException e) {
			LOGGER.error("SQL exception " + e.getMessage());
		}
		return creditCard;
	}

	@Override
	public int create(CreditCard entity) {
		Object args[] = { entity.getNumber(), entity.getCvv(), entity.getValidity(), entity.getIsActive(),
				entity.getAccountId() };
		return executor.executeStatement(CREATE_CARD, args);

	}

	@Override
	public int delete(Integer cardId) {
		return executor.executeStatement(DELETE_CARD, cardId);
	}

	@Override
	public int update(CreditCard entity) {
		return executor.executeStatement(UPDATE, entity.getIsActive(), entity.getCardId());
	}

	/**
	 * Creates entity from result set
	 * 
	 * @param rs
	 * @return entity
	 */
	private CreditCard createEntity(ResultSet rs) {
		CreditCard creditCard = new CreditCard();
		try {
			creditCard.setCardId(rs.getInt("card_id"));
			creditCard.setNumber(rs.getString("number"));
			creditCard.setCvv(rs.getString("cvv"));
			creditCard.setIsActive(rs.getBoolean("is_active"));
			creditCard.setValidity(rs.getString("validity"));
			creditCard.setAccountId(rs.getInt("account_Id"));
		} catch (SQLException e) {
			LOGGER.error("SQL exception " + e.getMessage());
		}
		return creditCard;
	}
}
