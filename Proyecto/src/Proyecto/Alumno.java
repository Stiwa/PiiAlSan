package Proyecto;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Set;


public class Alumno extends Persona {  //Falta a�adir interfaz comparable
	
	private GregorianCalendar FechaIngreso = new GregorianCalendar();
	private String AsignaturasSuperadas;
	private String DocenciaRecibida="";
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
	//Constructor necesario para cargar la lista de personas en los mapas.
	public Alumno(String Nombre, String Apellidos, String DNI, GregorianCalendar FechaNacimiento, 
			GregorianCalendar FechaIngreso, String AsignaturasSuperadas, String DocenciaRecibida){
		
		super(Nombre, Apellidos,DNI, FechaNacimiento);
		this.FechaIngreso = FechaIngreso;
		
		//Asignatura.CompruebaAsigSup(AsignaturasSuperadas);
	}
	public String getDocenciaRecibida(){
		return DocenciaRecibida;
	}
	
	public String aString(){

		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy"); 
		return super.toString()+"\n"+sdf.format(FechaIngreso.getTime())+"\n";
	}
	
	
	public static void Matricula(String[] arrayDatos) throws IOException{
		
		if(arrayDatos.length != 3){
			Avisos.avisosFichero("Numero de parametros incorrecto");
			return;
		}
		
		String dni = arrayDatos[1].trim();
		String siglas = arrayDatos[2].trim();

    	if(Proyecto.mapAlumnos.get(dni) == null){
    		Avisos.avisosFichero("Alumno inexistente");
    		return;
    	}
	    int idSiglas = Util.PasarSiglasAId(siglas);
	    if(Proyecto.mapAsignaturas.get(idSiglas)==null){
	    	Avisos.avisosFichero("Asignatura inexistente");
    		return;
	    }
	    System.out.println("DOCENCIA RECIBIDA:  "+Proyecto.mapAlumnos.get(dni).getDocenciaRecibida());
	    if(!Avisos.ComprobarMatricula(Proyecto.mapAlumnos.get(dni).getDocenciaRecibida(),idSiglas)){
 	
    			Avisos.avisosFichero("Ya es alumno de la asignatura indicada");
    			return;
	    }
		}
    			
    		
 	}
	

		
		
	
	
	


