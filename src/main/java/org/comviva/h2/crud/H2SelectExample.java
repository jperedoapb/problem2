package org.comviva.h2.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Select PreparedStatement JDBC Example
 * 
 * @author Ramesh Fadatare
 *
 */
public class H2SelectExample {
    private static final String QUERY = "select id,Filename,Filevalue,ProcessData from comviva";

    public static void main(String[] args) {

        // using try-with-resources to avoid closing resources (boiler plate code)

        // Step 1: Establishing a Connection
        try {
        	Connection connection = H2JDBCUtils.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setInt(1, 1);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String Filename = rs.getString("Filename");
                String Filevalue = rs.getString("Filevalue");
                String ProcessData = rs.getString("ProcessData");
                String password = rs.getString("password");
                System.out.println(id + "," + Filename + "," + Filevalue + "," + ProcessData + "," + password);
            }
        } catch (SQLException e) {
        	H2JDBCUtils.printSQLException(e);
        }
        // Step 4: try-with-resource statement will auto close the connection.
    }
}
