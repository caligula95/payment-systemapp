package com.webapp.config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class ConnectionPool {
	private static DataSource datasource;

    /**
     * Singleton requirement
     */
    private ConnectionPool() {
    }

    /**
     * Getting parameters from file,(in context.xml resource is
     * created).
     *
     * @return DataSource, from which connection to db can be gotten.
     */
    public static synchronized DataSource getInstance() {
        if (datasource == null) {
            try {
                Context initCtx = new InitialContext();
                Context envCtx = (Context) initCtx.lookup("java:comp/env");
                datasource = (DataSource) envCtx.lookup("jdbc/web");
            } catch (NamingException ex) {
                Logger.getLogger(ConnectionPool.class.getName()).error("NamingException " + ex.getMessage());;
            }
        }
        return datasource;
    }
}
