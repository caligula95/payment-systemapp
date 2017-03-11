package com.webapp.persistence.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.webapp.entity.Role;
import com.webapp.entity.User;
import com.webapp.persistence.dao.UserDao;

/**
 * Realizes methods from UserDao interface
 * 
 * @see UserDao
 */
public class UserDaoImpl implements UserDao {

	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

	/**
	 * QueryExecutor instance
	 * 
	 * @see QueryExecutor
	 */
	private QueryExecutor executor = QueryExecutor.getInstance();

	/**
	 * SQL queries
	 */
	private static final String FIND_ALL = "SELECT users.user_Id, users.phone, users.password,"
			+ "users.email, users.username, users.surname, users.role_Id, role.name FROM users JOIN role ON users.role_id = role.id and users.role_id=1";
	private static final String FIND_BY_ID = "SELECT users.user_Id, users.phone, users.password,"
			+ "users.email, users.username, users.surname, users.role_Id, role.name FROM users JOIN role ON users.role_id = role.id WHERE users.user_id = ?";
	private static final String FIND_BY_LOGIN_PASSWORD = "SELECT users.user_Id, users.phone, users.password,"
			+ "users.email, users.username, users.surname, users.role_Id, role.name FROM users JOIN role ON users.role_id = role.id WHERE users.phone = ? AND users.password = ?";
	private static final String CREATE_USER = "INSERT INTO users (phone, password, email, username, surname, role_id) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_USER = "UPDATE users SET password = ?, email = ? WHERE user_id = ?";
	private static final String FIND_BY_PHONE = "SELECT users.user_Id, users.phone, users.password,"
			+ "users.email, users.username, users.surname, users.role_Id, role.name FROM users JOIN role ON users.role_id = role.id WHERE users.phone = ?";

	private UserDaoImpl() {

	}

	/**
	 * Singleton instance
	 */
	private static UserDaoImpl instance = null;

	public static synchronized UserDaoImpl getInstance() {
		if (instance == null) {
			instance = new UserDaoImpl();
		}
		return instance;
	}

	@Override
	public List<User> findAll() {
		User user = null;
		List<User> users = new ArrayList<>();
		try {
			ResultSet rs = executor.getResultSet(FIND_ALL);
			while (rs.next()) {
				user = createEntity(rs);
				users.add(user);
			}
		} catch (SQLException e) {
			LOGGER.error("SQL exception " + e.getMessage());
		}
		return users;
	}

	@Override
	public User findById(Integer id) {
		User user = null;
		try {
			ResultSet rs = executor.getResultSet(FIND_BY_ID, id);
			if (rs.next()) {
				user = createEntity(rs);
			}
		} catch (SQLException e) {
			LOGGER.error("SQL exception " + e.getMessage());
		}
		return user;
	}

	@Override
	public User findByPhone(String phone) {
		User user = null;
		try {
			ResultSet rs = executor.getResultSet(FIND_BY_PHONE, phone);
			if (rs.next())
				user = createEntity(rs);
		} catch (SQLException e) {
			LOGGER.error("SQL exception " + e.getMessage());
		}
		return user;
	}

	@Override
	public int create(User entity) {
		Object[] args = { entity.getPhone(), entity.getPassword(), entity.getEmail(), entity.getName(),
				entity.getSurname(), entity.getRole().getId() };
		return executor.executeStatement(CREATE_USER, args);

	}

	@Override
	public int update(User entity) {
		Object[] args = { entity.getPassword(), entity.getEmail(), entity.getUserId() };
		return executor.executeStatement(UPDATE_USER, args);
	}

	@Override
	public User findByLoginAndPassword(String login, String password) {
		User user = null;
		if (login != null && password != null) {
			try {
				ResultSet rs = executor.getResultSet(FIND_BY_LOGIN_PASSWORD, login, password);
				if (rs.next()) {
					user = createEntity(rs);
				}
			} catch (SQLException e) {
				LOGGER.error("SQL exception " + e.getMessage());
			}
		}
		return user;
	}

	/**
	 * Creates entity from result set
	 * 
	 * @param rs
	 * @return entity
	 */
	private User createEntity(ResultSet rs) {
		User user = new User();
		try {
			user.setUserId(rs.getInt("user_Id"));
			user.setPhone(rs.getString("phone"));
			user.setPassword(rs.getString("password"));
			user.setEmail(rs.getString("email"));
			user.setName(rs.getString("username"));
			user.setSurname(rs.getString("surname"));
			Role role = new Role();
			role.setId(rs.getInt("role_id"));
			role.setRolename(rs.getString("name"));
			user.setRole(role);
		} catch (SQLException e) {
			LOGGER.error("SQL exception1 " + e.getMessage());
		}
		return user;
	}
}
