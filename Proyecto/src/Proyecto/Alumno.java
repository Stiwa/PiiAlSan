package Proyecto;

import java.util.GregorianCalendar;
import java.util.LinkedList;

public class Alumno extends Persona {  //Falta añadir interfaz comparable
	
	private GregorianCalendar FechaIngreso = new GregorianCalendar();
	private LinkedList DocenciaRecibida = new LinkedList();
	//AsignaturasSuperadas iba a ser una list pero lo vamos a tener que hacer como una clase nueva
	
	
	public Alumno(){
	}
	public Alumno(String Nombre, String Apellidos, String DNI, 
			GregorianCalendar FechaNacimiento, String Perfil, GregorianCalendar FechaIngreso){
		
		super();
		
		
		
	}
	

}
