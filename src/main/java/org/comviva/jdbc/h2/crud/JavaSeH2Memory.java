package org.comviva.jdbc.h2.crud;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException; 
import java.sql.Statement; 

public class JavaSeH2Memory {
	
	// JDBC driver name and database URL 
	   static final String JDBC_DRIVER = "org.h2.Driver";   
	   static final String DB_URL = "jdbc:h2:~/testdb";  
	   
	   //  Database credentials 
	   static final String USER = "sa"; 
	   static final String PASS = ""; 
	   
	   private static final String INSERT_USERS_SQL = "INSERT INTO  comviva (filename,filevalue,processdata) " +
		        "VALUES " +
		        " (?, ?, ?);";
	   
	   
	public static void main(String[] args) {

		Connection conn = null; 
	      Statement stmt = null; 
	      try { 
	         // STEP 1: Register JDBC driver 
	         Class.forName(JDBC_DRIVER); 
	             
	         //STEP 2: Open a connection 
	         System.out.println("Connecting to database..."); 
	         conn = DriverManager.getConnection(DB_URL,USER,PASS);  
	         
	         //STEP 3: Execute a query 
	         System.out.println("Creating table in given database..."); 
	         stmt = conn.createStatement(); 
	         
	         //String sql = "DROP table comviva";
				
				
				  String sql = "create table comviva ( " +
				  "id  bigint auto_increment primary key, " + "Filename varchar(208)," +
				  "Filevalue double,\r\n" + "ProcessData double\r\n);";
				 
				 
				
	       
	            
	           
	            // Step 3: Execute the query or update query
	           
	         
	         
	         stmt.executeUpdate(sql);
	         System.out.println("Created table in given database..."); 
	        
	         
	         // STEP 4: Clean-up environment 
	         stmt.close(); 
	         conn.close(); 
	      } catch(SQLException se) { 
	         //Handle errors for JDBC 
	         se.printStackTrace(); 
	      } catch(Exception e) { 
	         //Handle errors for Class.forName 
	         e.printStackTrace(); 
	      } finally { 
	         //finally block used to close resources 
	         try{ 
	            if(stmt!=null) stmt.close(); 
	         } catch(SQLException se2) { 
	         } // nothing we can do 
	         try { 
	            if(conn!=null) conn.close(); 
	         } catch(SQLException se){ 
	            se.printStackTrace(); 
	         } //end finally try 
	      } //end try 
	      System.out.println("Goodbye!");
	   }
	
	public static java.sql.Time getCurrentJavaSqlTime() {
	    java.util.Date date = new java.util.Date();
	    return new java.sql.Time(date.getTime());
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
