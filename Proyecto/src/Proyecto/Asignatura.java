package Proyecto;

import java.util.LinkedList;

public class Asignatura {

	private String Coordinador = new String();
	private static int IdAsignatura;
	private String NombreAsignatura = new String();
	private String Siglas = new String();
	private int Curso;
	private float Nota;
	private LinkedList<Integer> Prerrequisitos = new LinkedList<Integer>();
	private LinkedList Grupos = new LinkedList();	
	//x
	
	
	public Asignatura(int idAsignatura,int idGrupo, char grupo){
		this.IdAsignatura=idAsignatura;
				
		
	}
	
	public Asignatura(int IdAsignatura,String Siglas,String Prerrequisitos){
		this.IdAsignatura= IdAsignatura;
		this.Siglas=Siglas;
		
		
	}
	public void AsignaturaCoord(int IdAsignatura,String Siglas,String Coordinador,String Prerrequisitos){
		this.IdAsignatura=IdAsignatura;
		this.Siglas=Siglas;
		this.Coordinador=Coordinador;		
		if(Prerrequisitos.length()>0){
			String[] lista= Prerrequisitos.trim().split(",");
			//en la lista de prerrequisitos debemos separarl
			for(int i=0; i<lista.length; i++){
			this.Prerrequisitos.add(Integer.parseInt(lista[i]));
			}
			
			
		}
				
	}
	
	public void AsignarCoordinador(String DNI,String Siglas){
		this.Siglas=Siglas;
		//Proyecto.mapProfesores.set(DNI,Siglas);
	}
	public static int getIdAsignatura(){
		return IdAsignatura;
	}
	public String getSiglas(){
		return Siglas;
	}
	
	public String getNombreAsignatura(){
		return NombreAsignatura;
	}
	
	
}
