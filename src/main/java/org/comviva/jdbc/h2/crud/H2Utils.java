package org.comviva.jdbc.h2.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Utils {

	private static String driverClassName = "org.h2.Driver";
	private static String jdbcURL = "jdbc:h2:mem:testdb";
    private static String jdbcUsername = "sa";
    private static String jdbcPassword = "";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            System.out.println("Error de driver");
            e.printStackTrace();
        }
        return connection;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
