package Proyecto;

import java.util.GregorianCalendar;
import java.util.LinkedHashMap;



public class Alumno extends Persona {  //Falta a�adir interfaz comparable
	
	private GregorianCalendar FechaIngreso = new GregorianCalendar();
	static LinkedHashMap<Integer,Asignatura> DocenciaRecibida = new LinkedHashMap<Integer, Asignatura>();
	//AsiganturasSuperadas se puede hacer con un linkedhashmap o en una clase nueva
	
	
	public Alumno(){
		
	}
	public Alumno(String Nombre, String Apellidos, String DNI, 
			GregorianCalendar FechaNacimiento, String Perfil, GregorianCalendar FechaIngreso){
		
		super(Nombre, Apellidos,DNI,FechaNacimiento,Perfil);
		//el super al derivar de persona tiene que llevar dentro de el los parametros que recibe de persona
		this.FechaIngreso= FechaIngreso;
		
	}
	
//xx
}
