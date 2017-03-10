package com.webapp.persistence.dao;

import java.util.List;

import com.webapp.entity.CreditCard;

/**
 * The CreditCardDao interface provides methods for retrieving data for
 * CreditCard entity
 */
public interface CreditCardDao {

	List<CreditCard> findCardsByAccountId(Integer id);

	/**
	 * Inserts new entity into database
	 * 
	 * @param entity
	 * @return id of created entity or 0 if error occurred
	 */
	int create(CreditCard entity);

	/**
	 * Removes credit card by id
	 * 
	 * @param id
	 * @return id of deleted entity or 0 if error occurred
	 */
	int delete(Integer id);

	/**
	 * Updates creditCard status
	 * 
	 * @param entity
	 * @return entity
	 */
	int update(CreditCard entity);

	/**
	 * Retrieves credit card entity by it number
	 * 
	 * @param number
	 * @return CreditCard or null if credit card not found
	 */
	CreditCard findCreditCardByCardNumber(String number);
}
