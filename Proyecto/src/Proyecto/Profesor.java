package Proyecto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;


public class Profesor extends Persona{
	
	private String Departamento = new String();
	private static String Categoria = new String();
	private int HorasAsignables;
	private LinkedHashMap<Integer,Asignatura> DocenciaImpartida= new LinkedHashMap<Integer,Asignatura>();
	
	
	public Profesor(){
	}
	public Profesor(String Nombre, String Apellidos, String DNI, 
		GregorianCalendar FechaNacimiento, String Perfil, String Categoria,String Departamento,int HorasAsignables ){
	
	super(Nombre,Apellidos,DNI,FechaNacimiento,Perfil);
	
	this.Categoria=Categoria;
	this.Departamento=Departamento;
	this.HorasAsignables=HorasAsignables;
	
	
	}
	
// ESTE CONSTRUCTOR DE PROFESOR ES EL DEL FICHERO DE EJECUCION PARA METERLO AL MAPA
	public Profesor(String DNI,String Nombre,String Apellidos,GregorianCalendar FechaNacimiento,String Categoria,
			String Departamento, int HorasAsignables){
		super(Nombre,Apellidos,DNI,FechaNacimiento);
	
		this.Categoria=Categoria;
		this.Departamento=Departamento;
		this.HorasAsignables=HorasAsignables;
	}
	//Necesario para cargar del fichero personas al mapa mapProfesores
	public Profesor(String DNI,String Nombre,String Apellidos,GregorianCalendar FechaNacimiento,String Categoria,
			String Departamento, int HorasAsignables, String DocenciaImpartida){

		super(Nombre,Apellidos,DNI,FechaNacimiento);	
		this.Categoria=Categoria;
		this.Departamento=Departamento;
		this.HorasAsignables=HorasAsignables;
		//this.DocenciaImpartida = DocenciaImpartida;
	}
	
	public static String getCategoria(){
		return Categoria;
		
	}
	
	@SuppressWarnings("unused")
	public void AsignarCargaDocente(char grupo,int id,int idgrupo){
		Integer a = new Integer(id);
		if(a==null){
			return;
		}
		//a�adimos a docenciaImpartida la carga docente.
		
		
	}
	
	public void ObtenerClasesProfesor(String Fichero){
		//	File f = new File();
		//	BufferedWriter salida = new BufferedWriter(new FileWriter(f));
		//Aqui hay que crear un for each con un mapa que recorra las docencias de cada profesor
			return;
		}
	
	public String aString(){
		
		return (super.toString()+"\n"+Categoria +"\n" +Departamento +"\n" +HorasAsignables +"\n");
	}

}
	//xx

