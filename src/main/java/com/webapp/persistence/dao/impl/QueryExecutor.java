package com.webapp.persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;
import com.webapp.config.ConnectionPool;

public class QueryExecutor {
	
	private static final Logger LOGGER = LogManager.getLogger(QueryExecutor.class);
	
	/**
	 * Connection instance
	 */
	private Connection connection;
	
	/**
	 * PreparedStatement instance
	 */
	private PreparedStatement preparedStatement;
	
	/**
     * Singleton instance
     */
	private static QueryExecutor instance = null;

	/**
	 * Getting connection from connection pool.
	 *
	 * @see ConnectionPool
	 * @throws SQLException
	 */
	private QueryExecutor() {
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (SQLException e) {
			LOGGER.error("Error while establish connection");
			e.printStackTrace();
		}
	}

	public static QueryExecutor getInstance() {
		if (instance == null)
			instance = new QueryExecutor();
		return instance;
	}

	/**
	 * Inserts an array of objects into prepared statement.
	 *
	 * @param preparedStatement
	 *            statement to be executed
	 * @param values
	 *            array of objects to be inserted
	 * @throws SQLException
	 */
	private void setValues(PreparedStatement preparedStatement, Object... values) throws SQLException {
		for (int i = 0; i < values.length; i++) {
			preparedStatement.setObject(i + 1, values[i]);
		}
	}

	/**
	 * Executes insert(returns id), update and delete queries.
	 *
	 * @param query
	 * @param args
	 * @return if if request is insert
	 */
	public int executeStatement(String query, Object... args) {
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			setValues(preparedStatement, args);
			int res = preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				return res;
			}
		} catch (SQLException e) {
			LOGGER.error("Execute statement error " + e.getMessage());
		}
		return 0;
	}

	/**
	 * Executes select query and returns resultset.
	 *
	 * @param query
	 *            to be executed
	 * @param args
	 * @return result of select queries
	 * @throws SQLException
	 */
	public ResultSet getResultSet(String query, Object... args) throws SQLException {
		preparedStatement = (PreparedStatement) connection.prepareStatement(query);
		setValues(preparedStatement, args);
		return preparedStatement.executeQuery();
	}

	/**
	 * Returns connection to pool.
	 */
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			LOGGER.error("Error while closing connection");
		}
	}
}
