package com.webapp.persistence.dao.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;
import com.webapp.config.ConnectionPool;

public class QueryExecutor {

	private static final Logger LOGGER = LogManager.getLogger(QueryExecutor.class);

	/**
	 * Connection instance
	 */
	private Connection connection = getConnection();

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
	private Connection getConnection() {
		try {
			try {
				Class.forName("org.postgresql.Driver").newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String username = "lxdnpxlomevtpr";
		String password = "26edd033ccceb3abeef55448585c04436fa6d8baca11fd49f90288bb23b37684";
		String dbUrl = "jdbc:postgresql://" + "ec2-54-217-222-254.eu-west-1.compute.amazonaws.com" + ":5432/d3l7p0524to10q";
		try {
			return DriverManager.getConnection(dbUrl, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;
	}

	private QueryExecutor() {
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
