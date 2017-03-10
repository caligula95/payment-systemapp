package com.webapp.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.webapp.entity.Role;
import com.webapp.entity.User;
import com.webapp.persistence.dao.UserDao;
import com.webapp.persistence.factory.MySqlDaoFactory;
import com.webapp.util.PasswordEncryptor;

/**
 * Provides service methods for UserDao. Layout between DAO and Command
 * 
 * @see UserDao
 */
public class UserService {

	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = LogManager.getLogger(UserService.class);

	private static final String ADMIN = "admin";
	private static final String CLIENT = "client";
	private static final String ALL = "all";
	private PasswordEncryptor encryptor = new PasswordEncryptor();
	private UserDao userDao = MySqlDaoFactory.createUserDao();

	private UserService() {
	}

	/**
	 * Singleton instance
	 */
	private static UserService instance = null;

	public static synchronized UserService getInstance() {
		if (instance == null)
			instance = new UserService();
		return instance;
	}

	/**
	 * Check user role
	 * 
	 * @param user
	 * @return role name
	 */
	public String checkRole(User user) {
		String role = null;
		if (user == null)
			role = ALL;
		else if (user.getRole().getRolename().equalsIgnoreCase(Role.ROLE_ADMIN))
			role = ADMIN;
		else if (user.getRole().getRolename().equalsIgnoreCase(Role.ROLE_CLIENT))
			role = CLIENT;
		return role;
	}

	/**
	 * Checks if user isn't in database and adds new user to the system
	 * 
	 * @param name
	 * @param surname
	 * @param phone
	 * @return 0 if error and userId if success
	 */
	public int addNewUser(String name, String surname, String phone) {
		int status = 0;
		User user = null;
		user = userDao.findByPhone(phone);
		if (user != null) {
			LOGGER.info("Attemption to create existent user");
		} else {
			user = new User();
			user.setPhone(phone);
			user.setName(name);
			user.setSurname(surname);
			Role role = new Role();
			role.setRolename(Role.ROLE_CLIENT);
			role.setId(1);
			user.setRole(role);
			status = userDao.create(user);
		}

		return status;
	}

	/**
	 * Registers user in system by phone. Check if user has already registered
	 * in payment system as client and allows him to register in payment system
	 * 
	 * @param email
	 * @param password
	 * @param phone
	 * @return 0 if error and userId if success
	 */
	public int registerUser(String email, String password, String phone) {
		int status = 0;
		User user = findUserInSystem(phone);
		if (user != null) {
			password = encryptor.encode(password);
			user.setEmail(email);
			user.setPassword(password);
			status = userDao.update(user);
		}
		return status;
	}

	/**
	 * Finds user by login and encrypted password
	 * 
	 * @param login
	 * @param password
	 * @return
	 */
	public User loginUser(String login, String password) {
		User user = null;
		password = encryptor.encode(password);
		user = userDao.findByLoginAndPassword(login, password);
		return user;
	}

	/**
	 * Finds user by phone and checks if user is registered already
	 * 
	 * @param phone
	 * @return null if user is registered of user if not
	 */
	private User findUserInSystem(String phone) {
		User user = null;
		user = userDao.findByPhone(phone);
		if (user.getPassword() != null)
			// set user to null if his password is already in system
			user = null;
		return user;
	}

	/**
	 * This method is used in order to avoid dao layer in commands
	 * 
	 * @see UserDaoImpl
	 * @param userId
	 * @return
	 */
	public User findUserById(Integer userId) {
		return userDao.findById(userId);
	}

	/**
	 * This method is used in order to avoid dao layer in commands
	 * 
	 * @see UserDaoImpl
	 * @return
	 */
	public List<User> findAll() {
		return userDao.findAll();
	}

}
