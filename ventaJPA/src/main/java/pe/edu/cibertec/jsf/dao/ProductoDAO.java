package pe.edu.cibertec.jsf.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pe.edu.cibertec.jsf.model.Producto;

public class ProductoDAO extends ConexionBD{	
	
	public void registrar(Producto producto) throws Exception{
		try {
//			this.conectar();
//			PreparedStatement st = this.getCn().prepareStatement(
//					"Insert into producto( nombre, precio, cantidad, empresa) "
//					+ " values(?, ?, ?, ?)");
//			st.setString(1, producto.getNombre());
//			st.setDouble(2, producto.getPrecio());
//			st.setLong(3, producto.getCantidad());
//			st.setString(4, producto.getEmpresa());
//
//			st.executeUpdate();
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaVenta");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();

			em.persist(producto);
			em.getTransaction().commit();
			
			
		} catch (Exception error) {
			throw error;
		} finally{
			this.cerrar();
		}
	}
	
	
	public List<Producto> listar() throws Exception {
		List<Producto> lista;
		ResultSet rs;
		try {
			this.conectar();
			PreparedStatement st = this.getCn().prepareCall("SELECT codigo, nombre, precio, cantidad, empresa FROM Producto");
			rs = st.executeQuery();
			lista = new ArrayList();
			while (rs.next()) {
				Producto producto = new Producto();
				producto.setCodigo(rs.getInt("codigo"));
				producto.setNombre(rs.getString("nombre"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setCantidad(rs.getInt("cantidad"));
				producto.setEmpresa(rs.getString("empresa"));
				lista.add(producto);
			}
		} catch (Exception error) {
			throw error;
		} finally {
			this.cerrar();
		}
		return lista;
	}
	
	public Producto obtenerProducto(Producto producto) throws Exception{
		Producto prod = null;
		ResultSet rs;
		try {
			this.conectar();
			PreparedStatement st = this.getCn().prepareStatement("SELECT codigo, nombre, precio, cantidad, empresa "
					+ "FROM Producto WHERE CODIGO = ?");
			st.setInt(1, producto.getCodigo());
			rs = st.executeQuery();
			while (rs.next()) {
				prod = new Producto();
				prod.setCodigo(rs.getInt("codigo"));
				prod.setNombre(rs.getString("nombre"));
				prod.setPrecio(rs.getDouble("precio"));
				prod.setCantidad(rs.getInt("cantidad"));
				prod.setEmpresa(rs.getString("empresa"));
			}
		} catch (Exception e) { 
			throw e;
		} finally{
			this.cerrar();
		}
		return prod;
	}
	
	
	public void modificar(Producto producto) throws Exception{
		try {
//			this.conectar();
//			
//			PreparedStatement st = this.getCn().prepareStatement("update producto "
//					+ "set nombre = ?, "
//					+ "precio = ?, "
//					+ "cantidad = ?, "
//					+ "empresa = ? "
//					+ "where codigo = ?");
//			st.setString(1, producto.getNombre());
//			st.setDouble(2, producto.getPrecio());
//			st.setLong(3, producto.getCantidad());
//			st.setString(4, producto.getEmpresa());
//			st.setInt(5, producto.getCodigo());
//			st.executeUpdate();
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaVenta");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();

			em.merge(producto);
			em.getTransaction().commit();
			
			
			
		} catch (Exception e) {
			throw e;
		} finally{
			this.cerrar();
		}
	}
	
	
	public void eliminar(Producto producto) throws Exception{
		try {
//			this.conectar();
//			PreparedStatement st = this.getCn().prepareStatement("delete from producto where codigo = ?");
//			st.setInt(1, producto.getCodigo());
//			st.executeUpdate();
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaVenta");
			EntityManager em = emf.createEntityManager();
			
		Producto prod = obtenerPorId(producto.getCodigo());
		if (prod != null) {
			em.remove(prod);
		}
			
		} catch (Exception e) {
			throw e;
		} finally{
			this.cerrar();
		}
	}
	
	public Producto obtenerPorId(Integer id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaVenta");
		EntityManager em = emf.createEntityManager();
		return em.find(Producto.class, id);
	}
	
	
	

}
