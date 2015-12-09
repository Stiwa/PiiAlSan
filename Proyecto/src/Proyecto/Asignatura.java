package Proyecto;

import java.util.LinkedList;

public class Asignatura {

	private String Coordinador = new String();
	private int IdAsignatura;
	private String NombreAsignatura = new String();
	private String Siglas = new String();
	private int Curso;
	private float Nota;
	private LinkedList Prerrequisitos = new LinkedList();
	
	
	
	
	public Asignatura(){
		
	}
	
	public Asignatura(int IdAsignatura,String Siglas,LinkedList<String> Prerrequisitos){
		
	}
	public void AsignaturaCoord(int IdAsignatura,String Siglas,String Coordinador,LinkedList<String> Prerrequisitos){
		
		
	}
	//Aqui hay que poner el super?¿?¿?
	
	public int getIdAsignatura(){
		return IdAsignatura;
	}
	public String getSiglas(){
		return Siglas;
	}
	
}
