package Proyecto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
	public void ObtenerCalendarioClases(String DNI,String Salida){
	//	File f = new File();
	// en que archivo debemos guardar el calendario de las clases del profesor?
	//	BufferedWriter salida = new BufferedWriter(new FileWriter(f));
	//Aqui hay que crear un for each con un mapa que recorra las docencias de cada profesor
		return;
	}
	@SuppressWarnings("unused")
	public void AsignarCargaDocente(char grupo,int id,int idgrupo){
		Integer a=new Integer(id);
		if(a==null){
			return;
		}
		//añadimos a docenciaImpartida la carga docente.
		
		
	}
}
	//xx

