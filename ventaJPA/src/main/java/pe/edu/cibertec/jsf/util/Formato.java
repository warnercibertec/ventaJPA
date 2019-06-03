package pe.edu.cibertec.jsf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Formato {
	
	
	public static String darFormatoFechaToString(Date fecha) throws ParseException {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
		return formatoFecha.format(fecha);
	}
	
	
	public static Date darFormatoFechaAPP(String fecha) throws ParseException {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-mm-yyyy");
		return formatoFecha.parse(fecha);
	}
	
	public static Date darFormatoStringToDate(String fecha) throws ParseException {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-mm-yyyy");
		return formatoFecha.parse(fecha);
	}
	

}
