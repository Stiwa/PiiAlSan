package Proyecto;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Clase asignatura que anhade las asignaturas del fichero asignaturas.txt al mapa,asigna un coordinador,anhade grupos y evalua asignaturas
 *
 *
 */
public class Asignatura {

	private String Coordinador = new String();
	private int IdAsignatura;
	private String NombreAsignatura = new String();
	private String Siglas = new String();
	private int Curso;
	
	private LinkedList<Integer> Prerrequisitos = new LinkedList<Integer>();
	private ArrayList<Grupos> Grupos = new ArrayList<Grupos>();	
	
	/**
	 * Constructor vacio de asignatura
	 */
	public Asignatura(){
		
	}
	//Constructor necesario cuando cargamos del fichero de personas a mapa
	/**
	 * Constructor de asignatura
	 * @param idAsignatura
	 */
	public Asignatura (int idAsignatura){
		this.IdAsignatura = idAsignatura;
	}	
	/**
	 * Constructor de asignatura que anhade grupos
	 * @param idAsignatura
	 * @param idGrupo
	 * @param grupo
	 */
	public Asignatura(int idAsignatura,int idGrupo, char grupo){
		this.IdAsignatura=idAsignatura;	
		Grupos.add(new Grupos(grupo, idGrupo));	
	}	
	/**
	 * Constructor de asignatura
	 * @param IdAsignatura
	 * @param Siglas
	 * @param Prerrequisitos
	 */
	public Asignatura(int IdAsignatura,String Siglas,String Prerrequisitos){
		this.IdAsignatura= IdAsignatura;
		this.Siglas=Siglas;	
	}
	//Necesario para cargar las asignaturas a los mapas
	/**
	 * Constructor de asignatura necesario para cargar las asignaturas en los mapas y anhade los prerrequisitos y grupos de teoria y practica
	 * @param IdAsignatura
	 * @param NombreAsignatura
	 * @param Siglas
	 * @param Curso
	 * @param Coordinador
	 * @param Prerrequisitos
	 * @param gruposTeoria
	 * @param gruposPractica
	 */
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
	/**
	 * Metodo que anhade preerrequisitos
	 * @param IdAsignatura
	 * @param Siglas
	 * @param Coordinador
	 * @param Prerrequisitos
	 */
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
	/**
	 * Metodo que recorre el fichero de ejecucion para asignar un coordinador que mete en el mapa de asignaturas
	 * @param arrayDatos
	 * @throws IOException
	 */
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
	/**
	 * Devuelve la variable int Curso
	 * @return
	 */
	public int getCurso(){
		return Curso;
	}
	/**
	 * Devuelve la variable int IdAsignatura
	 * @return
	 */
	public int getIdAsignatura(){
		return IdAsignatura;
	}
	/**
	 * Devuelve la variable String Siglas
	 * @return
	 */
	public String getSiglas(){
		return Siglas;
	}	
	/**
	 * Devuelve la variable String NombreAsignatura
	 * @return
	 */
	public String getNombreAsignatura(){
		return NombreAsignatura;
	}
	/**
	 * Devuelve la variable String Coordinador
	 * @return
	 */
	public String getCoordinador(){
		return Coordinador;
	}
	/**
	 * Setter de la variable String Coordinador
	 * @param Coordinador
	 */
	public void setCoordinador(String Coordinador){
		this.Coordinador = Coordinador;
	}
	/**
	 * Devuelve el ArrayList Grupos
	 * @return
	 */
	public ArrayList<Grupos> getGrupos() {
		return Grupos;
	}
	/**
	 * Setter del ArrayList Grupos
	 * @param grupos
	 */
	public void setGrupos(ArrayList<Grupos> grupos) {
		Grupos = grupos;
	}
	/**
	 * Metodo que recorre la lista Grupos y compara las variables id y tipo de grupo con las que recibe el metodo y retorna el objeto grupo que
	 * coincida con las variables
	 * @param idGrupo
	 * @param tipoGrupo
	 * @return
	 */
	public Grupos getGrupo(int idGrupo,char tipoGrupo){
		for(int i=0;i<Grupos.size();i++){
			if(Grupos.get(i).getIdGrupo()==idGrupo && Grupos.get(i).getTipoGrupo()==tipoGrupo){
				return Grupos.get(i);
			}
		}
		return null;
	}
	/**
	 * Devuelve la LinkedList Prerrequisitos
	 * @return
	 */
	public LinkedList<Integer> getPrerrequisitos(){
		return Prerrequisitos;
	}
	/**
	 * Metodo boolean que comprueba si coincide una asignatura en un grupo
	 * @param idGrupo
	 * @param tipoGrupo
	 * @param idSiglas
	 * @return
	 */
	public boolean comprobarGrupo(int idGrupo, char tipoGrupo, int idSiglas){
		if(Proyecto.mapAsignaturas.get(idSiglas)==null)
			return false;
		if(Proyecto.mapAsignaturas.get(idSiglas).comprobarTipoGrupo(tipoGrupo,idGrupo))
			return true;
		return false;
	}
	/**
	 * Metodo boolean que recorre la lista grupos y compara las variables id y tipo de grupo para saber si coinciden con los parametros
	 * que recibe el metodo, si coinciden retorna true
	 * @param tipoGrupo
	 * @param idGrupo
	 * @return
	 */
	public boolean comprobarTipoGrupo(char tipoGrupo,int idGrupo) {
		boolean retorno = false;
		for(int i=0; i<Grupos.size(); i++){
			if(Grupos.get(i).getTipoGrupo()==tipoGrupo&& Grupos.get(i).getIdGrupo()==idGrupo){
				retorno = true;
				break;
			}
		}
		return retorno;
	}
	/**
	 * Metodo que recorre el fichero asignaturas y las mete en el mapa asignaturas
	 * @param nombreArchivo
	 * @throws IOException
	 */
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
	/**
	 * Retorna una variable String para escribir en el fichero
	 */
	public String toString(){
			
			return IdAsignatura+"\n"+NombreAsignatura +"\n" +Siglas +"\n" +Curso +"\n" +Coordinador
					+"\n" +PrerrequisitosToString() +"\n" +gruposToString() +"\n";
	}	
	/**
	 * Metodo que crea un String prerrquisitos con todos los valores de la lista Prerrequisitos separados por un ;
	 * @return
	 */
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
	/**
	 * Metodo que crea dos Strings, uno para grupos de teoria y otro para grupos de practica, con los valores de la lista Grupos
	 * @return
	 */
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
	/**
	 * Metodo que anhade un nuevo grupo a la lista Grupos
	 * @param idGrupo
	 * @param tipoGrupo
	 */
	public void anhadeGrupo(int idGrupo, char tipoGrupo) {
		Grupos.add(new Grupos(tipoGrupo,idGrupo));	
		return;
	}
	/**
	 * Metodo que evalua la asignatura recibe por el fichero de ejecucion
	 * @param arrayDatos
	 * @throws IOException
	 */
	public static void evaluaAsignatura(String[] arrayDatos) throws IOException{
		if(arrayDatos.length != 4){
			Avisos.avisosFichero("Numero de argumentos incorrecto");
			return;
		}
		String siglas = arrayDatos[1].trim();
		String cursoAcademico = arrayDatos[2].trim();
		String ficheroNotas = arrayDatos[3].trim();
		int idSiglas = Util.PasarSiglasAId(siglas);
		if(Proyecto.mapAsignaturas.get(idSiglas) == null){
			Avisos.avisosFichero("Asignatura inexistente");
			return;
		}
		if(!Avisos.compruebaExistenciaFichero(ficheroNotas)){
			Avisos.avisosFichero("Fichero de notas inexistente");
			return;
		}
		if(Avisos.comprobarAsignaturaYaEvaluada(idSiglas, ficheroNotas, cursoAcademico)){
			Avisos.avisosFichero("Asignatura ya evaluada en ese curso academico");
			return;
		}
		//Hay que comprobar el fichero de notas
		Avisos.comprobarFicheroNotas(idSiglas, ficheroNotas, cursoAcademico);
		//ls funcion esta dentro del metodo comprobarFicheroNotas
	
		Avisos.avisosFichero("OK");
		
		return;
	}
	
	
}
