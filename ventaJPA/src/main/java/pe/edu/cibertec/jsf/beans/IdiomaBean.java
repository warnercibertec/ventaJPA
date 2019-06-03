package pe.edu.cibertec.jsf.beans;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class IdiomaBean {

	private Locale locale;
	private Map<String, String> mapaIdiomas;

	public IdiomaBean() {
		mapaIdiomas = new LinkedHashMap<>();
		mapaIdiomas.put("Español", "es");
		mapaIdiomas.put("English", "en");
		locale = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestLocale();
	}

//	public void cambioIdioma(ValueChangeEvent e) {
//		String nuevoIdioma = e.getNewValue().toString();
//		locale = new Locale(nuevoIdioma);
//		FacesContext.getCurrentInstance().getViewRoot()
//            .setLocale(locale);
//	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Map<String, String> getMapaIdiomas() {
		return mapaIdiomas;
	}

	public void setMapaIdiomas(Map<String, String> mapaIdiomas) {
		this.mapaIdiomas = mapaIdiomas;
	}

	public String getIdioma() {
	    return locale.getLanguage();
	}

	public void setIdioma(String idioma) {
	    locale = new Locale(idioma);
	    FacesContext.getCurrentInstance()
	    	.getViewRoot().setLocale(locale);
	}
}
