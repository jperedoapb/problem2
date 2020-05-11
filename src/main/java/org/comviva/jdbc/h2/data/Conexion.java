package org.comviva.jdbc.h2.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Conexion {
	
	private static Logger logger = LoggerFactory.getLogger(Conexion.class);
	
	public static Connection getConnection() throws SQLException {
		String url = null;
		String username = null;
		String password = null;
		try {
		
			// /Examen2/src/main/resources/connection.prop
			FileInputStream fis=new FileInputStream("..\\examen2\\src\\main\\resources\\connection.prop");
			
			Properties p=new Properties (); 
	        p.load (fis);	        
	        url = (String) p.get ("url"); 
	        username = (String) p.get ("username"); 
	        password = (String) p.get ("password");
	        
	          
		} catch (FileNotFoundException e) {
			logger.error("Se ha producido un eror al buscar el archivo properties {}",Conexion.class,e);
			
		} catch (IOException e) {
			logger.error("Se ha producido un eror de I/O {}",Conexion.class,e);
			
		}
		return DriverManager.getConnection(url,username,password);
		
	}
	
	public static void close (ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException ex) {
			logger.error("Se ha producido un error ResultSet {}",Conexion.class,ex);
			
		}
	}
	
	public static void close (PreparedStatement stmt) {
		try {
			stmt.close();
		} catch (SQLException ex) {
			logger.error("Se ha producido un eror al cerrar el statment {}",Conexion.class,ex);
			
		}
	}
	
	public static void close (Connection conn) {
		try {
			conn.close();
		} catch (SQLException ex) {
			
			logger.error("Se ha producido un eror al cerrar la conexion {}",Conexion.class,ex);
		}
	}
	
	private Conexion() {
	    throw new IllegalStateException("Utility class");
	  }


}
