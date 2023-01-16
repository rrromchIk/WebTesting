package com.epam.testing.model.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * DataSource configuration
 *
 * @author rom4ik
 */

public class DataSource {
    private static javax.sql.DataSource ds;
    private static final DataSource instance;

    static {
        try {
            Context initContext = new InitialContext();
            ds = (javax.sql.DataSource)initContext.lookup("java:/comp/env/jdbc/web_testing");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        instance = new DataSource();
    }

    private DataSource() {}

    /**
     * Singleton.
     */
    public static synchronized DataSource getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}