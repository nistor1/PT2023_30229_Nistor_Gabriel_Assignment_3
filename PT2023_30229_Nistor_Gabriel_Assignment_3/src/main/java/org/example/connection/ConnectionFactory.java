package org.example.connection;

import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.*;

public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DBURL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USER = "sys as sysdba";
    private static final String PASS = "bazadate1";

    private static ConnectionFactory singleInstance = new ConnectionFactory();
    /**
     * ConnectionFactory constructor
     */
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Creates a connection to the database
     * @return Connection object
     */
    private Connection createConnection() {

        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error!");
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return connection;
    }


    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    public static void close(Connection connection) {
        try{
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(Statement statement) {
        try{
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet resultSet) {
        try{
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
