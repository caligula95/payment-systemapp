package com.webapp.persistence.dao;

import java.util.List;

import com.webapp.entity.User;

/**
 * The UserDao interface provides methods for retrieving data for User entity
 */
public interface UserDao {

	/**
	 * Finds all users
	 * 
	 * @return List of User object
	 */
	List<User> findAll();

	/**
	 * Finds User entity by userId
	 * 
	 * @param id
	 * @return User of null if user not found
	 */
	User findById(Integer id);

	/**
	 * Retrieves user by login and password
	 * 
	 * @param login
	 * @param password
	 * @return User of null if user not found
	 */
	User findByLoginAndPassword(String login, String password);

	/**
	 * Finds user by phone
	 * 
	 * @param phone
	 * @return User of null if user not found
	 */
	User findByPhone(String phone);

	/**
	 * Inserts new entity into database
	 * 
	 * @param entity
	 * @return id of created entity or 0 if error occurred
	 */
	int create(User entity);

	/**
	 * Updates existing account
	 * 
	 * @param entity
	 * @return id of updated entity
	 */
	int update(User entity);
}
