package com.epam.javalab13.dao;

import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class for getting connection to database
 */
public class ConnectionPool {

    private static Logger logger = Logger.getLogger(ConnectionPool.class);

    private static ConnectionPool pool = null;
    private static DataSource dataSource;

    /**
     * Private constructor for Singleton
     */
    private ConnectionPool() {

        /*
        Database properties
         */
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:mysql://localhost:3306/totalizator");//DataBase URL
        p.setDriverClassName("com.mysql.jdbc.Driver");//Class for JDBC
        p.setUsername("root");//Connection username
        p.setPassword("root");//Connection password
        p.setValidationQuery("SELECT 1");//For some ? validation
        p.setMaxActive(100);//Maximum number of active connections
        p.setInitialSize(5);//Number of created connections on pool start


        /*
         * Classes for connection
         */
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");

        dataSource = new DataSource();
        dataSource.setPoolProperties(p);

        logger.info("Pull object created!");
    }


    /**
     * Creating connection using lazy initialization
     * @return Connection object
     */
    public static Connection getConnection() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("Exception while getting connection:",e);
        }
        return null;
    }
}
