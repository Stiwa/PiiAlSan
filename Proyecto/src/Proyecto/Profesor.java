package Proyecto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;


public class Profesor extends Persona{
	
	private String Departamento = new String();
	private String Categoria = new String();
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
	
	
public static void InsertaProfesor(String[] linea) throws IOException{
		
		
		String aux[] = linea[5].split("/");
		int dia = Integer.parseInt(aux[0]);
		int mes = Integer.parseInt(aux[1]);
		int anho = Integer.parseInt(aux[2]);
		
		GregorianCalendar fecha = new GregorianCalendar(dia, mes, anho);
		int HorasAsignables=Integer.parseInt(linea[8]);
		
		
		if(Avisos.ComprobarFecha(fecha) == false){
			Avisos.avisosFichero("Fecha incorrecta");
		}
		
		if(Proyecto.mapProfesores.get(linea[2]) != null){
			Avisos.avisosFichero("Profesor ya existente");
		}
		
		Proyecto.mapProfesores.put(linea[2], new Profesor(linea[2],linea[3],linea[4],fecha,linea[6],linea[7],HorasAsignables) );
}
	@SuppressWarnings("unused")
	public void AsignarCargaDocente(char grupo,int id,int idgrupo){
		Integer a = new Integer(id);
		if(a==null){
			return;
		}
		//añadimos a docenciaImpartida la carga docente.
		
		
	}

}
	//xx

