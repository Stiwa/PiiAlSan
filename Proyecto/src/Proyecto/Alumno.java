package Proyecto;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;



public class Alumno extends Persona {  //Falta añadir interfaz comparable
	
	private GregorianCalendar FechaIngreso = new GregorianCalendar();
	
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
	public static void InsertaAlumno(String[] linea) throws IOException{
		
		String aux[] = linea[5].split("/");
		int dia = Integer.parseInt(aux[0]);
		int mes = Integer.parseInt(aux[1]);
		int anho = Integer.parseInt(aux[2]);
		
		GregorianCalendar fecha = new GregorianCalendar(dia, mes, anho);
		
		//Aqui hay que comprobar la segunda de las fechas (ingreso) (preguntar si obligatoria)
		
		if(Avisos.ComprobarFecha(fecha) == false){
			Avisos.avisosFichero("Fecha incorrecta");
		}
		
		if(Proyecto.mapAlumnos.get(linea[2]) != null){
			Avisos.avisosFichero("Alumno ya existente");
		}
		
		Proyecto.mapAlumnos.put(linea[2], new Alumno(linea[3], linea[4], linea[2], fecha ,linea[1]) );
		
		
	}
}
