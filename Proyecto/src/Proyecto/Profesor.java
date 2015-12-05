package Proyecto;

import java.util.GregorianCalendar;
import java.util.LinkedList;

public class Profesor extends Persona{
	
	private String Departamento = new String();
	private String Categoria = new String();
	private int HorasAsignables;
	private LinkedList DocenciaImpartida = new LinkedList();
	
	
	public Profesor(){
	}
	public Profesor(String Nombre, String Apellidos, String DNI, 
		GregorianCalendar FechaNacimiento, String Perfil, String Categoria,String Departamento,int HorasAsignables ){
	
	super(Nombre,Apellidos,DNI,FechaNacimiento,Perfil);
	//el super al derivar de persona tiene que llevar dentro de el los parametros que recibe de persona
	this.Categoria=Categoria;
	this.Departamento=Departamento;
	this.HorasAsignables=HorasAsignables;
	
	
	}
}
