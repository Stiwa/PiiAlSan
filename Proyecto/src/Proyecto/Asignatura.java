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
	
	//Recibe un String de Asignaturas superadas. Comprueba que son correctas y las añade al mapa
	public void CompruebaAsigSup(String AsigSup){
		if(AsigSup.trim().length()!=0){
			//Primero divide el String en grupos (idAsig + año + nota)
			String Asignaturas[] = AsigSup.trim().split(";");
			
			//Hace cada caso del array de Strings por separado dividiendo id, año y nota
			for(int i= 0; i<Asignaturas.length; i++){
				String[] aux2 = Asignaturas[i].trim().split(" ");
				//Nota n = new Nota(Float.parseFloat(aux2[2]), aux2[1].trim());
				//this.asignaturasSuperadas.put(Integer.parseInt(aux2[0].trim()), n);
			}
		}
	}
	
	
}
