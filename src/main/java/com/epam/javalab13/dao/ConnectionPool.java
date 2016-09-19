package com.epam.javalab13.dao;

import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Class for getting connection to database
 */
public class ConnectionPool {

    private static ResourceBundle bundle = ResourceBundle.getBundle("security/config");//Here stored confidential data
    private static Logger logger = Logger.getLogger(ConnectionPool.class);
    private static DataSource dataSource;

    //Init database properties
    static {
        /*
        Database properties
         */
        PoolProperties p = new PoolProperties();
        p.setUrl(bundle.getString("jdbc.url"));//DataBase URL
        p.setDriverClassName("com.mysql.jdbc.Driver");//Class for JDBC
        p.setUsername(bundle.getString("jdbc.username"));//Connection username
        p.setPassword(bundle.getString("jdbc.password"));//Connection password
        p.setValidationQuery("SELECT 1");//For some ? validation
        p.setMaxActive(Integer.parseInt(bundle.getString("jdbc.maxActive")));//Maximum number of active connections
        p.setInitialSize(5);//Number of created connections on pool start


        /*
         * Classes for connection
         */
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");

        dataSource = new DataSource();
        dataSource.setPoolProperties(p);

        logger.info("Application started!");
    }


    /**
     * Creating connection using lazy initialization
     * @return Connection object
     */
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("Exception while getting connection:",e);
        }
        return null;
    }
}