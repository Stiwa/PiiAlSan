package Proyecto;

import java.util.GregorianCalendar;

public class Persona {
	private String Nombre = new String();
	private String Apellidos = new String();
	private String DNI = new String();
	private GregorianCalendar FechaNacimiento = new GregorianCalendar();
	private String Perfil = new String();
	
	protected Persona(){
	}
	
	protected Persona(String Nombre, String Apellidos, String DNI, GregorianCalendar FechaNacimiento, String Perfil){
		
		this.Nombre = Nombre;
		this.Apellidos = Apellidos;
		this.DNI = DNI;
		this.FechaNacimiento = FechaNacimiento;
		this.Perfil = Perfil;
	}
	
	public String getDNI(){
		return DNI;
	}
	public String getNombre(){
		return Nombre;
	}
	public String getApellidos(){
		return Apellidos;
	}
	public static void InsertaPersona(String datos){
		System.out.println("funciona");
	}

}
