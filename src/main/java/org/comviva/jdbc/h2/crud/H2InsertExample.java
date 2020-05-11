package org.comviva.jdbc.h2.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2InsertExample {

	private static final String INSERT_USERS_SQL = "INSERT INTO comviva " +
	        "VALUES " +
	        " (?, ?, ?);";

	    public static void main(String[] argv) throws SQLException {
	        H2InsertExample createTableExample = new H2InsertExample();
	        createTableExample.insertRecord();
	    }

	    public void insertRecord() throws SQLException {
	        System.out.println(INSERT_USERS_SQL);
	        // Step 1: Establishing a Connection
	        try {
	        	Connection connection = H2Utils.getConnection();
	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
	            		
	            preparedStatement.setString(1, "archivo.txt");
	            preparedStatement.setDouble(2, 35.4);
	            preparedStatement.setInt(3, 25);
	            
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {

	            // print SQL exception information
	            H2Utils.printSQLException(e);
	        }

	        // Step 4: try-with-resource statement will auto close the connection.
	    }
}
