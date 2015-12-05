package Proyecto;

import java.util.GregorianCalendar;
import java.util.LinkedList;

public class Alumno extends Persona {  //Falta a�adir interfaz comparable
	
	private GregorianCalendar FechaIngreso = new GregorianCalendar();
	private LinkedList DocenciaRecibida = new LinkedList();
	//AsignaturasSuperadas iba a ser una list pero lo vamos a tener que hacer como una clase nueva
	
	
	public Alumno(){
		
	}
	public Alumno(String Nombre, String Apellidos, String DNI, 
			GregorianCalendar FechaNacimiento, String Perfil, GregorianCalendar FechaIngreso){
		
		super(Nombre, Apellidos,DNI,FechaNacimiento,Perfil);
		//el super al derivar de persona tiene que llevar dentro de el los parametros que recibe de persona
		this.FechaIngreso= FechaIngreso;
		
		
		
		
	}
	

}
