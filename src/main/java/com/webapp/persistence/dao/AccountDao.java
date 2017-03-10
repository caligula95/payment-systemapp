package com.webapp.persistence.dao;

import java.util.List;

import com.webapp.entity.Account;

/**
 * The AccountDao interface provides methods for retrieving data for Account
 * entity
 */
public interface AccountDao {

	/**
	 * Inserts new entity into database
	 * 
	 * @param entity
	 * @return id of created entity or 0 if error occurred
	 */
	int create(Account entity);

	/**
	 * Updates existing account
	 * 
	 * @param entity
	 * @return id of updated entity
	 */
	int update(Account entity);

	/**
	 * Finds account by account id
	 * 
	 * @param id
	 * @return account or null if account doesn't found
	 */
	Account findAccountById(Integer id);

	/**
	 * Returns all accounts of user
	 * 
	 * @param userId
	 * @return List of accounts
	 */
	List<Account> findAllAccountsOfUser(Integer userId);
}
