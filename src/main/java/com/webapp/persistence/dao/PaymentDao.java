package com.webapp.persistence.dao;

import java.util.List;

import com.webapp.entity.Payment;

/**
 * The PaymentDao interface provides methods for retrieving data for Payment
 * entity
 */
public interface PaymentDao {

	/**
	 * Inserts new entity into database
	 * 
	 * @param entity
	 * @return id of created entity or 0 if error occurred
	 */
	int create(Payment entity);

	/**
	 * Finds payment by payment id
	 * 
	 * @param paymentId
	 * @return Payment of null if payment not found
	 */
	Payment findPaymentByPaymentId(Integer paymentId);

	/**
	 * Retrieves all payments of account by its id
	 * 
	 * @param accountId
	 * @return List of Payment objects
	 */
	List<Payment> findAllPaymentsByAccountId(Integer accountId);

}
