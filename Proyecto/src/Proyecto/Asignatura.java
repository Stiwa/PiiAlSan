package Proyecto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

public class Asignatura {

	private static String Coordinador = new String();
	private int IdAsignatura;
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
	//Necesario para cargar las asignaturas a los mapas
	public Asignatura(int IdAsignatura, String NombreAsignatura, String Siglas, int Curso, String Coordinador,
			String Prerrequisitos, String gruposTeoria, String gruposPractica){
		this.IdAsignatura= IdAsignatura;
		this.NombreAsignatura = NombreAsignatura;
		this.Siglas=Siglas;	
		this.Curso = Curso;
		this.Coordinador = Coordinador;
		
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
	
	/*
	public static void AsignaCoordinador(String [] arrayDatos) throws IOException{

		if(Proyecto.mapProfesores.get(arrayDatos[1])==null){
			Avisos.avisosFichero("Profesor inexistente");
			return;
		}
		
		/*if(Avisos.ComprobarSiglasExistentes(arrayDatos[2].trim()) == false){
			Avisos.avisosFichero("Asignatura Inexistente");
			return;
		}*/
		
		//no funciona la funcion estitular
		/*if(Avisos.EsTitular(arrayDatos[1]) == false){
			Avisos.avisosFichero("Profesor no titular");
			return;
		}*/
		
		//no se como comprobar si funciona bien
		/*if(Avisos.numeroAsignaturasCoordinadas(arrayDatos[1])!=2){
			Avisos.avisosFichero("Profesor ya es coordinador de 2 materias");
			return;
		}
		*/
		
		//Encontrar el id de la asignatura correspondiente a las siglas
		/*Set<Integer> clave = Proyecto.mapAsignaturas.keySet();
		Asignatura asignatura=null;
		for(int key:clave){
			if(Proyecto.mapAsignaturas.get(key).getSiglas().equals(arrayDatos[2])){
				asignatura = Proyecto.mapAsignaturas.get(key);
				break;
			}
		}
		Proyecto.mapAsignaturas.get(asignatura).setCoordinador(arrayDatos[1]);
		
	}
	*/

	public int getIdAsignatura(){
		return IdAsignatura;
	}
	public String getSiglas(){
		return Siglas;
	}
	
	public String getNombreAsignatura(){
		return NombreAsignatura;
	}
	public static String getCoordinador(){
		return Coordinador;
	}
	public void setCoordinador(String Coordinador){
		this.Coordinador = Coordinador;
	}
	
	public static void cargaAsignaturasAMapa(String nombreArchivo) throws IOException{

		try{
			Scanner input = new Scanner(new FileInputStream(nombreArchivo));
			while(input.hasNextLine()){
				String id = input.nextLine().trim();
				String nombre = input.nextLine().trim();
				String siglas = input.nextLine().trim();
				String curso = input.nextLine().trim();
				String coordinador = input.nextLine().trim();
				String prerrequisitos = input.nextLine().trim();
				String gruposA = input.nextLine().trim();
				String gruposB = input.nextLine().trim();
						
				
				Asignatura a = new Asignatura(Integer.parseInt(id.trim()),nombre, siglas, Integer.parseInt(curso.trim()),
						coordinador.trim(), prerrequisitos.trim(), gruposA.trim(), gruposB.trim());
					
				Proyecto.mapAsignaturas.put(Integer.parseInt(id.trim()), a);
				
				//Saltar el asterisco que divide
				if(input.hasNextLine())
					input.nextLine();
				}
			input.close();
		}catch (FileNotFoundException e){
			  Avisos.avisosFichero("Error fichero: " +nombreArchivo);
			  System.exit(1);
		}
	}
	
	//Recibe un String de Asignaturas superadas. Comprueba que son correctas y las a�ade al mapa
	public void CompruebaAsigSup(String AsigSup){
		if(AsigSup.trim().length()!=0){
			//Primero divide el String en grupos (idAsig + a�o + nota)
			String Asignaturas[] = AsigSup.trim().split(";");
			
			//Hace cada caso del array de Strings por separado dividiendo id, a�o y nota
			for(int i= 0; i<Asignaturas.length; i++){
				String[] aux2 = Asignaturas[i].trim().split(" ");
				//Nota n = new Nota(Float.parseFloat(aux2[2]), aux2[1].trim());
				//this.asignaturasSuperadas.put(Integer.parseInt(aux2[0].trim()), n);
			}
		}
	}
	
	public String aString(){
			
			return IdAsignatura+"\n"+NombreAsignatura +"\n" +Siglas +"\n" +Curso +"\n" +Coordinador
					+"\n"+Prerrequisitos +"\n";
	}
	
	
}
