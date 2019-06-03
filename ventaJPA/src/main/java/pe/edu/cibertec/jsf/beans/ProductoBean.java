package pe.edu.cibertec.jsf.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pe.edu.cibertec.jsf.dao.ProductoDAO;
import pe.edu.cibertec.jsf.model.Producto;

@ManagedBean
@ViewScoped
public class ProductoBean {
	
	private Producto producto = new Producto();
	private List<Producto> lstProducto;
	private String accion;

	
	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
		this.limpiar();
	}

	public List<Producto> getLstProducto() {
		return lstProducto;
	}

	public void setLstProducto(List<Producto> lstProducto) {
		this.lstProducto = lstProducto;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public void operar() throws Exception {
		switch (accion) {
		case "REGISTRAR":
			this.registrar();
			break;

		case "MODIFICAR":
			this.modificar();
			break;
		}
		limpiar();
	}
	
	private void registrar() throws Exception{
		ProductoDAO dao;
		try {
			dao = new ProductoDAO();
			dao.registrar(producto);
			this.listar();
		} catch (Exception error) {
			throw error;
		}
	}	
	
	private void modificar() throws Exception{
		ProductoDAO dao;
		try {
			dao = new ProductoDAO();
			dao.modificar(producto);
			this.listar();
		} catch (Exception error) {
			throw error;
		}
	}
	
	private void limpiar() {
		this.producto.setCodigo(0);
		this.producto.setNombre("");
		this.producto.setPrecio(0);
		this.producto.setCantidad(0);
		this.producto.setEmpresa("");
	}
	
	public void listar() throws Exception{
		ProductoDAO dao;
		try {
			if( isPostBack() == false) {
				dao = new ProductoDAO();
				lstProducto = dao.listar();
			}
		} catch (Exception error) {
			throw error;
		}
	}
	
	public void obtenerProducto(Producto per) throws Exception{
		ProductoDAO dao;
		Producto temp;
		try {
			dao = new ProductoDAO();
			temp = dao.obtenerProducto(per);
			if(temp != null) {
				producto = temp;
				this.accion = "MODIFICAR";
			}
		} catch (Exception error) {
			throw error;
		}
	}
	
	
	public void eliminar(Producto per) throws Exception{
		ProductoDAO dao;
		try {
			dao = new ProductoDAO();
			dao.eliminar(per);
			this.listar();
		} catch (Exception error) {
			throw error;
		}
	}
	
	private boolean isPostBack() {
		boolean rpta;
		rpta = FacesContext.getCurrentInstance().isPostback();
		return rpta;
	}
	
	

}
