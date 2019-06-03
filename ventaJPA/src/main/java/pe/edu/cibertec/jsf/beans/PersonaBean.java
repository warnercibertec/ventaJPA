package pe.edu.cibertec.jsf.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import pe.edu.cibertec.jsf.dao.PersonaDAO;
import pe.edu.cibertec.jsf.model.Persona;

@ManagedBean
@ViewScoped
public class PersonaBean {
	
	private Persona persona = new Persona();
	private List<Persona> lstPersona;
	private String accion;
	
	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
		this.limpiar();
	}

	public List<Persona> getLstPersona() {
		return lstPersona;
	}

	public void setLstPersona(List<Persona> lstPersona) {
		this.lstPersona = lstPersona;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
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
		PersonaDAO dao;
		try {
			dao = new PersonaDAO();
			dao.registrar(persona);
			this.listar();
		} catch (Exception e) {
			throw e;
		}
	}	
	
	private void modificar() throws Exception{
		PersonaDAO dao;
		try {
			dao = new PersonaDAO();
			dao.modificar(persona);
			this.listar();
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void limpiar() {
		this.persona.setCodigo(0);
		this.persona.setNombre("");
		this.persona.setApePaterno("");
		this.persona.setApeMaterno("");
		this.persona.setNumDocumento("");
		this.persona.setFecNacimiento(null);
		this.persona.setNumCelular("");
		this.persona.setCorreo("");
		this.persona.setSexo("");
	}
	
	public void listar() throws Exception{
		PersonaDAO dao;
		try {
			if( isPostBack() == false) {
				dao = new PersonaDAO();
				lstPersona = dao.listar();
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void obtenerPersona(Persona per) throws Exception{
		PersonaDAO dao;
		Persona temp;
		try {
			dao = new PersonaDAO();
			temp = dao.obtenerPersona(per);
			if(temp != null) {
				persona = temp;
				this.accion = "MODIFICAR";
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public void eliminar(Persona per) throws Exception{
		PersonaDAO dao;
		try {
			dao = new PersonaDAO();
			dao.eliminar(per);
			this.listar();
		} catch (Exception e) {
			throw e;
		}
	}
	
	private boolean isPostBack() {
		boolean rpta;
		rpta = FacesContext.getCurrentInstance().isPostback();
		return rpta;
	}
	

}
