package org.example.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DBURL = "jdbc:oracle:thin:@localhost:1521:schooldb";
    private static final String USER = "sys as sysdba";
    private static final String PASS = "bazadate1";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
/*
    private Connection createConnection() {

    }

    public static Connection getConnection() {

    }
*/
    public static void close(Connection connection) {

    }
    public static void close(Statement statement) {

    }

    public static void close(ResultSet resultSet) {

    }
}
