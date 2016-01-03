package Proyecto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

public class Asignatura {

	private String Coordinador = new String();
	private int IdAsignatura;
	private String NombreAsignatura = new String();
	private String Siglas = new String();
	private int Curso;
	
	private LinkedList<Integer> Prerrequisitos = new LinkedList<Integer>();
	private ArrayList<Grupos> Grupos = new ArrayList<Grupos>();	
	
	public Asignatura(){
		
	}
	//Constructor necesario cuando cargamos del fichero de personas a mapa
	public Asignatura (int idAsignatura){
		this.IdAsignatura = idAsignatura;
	}
	
	public Asignatura(int idAsignatura,int idGrupo, char grupo){
		this.IdAsignatura=idAsignatura;	
		Grupos.add(new Grupos(grupo, idGrupo));	
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
		if(Prerrequisitos.length() != 0){
			String[] aux = Prerrequisitos.trim().split(";");
			for (int i=0; i<aux.length; i++){
				this.Prerrequisitos.add(Integer.parseInt(aux[i].trim()));
			}
		}
		if(gruposTeoria.length() != 0){
			String[] aux = gruposTeoria.trim().split(";");
			for (int i=0; i<aux.length; i++){
				String[] aux2 = aux[i].trim().split("\\s+");
				Grupos.add(new Grupos('A', Integer.parseInt(aux2[0]), aux2[1].trim().charAt(0), aux2[2], aux2[3]));
			}
		}
		if(gruposPractica.length() != 0){
			String[] aux = gruposPractica.trim().split(";");
			for (int i=0; i<aux.length; i++){
				String[] aux2 = aux[i].trim().split("\\s+");
				Grupos.add(new Grupos('B', Integer.parseInt(aux2[0]), aux2[1].trim().charAt(0), aux2[2], aux2[3]));
			}
		}
		
	}
	
	public void AsignaturaCoord(int IdAsignatura,String Siglas,String Coordinador,String Prerrequisitos){
		//Esta funcion vamos a usarla?????
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
	
	
	public static void AsignaCoordinador(String [] arrayDatos) throws IOException{

		if(Proyecto.mapProfesores.get(arrayDatos[1])==null){
			Avisos.avisosFichero("Profesor inexistente");
			return;
		}
		
		if(Proyecto.mapAsignaturas.get(Util.PasarSiglasAId(arrayDatos[2].trim()))==null){
			Avisos.avisosFichero("Asignatura Inexistente");
			return;
		} 
		
		if(Avisos.EsTitular(arrayDatos[1].trim()) == false){
			Avisos.avisosFichero("Profesor no titular");
			return;
		}
		
		if(Avisos.numeroAsignaturasCoordinadas(arrayDatos[1])>=2){
			Avisos.avisosFichero("Profesor ya es coordinador de 2 materias");
			return;
		}
		
		
		//Encontrar el id de la asignatura correspondiente a las siglas
		
		Proyecto.mapAsignaturas.get(Util.PasarSiglasAId(arrayDatos[2].trim())).setCoordinador(arrayDatos[1]);
		
		Avisos.avisosFichero("OK");
	}
	

	public int getIdAsignatura(){
		return IdAsignatura;
	}
	public String getSiglas(){
		return Siglas;
	}
	
	public String getNombreAsignatura(){
		return NombreAsignatura;
	}
	public String getCoordinador(){
		return Coordinador;
	}
	public void setCoordinador(String Coordinador){
		this.Coordinador = Coordinador;
	}
	public ArrayList<Grupos> getGrupos() {
		return Grupos;
	}
	public void setGrupos(ArrayList<Grupos> grupos) {
		Grupos = grupos;
	}
	public Grupos getGrupo(int idGrupo,char tipoGrupo){
		for(int i=0;i<Grupos.size();i++){
			if(Grupos.get(i).getIdGrupo()==idGrupo && Grupos.get(i).getTipoGrupo()==tipoGrupo){
				return Grupos.get(i);
			}
		}
		return null;
	}
	public LinkedList<Integer> getPrerrequisitos(){
		return Prerrequisitos;
	}
	
	//Comprobado
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
	
	//Recibe un String de Asignaturas superadas. Comprueba que son correctas y las anhade al mapa
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
	
	public String toString(){
			
			return IdAsignatura+"\n"+NombreAsignatura +"\n" +Siglas +"\n" +Curso +"\n" +Coordinador
					+"\n" +PrerrequisitosToString() +"\n" +gruposToString() +"\n";
	}
	
	public String PrerrequisitosToString(){
		String prerrequisitos ="";
		boolean ponPuntoComa = false;
		for(int i = 0; i<Prerrequisitos.size(); i++){
			if(ponPuntoComa)
				prerrequisitos += ";";
			prerrequisitos += 	Integer.toString(Prerrequisitos.get(i));	
			ponPuntoComa = true;
		}	
		return prerrequisitos;
	}
	
	public String gruposToString(){
		String aux = "";
		String aux2 = "";
		boolean ponPuntoComa = false;
		boolean ponPuntoComa2 = false;		
		for(int i=0; i<Grupos.size(); i++){
			if(Grupos.get(i).getTipoGrupo() == 'A'){
				if(ponPuntoComa)
					aux += "; ";
				aux += Grupos.get(i).ficheroAsignaturas();
				ponPuntoComa = true;
			}
			else{
				if(ponPuntoComa2)
					aux2 += "; ";
				aux2 += Grupos.get(i).ficheroAsignaturas();
				ponPuntoComa2 = true;
			}
		}
		return (aux +"\n" +aux2);
	}
}
