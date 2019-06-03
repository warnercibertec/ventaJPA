package pe.edu.cibertec.jsf.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pe.edu.cibertec.jsf.model.Persona;
import pe.edu.cibertec.jsf.util.Formato;

public class PersonaDAO extends ConexionBD{
	
	public void registrar(Persona persona) throws Exception{
		try {
			this.conectar();
			PreparedStatement st = this.getCn().prepareStatement(
					"Insert into persona( nombre, apePaterno, apeMaterno, numDocumento, fecNacimiento, numCelular, correo, sexo) "
					+ " values(?, ?, ?, ?, ?, ?, ?, ?)");
			st.setString(1, persona.getNombre());
			st.setString(2, persona.getApePaterno());
			st.setString(3, persona.getApeMaterno());
			st.setString(4, persona.getNumDocumento());
			st.setString(5, Formato.darFormatoFechaToString(persona.getFecNacimiento()));
			st.setString(6, persona.getNumCelular());
			st.setString(7, persona.getCorreo());
			st.setString(8, persona.getSexo());
			st.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally{
			this.cerrar();
		}
	}
	
	
	public List<Persona> listar() throws Exception {
		List<Persona> lista;
		ResultSet rs;
		try {
			this.conectar();
			PreparedStatement st = this.getCn().prepareCall("SELECT codigo, nombre, apePaterno, apeMaterno, numDocumento, "
					+ "DATE_FORMAT(fecNacimiento,'%d-%m-%Y') as fecNacimiento, numCelular, correo, sexo FROM PERSONA");
			rs = st.executeQuery();
			lista = new ArrayList();
			while (rs.next()) {
				Persona persona = new Persona();
				persona.setCodigo(rs.getInt("codigo"));
				persona.setNombre(rs.getString("nombre"));
				persona.setApePaterno(rs.getString("apePaterno"));
				persona.setApeMaterno(rs.getString("apeMaterno"));
				persona.setNumDocumento(rs.getString("numDocumento"));
				persona.setFecNacimientoToString((rs.getString("fecNacimiento")));
				persona.setNumCelular(rs.getString("numCelular"));
				persona.setCorreo(rs.getString("correo"));
				persona.setSexo(rs.getString("sexo").equals("M") ? "MASCULINO":"FEMENINO");
				lista.add(persona);
			}
		} catch (Exception error) {
			throw error;
		} finally {
			this.cerrar();
		}
		return lista;
	}
	
	public Persona obtenerPersona(Persona persona) throws Exception{
		Persona pers = null;
		ResultSet rs;
		try {
			this.conectar();
			PreparedStatement st = this.getCn().prepareStatement("SELECT codigo, nombre, apePaterno, apeMaterno, numDocumento, "
					+ "DATE_FORMAT(fecNacimiento,'%d-%m-%Y') as fecNacimiento, numCelular, correo, sexo  FROM PERSONA WHERE CODIGO = ?");
			st.setInt(1, persona.getCodigo());
			rs = st.executeQuery();
			while (rs.next()) {
				pers = new Persona();
				pers.setCodigo(rs.getInt("codigo"));
				pers.setNombre(rs.getString("nombre"));
				pers.setApePaterno(rs.getString("apePaterno"));
				pers.setApeMaterno(rs.getString("apeMaterno"));
				pers.setNumDocumento(rs.getString("numDocumento"));
//				pers.setFecNacimientoToString( rs.getString("fecNacimiento"));
				pers.setFecNacimiento( Formato.darFormatoStringToDate(rs.getString("fecNacimiento")));
				pers.setNumCelular(rs.getString("numCelular"));
				pers.setCorreo(rs.getString("correo"));
				pers.setSexo(rs.getString("sexo"));
			}
		} catch (Exception e) { 
			throw e;
		} finally{
			this.cerrar();
		}
		return pers;
	}
	
	
	public void modificar(Persona persona) throws Exception{
		try {
			this.conectar();
			
			PreparedStatement st = this.getCn().prepareStatement("update persona "
					+ "set nombre = ?, "
					+ "apePaterno = ?, "
					+ "apeMaterno = ?, "
					+ "numDocumento = ?, "
					+ "fecNacimiento = ?, "
					+ "numCelular = ?, "
					+ "correo = ?, "
					+ "sexo = ? "
					+ "where codigo = ?");
			st.setString(1, persona.getNombre());
			st.setString(2, persona.getApePaterno());
			st.setString(3, persona.getApeMaterno());
			st.setString(4, persona.getNumDocumento());
			st.setString(5, Formato.darFormatoFechaToString(persona.getFecNacimiento()));
			st.setString(6, persona.getNumCelular());
			st.setString(7, persona.getCorreo());
			st.setString(8, persona.getSexo());
			st.setInt(9, persona.getCodigo());
			st.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally{
			this.cerrar();
		}
	}
	
	
	public void eliminar(Persona persona) throws Exception{
		try {
			this.conectar();
			PreparedStatement st = this.getCn().prepareStatement("delete from persona where codigo = ?");
			st.setInt(1, persona.getCodigo());
			st.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally{
			this.cerrar();
		}
	}
	
	
	
	
	

}
