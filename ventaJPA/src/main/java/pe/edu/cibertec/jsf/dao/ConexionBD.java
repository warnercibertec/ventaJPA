package pe.edu.cibertec.jsf.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
	
	private Connection cn;

	public Connection getCn() {
		return cn;
	}

	public void setCn(Connection cn) {
		this.cn = cn;
	}
	
	
	
	public void conectar() throws Exception{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ventaDB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"cotrina","mysql");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void cerrar() throws Exception {
		try {
			if(cn != null){
				if(cn.isClosed() == false){
					cn.close();
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	

}
