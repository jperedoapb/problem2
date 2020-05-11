package org.comviva.jdbc.h2.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.comviva.jdbc.h2.domain.Comviva;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComvivaJdbc {

	private final Logger logger = LoggerFactory.getLogger(ComvivaJdbc.class);
	private static final String SQL_SELECT = "SELECT id, filename, filevalue, processdata from comviva";
	private static final String SQL_INSERT = "INSERT INTO COMVIVA (filename, filevalue, processdata) VALUES (?,?,?)";
	private static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS COMVIVA (id  bigint auto_increment primary key, "+
											 "Filename varchar(208),Filevalue double, ProcessData double)";
	
	public List<Comviva> select(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Comviva comviva = null;
		List <Comviva> comvivas = new ArrayList<Comviva>();
		
		try {
			conn = Conexion.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery();
			while ( rs.next()) {
				int id_comviva = rs.getInt("id");
				String fileName = rs.getString("FileName");
				float fileValue = rs.getFloat("Filevalue");
				float processData = rs.getFloat("processdata");
				
				comviva = new Comviva();
				comviva.setId(id_comviva);
				comviva.setFileName(fileName);
				comviva.setFileValue(fileValue);
				comviva.setProcessData(processData);
				
				comvivas.add(comviva);
				
			}
		} catch (SQLException e) {
			logger.error("Se ha producido un error:  ComvivaJdbc::select {}",ComvivaJdbc.class,e);
			
		} finally {
			Conexion.close(rs);
			Conexion.close(stmt);
			Conexion.close(conn);
		}
		
		return comvivas;
	}
	
	public int insert(Comviva comviva) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			conn = Conexion.getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setString(1,comviva.getFileName());
			stmt.setDouble(2, comviva.getFileValue());
			stmt.setDouble(3, comviva.getProcessData());
			
			rows = stmt.executeUpdate();
			logger.info("ejecutando query: {}",SQL_INSERT);
			logger.info("Registros afectados: {}", rows);
		} 
		catch (SQLException ex) {			
			logger.error("Se ha producido un error:  ComvivaJdbc::insert {}",ComvivaJdbc.class,ex);
		}
		finally {
			Conexion.close(stmt);
			Conexion.close(conn);
		}
		
		return rows;
	}
	
	public boolean create() {
		Connection conn = null;
		PreparedStatement stmt = null;
		int tables = 0;
		try {
			conn = Conexion.getConnection();
			stmt = conn.prepareStatement(SQL_CREATE);
			tables=stmt.executeUpdate();
			logger.info("Tablas creadas: {}", tables);
			return true;
		} catch (Exception e) {
			logger.error("Se ha producido un error:  ComvivaJdbc::create {}",ComvivaJdbc.class,e);
			return false;
		}
	}
}
