package Proyecto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Set;


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
	//el super al derivar de persona tiene que llevar dentro de el los parametros que recibe de persona
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
	public void ObtenerCalendarioClases(String DNI,String Salida){
	//	File f = new File();
	// en que archivo debemos guardar el calendario de las clases del profesor?
	//	BufferedWriter salida = new BufferedWriter(new FileWriter(f));
	//Aqui hay que crear un for each con un mapa que recorra las docencias de cada profesor
		return;
	}
	
	public static String getCategoria(){
		return Categoria;
		
	}

	/*
	public void AsignaCargaDocente(String[] arrayDatos) throws IOException{
		String dni = arrayDatos[0].trim();
		if(Proyecto.mapProfesores.get(arrayDatos[1])==null){
			Avisos.avisosFichero("Profesor inexistente");
			return;
		}
		String siglas = arrayDatos[1].trim();
		Util.PasarSiglasAId(siglas);
		if(Proyecto.mapAsignaturas.get(Util.PasarSiglasAId(arrayDatos[2].trim()))==null){
			Avisos.avisosFichero("Asignatura Inexistente");
			return;
		} 
		String tipoGrupo = arrayDatos[2].trim();
		if(!tipoGrupo.equalsIgnoreCase("A") && !tipoGrupo.equalsIgnoreCase("B")){
			Avisos.avisosFichero("Tipo de grupo incorrecto");
		}
		int idGrupo = Integer.parseInt(arrayDatos[3].trim());
		
		Set<String> keys = Proyecto.mapProfesores.keySet();
		
		
		Proyecto.mapProfesores.get(dni).getSiglas().equalsIgnoreCase(siglas
				
		
		
		return;
	}
	*/
	
	public String aString(){
		
		return (super.toString()+"\n"+Categoria +"\n" +Departamento +"\n" +HorasAsignables +"\n");
	}

}
	//xx

