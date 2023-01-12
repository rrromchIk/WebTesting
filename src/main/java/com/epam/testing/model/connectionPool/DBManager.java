package com.epam.testing.model.connectionPool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connection pool for Database
 *
 * @author rom4ik
 */

public class DBManager {
    private static DataSource ds;
    private static final DBManager instance;

    static {
        try {
            Context initContext = new InitialContext();
            ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/web_testing");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        instance = new DBManager();
    }

    private DBManager() {}

    /**
     * Singleton.
     */
    public static synchronized DBManager getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}