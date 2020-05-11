package org.comviva.jdbc.h2.crud;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class H2CreateExample {

	private static final String createTableSQL = "create table comviva ( " + 
			"id  bigint auto_increment primary key, " +
	        "Filename varchar(208)," + 
			"Filevalue double,\r\n" + 
	        "ProcessData time\r\n);"; 
	        

	    public static void main(String[] argv) throws SQLException {
	        H2CreateExample createTableExample = new H2CreateExample();
	        createTableExample.createTable();
	    }

	    public void createTable() throws SQLException {

	        System.out.println(createTableSQL);
	        
	        try  {
	        	// Step 1: Establishing a Connection
	        	Connection connection = H2Utils.getConnection();
	        	// Step 2:Create a statement using connection object
	            Statement statement = connection.createStatement();
	            // Step 3: Execute the query or update query
	            statement.execute(createTableSQL);

	        } catch (SQLException e) {
	            // print SQL exception information
	            H2Utils.printSQLException(e);
	        } 

	    }
}
