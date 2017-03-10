package com.webapp.persistence.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.webapp.entity.Account;
import com.webapp.persistence.dao.AccountDao;

/**
 * Realizes methods from AccountDao interface
 * 
 * @see AccountDao
 */
public class AccountDaoImpl implements AccountDao {

	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = LogManager.getLogger(AccountDaoImpl.class);

	/**
	 * QueryExecutor instance
	 * 
	 * @see QueryExecutor
	 */
	private QueryExecutor executor = QueryExecutor.getInstance();

	/**
	 * SQL queries
	 */
	private static final String CREATE_ACCOUNT = "insert into account(number, balance, user_Id, is_blocked) values(?,?,?,?)";
	private static final String FIND_ALL_USER_ACCOUNTS = "select * from account where user_Id = ?";
	private static final String UPDATE_ACCOUNT = "update account set balance=?, is_blocked=? where account_Id=?";
	private static final String FIND_ACCOUNT_BY_ID = "select * from account where account_Id=?";

	private AccountDaoImpl() {
	}

	/**
	 * Singleton instance
	 */
	private static AccountDaoImpl instance = null;

	public static synchronized AccountDaoImpl getInstance() {
		if (instance == null) {
			instance = new AccountDaoImpl();
		}
		return instance;
	}

	public int create(Account entity) {
		Integer entityId = null;
		Object[] args = { entity.getNumber(), entity.getBalance(), entity.getUserId(), entity.getIsBlocked() };
		entityId = executor.executeStatement(CREATE_ACCOUNT, args);
		return entityId;
	}

	@Override
	public int update(Account entity) {
		Integer entityId = null;
		Object args[] = { entity.getBalance(), entity.getIsBlocked(), entity.getAccountId() };
		entityId = executor.executeStatement(UPDATE_ACCOUNT, args);
		return entityId;

	}

	@Override
	public Account findAccountById(Integer id) {
		Account account = null;
		try {
			ResultSet rs = executor.getResultSet(FIND_ACCOUNT_BY_ID, id);
			if (rs.next()) {
				account = createEntity(rs);
			}
		} catch (SQLException e) {
			LOGGER.error("SQL exception " + e.getMessage());
		}
		return account;
	}

	@Override
	public List<Account> findAllAccountsOfUser(Integer userId) {
		Account account = null;
		List<Account> accounts = new ArrayList<>();
		try {
			ResultSet rs = executor.getResultSet(FIND_ALL_USER_ACCOUNTS, userId);
			while (rs.next()) {
				account = createEntity(rs);
				accounts.add(account);
			}
		} catch (SQLException e) {
			LOGGER.error("SQL exception " + e.getMessage());
		}
		return accounts;
	}

	/**
	 * Creates entity from result set
	 * 
	 * @param rs
	 * @return entity
	 */
	private Account createEntity(ResultSet rs) {
		Account account = new Account();
		try {
			account.setAccountId(rs.getInt("account_id"));
			account.setBalance(rs.getBigDecimal("balance"));
			account.setNumber(rs.getString("number"));
			account.setUserId(rs.getInt("user_Id"));
			account.setIsBlocked(rs.getBoolean("is_blocked"));
		} catch (SQLException e) {
			LOGGER.error("SQL exception " + e.getMessage());
		}
		return account;
	}

}
