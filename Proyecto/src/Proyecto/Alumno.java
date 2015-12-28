package Proyecto;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;


public class Alumno extends Persona {  //Falta a�adir interfaz comparable
	
	private GregorianCalendar FechaIngreso = new GregorianCalendar();
	private String AsignaturasSuperadas;
	
	static LinkedHashMap<Integer,Asignatura> DocenciaRecibida = new LinkedHashMap<Integer, Asignatura>();
	//static LinkedHashMap<Integer,Nota> AsignaturasSuperadas = new LinkedHashMap<Integer, Asignatura>();
	
	
	public Alumno(){
		
	}
	public Alumno(String Nombre, String Apellidos, String DNI, GregorianCalendar FechaNacimiento, String Perfil){
		
		super(Nombre, Apellidos,DNI,FechaNacimiento,Perfil);
	}
	public Alumno(String Nombre, String Apellidos, String DNI, 
			GregorianCalendar FechaNacimiento, String Perfil, GregorianCalendar FechaIngreso){
		
		super(Nombre, Apellidos,DNI,FechaNacimiento,Perfil);
		//el super al derivar de persona tiene que llevar dentro de el los parametros que recibe de persona
		this.FechaIngreso= FechaIngreso;
		
	}
	//Constructor necesario para cargar la lista de personas en los mapas
	public Alumno(String Nombre, String Apellidos, String DNI, GregorianCalendar FechaNacimiento, 
			GregorianCalendar FechaIngreso, String AsignaturasSuperadas, String DocenciaRecibida){
		
		super(Nombre, Apellidos,DNI, FechaNacimiento);
		this.FechaIngreso = FechaIngreso;
		
		//Asignatura.CompruebaAsigSup(AsignaturasSuperadas);
	}
	
	
	public static void ObtenerAsignatura(Asignatura materia){
		DocenciaRecibida.put(Asignatura.getIdAsignatura(), materia);
		return;
		
	}
	
		
	public static void InsertaAlumno(String[] arrayDatos) throws IOException{
		
		String linea1[] = arrayDatos[0].split("\\s+");
		String auxFecha[] = arrayDatos[4].trim().split("\\s+");	
		
		if(arrayDatos.length!=5 ||linea1.length!=3|| auxFecha.length!=2){
			Avisos.avisosFichero("Numero de parametros incorrecto");
			return;
		}
		
		GregorianCalendar fecha2 = Util.PasarAGregorianCalendar(auxFecha[0].trim());
		//Aqui hay que comprobar la segunda de las fechas (ingreso), pero me da null pointer exception
		/*
		if(Avisos.ComprobarFecha(fecha2) == false){
			Avisos.avisosFichero("Fecha incorrecta");
		}
		 */	
		if(Proyecto.mapAlumnos.get(arrayDatos[2]) != null){
			Avisos.avisosFichero("Alumno ya existente");
		}	
		Proyecto.mapAlumnos.put(arrayDatos[2], new Alumno(arrayDatos[3], arrayDatos[4], arrayDatos[2], fecha2 ,arrayDatos[1]) );
			
	}
	
	public String aString(){
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy"); 
		return super.toString()+"\n"+sdf.format(FechaIngreso.getTime());
	}
}
