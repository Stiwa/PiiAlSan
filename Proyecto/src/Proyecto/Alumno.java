package Proyecto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Clase alumno que extiende de persona e incluye metodos para asignar grupo a un alumno,matricularlo y obetener su expediente y docencia reibida
 *
 *
 */

public class Alumno extends Persona implements Comparable<Alumno> {  
	
	private Calendar FechaIngreso = Calendar.getInstance();
	private LinkedHashMap<Integer, Asignatura> DocenciaRecibida = new LinkedHashMap<Integer, Asignatura>();
	private LinkedHashMap<Integer, Notas> AsignaturasSuperadas = new LinkedHashMap<Integer, Notas>();
	
	/**
	 * Constructor vacio de alumno
	 */
	public Alumno(){
		
	}
	/**
	 * Constructor de alumno
	 * @param Nombre
	 * @param Apellidos
	 * @param DNI
	 * @param FechaNacimiento
	 * @param Perfil
	 */
	public Alumno(String Nombre, String Apellidos, String DNI, Calendar FechaNacimiento, String Perfil){
		
		super(Nombre, Apellidos,DNI,FechaNacimiento,Perfil);
	}
	/**
	 * Constructor de alumno
	 * @param Nombre
	 * @param Apellidos
	 * @param DNI
	 * @param FechaNacimiento
	 * @param Perfil
	 * @param FechaIngreso
	 */
	public Alumno(String Nombre, String Apellidos, String DNI, 
			Calendar FechaNacimiento, String Perfil, Calendar FechaIngreso){
		
		super(Nombre, Apellidos,DNI,FechaNacimiento,Perfil);
		//el super al derivar de persona tiene que llevar dentro de el los parametros que recibe de persona
		this.FechaIngreso= FechaIngreso;
		
	}
	//Constructor necesario para cargar la lista de personas en los mapas.
	/**
	 * Constructor de alumno que anhade las asignaturas superadas y la docencia recibida en sus listas
	 * @param Nombre
	 * @param Apellidos
	 * @param DNI
	 * @param FechaNacimiento
	 * @param FechaIngreso
	 * @param AsignaturasSuperadas
	 * @param DocenciaRecibida
	 */
	public Alumno(String Nombre, String Apellidos, String DNI, Calendar FechaNacimiento, 
			Calendar FechaIngreso, String AsignaturasSuperadas, String DocenciaRecibida){
		
		super(Nombre, Apellidos,DNI, FechaNacimiento);
		this.FechaIngreso = FechaIngreso;
		
		
		if(AsignaturasSuperadas.length() !=0){
			String grupoDatos[] = AsignaturasSuperadas.split(";");
			for(int i=0;i<grupoDatos.length;i++){
				String PartesDatos[]=grupoDatos[i].trim().split("\\s+");
				this.AsignaturasSuperadas.put(Integer.parseInt(PartesDatos[0].trim()),new Notas(Float.parseFloat(PartesDatos[2])
						,PartesDatos[1]));
				
			}
		}
		
		if(DocenciaRecibida.length()!=0){
			String grupoDatos[] = DocenciaRecibida.split(";");
			
			for(int i=0; i<grupoDatos.length; i++) {
				String arrayDocente[] = grupoDatos[i].trim().split(" ");
				
				//Para el caso de cuando no esta asignado ningun grupo de teoria o de practica
				if(arrayDocente.length==1){
					//Hay que crear un objeto asignatura pero con solo el dato del id, ya que no tiene grupos asignados
					Asignatura a = new Asignatura(Integer.parseInt(arrayDocente[0])); 
					this.DocenciaRecibida.put(Integer.parseInt(arrayDocente[0]), a);
				}
				else {
					if(this.DocenciaRecibida.get(Integer.parseInt(arrayDocente[0]))!=null){
						Grupos g = new Grupos(arrayDocente[1].toCharArray()[0], Integer.parseInt(arrayDocente[2]));
						this.DocenciaRecibida.get(Integer.parseInt(arrayDocente[0])).getGrupos().add(g);
					}
					else {
						Asignatura a = new Asignatura(Integer.parseInt(arrayDocente[0]),Integer.parseInt(arrayDocente[2]), 
								arrayDocente[1].toCharArray()[0]);
						
						this.DocenciaRecibida.put(Integer.parseInt(arrayDocente[0]), a);
					}
				}
			}//Cierra el for
			
		}
		
	}
	/**
	 * Metodo que recorre la lista de docencia recibida y los frpos para crear un String docencia con todos los valores con sus respectivos
	 * grupos
	 * @return
	 */
	public String DocenciaRecibidaToString(){
		String docencia ="";
		boolean ponPuntoComa = false;
		Set<Integer> clave = DocenciaRecibida.keySet();
		for(int key:clave){
			ArrayList<Grupos> Grupos = DocenciaRecibida.get(key).getGrupos();
			if(Grupos.size() == 0){
				if(ponPuntoComa)
					docencia += "; " +key;
				else{ 
					docencia += key;
					ponPuntoComa = true;
				}
					
			}
			for(int i=0; i<Grupos.size(); i++){
				if(ponPuntoComa)
					docencia += "; ";
				docencia += key +" " +Grupos.get(i).getTipoGrupo() +" " +Integer.toString(Grupos.get(i).getIdGrupo());
				ponPuntoComa = true;
			}
			
		}	
		return docencia;
	}
	/**
	 * Metodo que recorre la lista AsignaturasSuperadas y crea un String con todos los valores de la lista
	 * @return
	 */
	public String AsignaturasSuperadasToString(){
		String AsigSup = "";
		boolean ponPuntoComa = false;
		Set<Integer> clave = AsignaturasSuperadas.keySet();
		for(int key:clave){
			if(ponPuntoComa)
				AsigSup += "; ";
			
			AsigSup += Integer.toString(key) +" " +AsignaturasSuperadas.get(key).getAnhoAcademico() 
					+" " +AsignaturasSuperadas.get(key).getNota();
			ponPuntoComa = true;
		}
		return AsigSup;
	}
	/**
	 * Retorna una variable String con los parametros de la clase padre y de la clase alumno
	 */
	public String toString(){

		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy"); 
		return (super.toString()+"\n"+sdf.format(FechaIngreso.getTime())+"\n" 
				+AsignaturasSuperadasToString() +"\n" +DocenciaRecibidaToString() +"\n");
	}
	/**
	 * Metodo que matricula los alumnos que nos dan para esta funcion en el fichero de ejecucion
	 * @param arrayDatos
	 * @throws IOException
	 */
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
	    if(Proyecto.mapAlumnos.get(dni).ComprobarSiMatriculado(idSiglas)){
	    	Avisos.avisosFichero("Ya es alumno de la asignatura indicada");
	    	return;
	    }
		if(!Avisos.comprobarPrerrequisitos(Proyecto.mapAsignaturas.get(idSiglas), Proyecto.mapAlumnos.get(dni))){
			Avisos.avisosFichero("No cumple requisitos");
			return;
		}
		//Anhadir a la docencia recibida
		Proyecto.mapAlumnos.get(dni).getDocenciaRecibida().put(idSiglas, new Asignatura(idSiglas));
		Avisos.avisosFichero("OK");   
	}   
	//Comprobaciones necesarias en alumnos porque necesitan la docencia recibida de cada objeto Alumno
	/**
	 * Metodo boolean que comprueba si un alumno ya esta matriculado en una asignatura
	 * @param idAsignatura
	 * @return
	 */
	public boolean ComprobarSiMatriculado(int idAsignatura){
		boolean retorno=true;
		if(DocenciaRecibida.get(idAsignatura) == null){
			retorno = false;
			return retorno;
		}
		return retorno;
	}
	/**
	 * Metodo boolean que comprueba si un alumno tiene alguna asignatura superada o no
	 * @param idAsignatura
	 * @return
	 */
	public boolean ComprobarSiAprobado(int idAsignatura){
		boolean retorno=true;
		if(AsignaturasSuperadas.get(idAsignatura) == null){
			retorno = false;
			return retorno;
		}
		return retorno;
	}
	/**
	 * Metodo que asigna un grupo a un alumno  que nos dan en el fichero de ejecucion para esta funcion
	 * @param arrayDatos
	 * @throws IOException
	 */
	public static void asignarGrupo(String[] arrayDatos)throws IOException{
		if(arrayDatos.length!=5){
			Avisos.avisosFichero("Numero de argumentos incorrecto");
			return;
		}
		String siglas = arrayDatos[2].trim();
		String dni = arrayDatos[1].trim();
		String tipoGrupo=arrayDatos[3].trim();
		String idGrupo= arrayDatos[4].trim();
		int idSiglas = Util.PasarSiglasAId(siglas);
		
		if(Proyecto.mapAlumnos.get(dni)==null){
			Avisos.avisosFichero("Alumno inexistente");
			return;
		}
		if(Proyecto.mapAsignaturas.get(idSiglas)==null){
			Avisos.avisosFichero("Asignatura inexistente");
			return;
		}
		if(!Proyecto.mapAlumnos.get(dni).ComprobarSiMatriculado(Proyecto.mapAsignaturas.get(idSiglas).getIdAsignatura())){
			Avisos.avisosFichero("Alumno no matriculado");
			return;
		} 
		if(!Avisos.comprobarTipoGrupo(tipoGrupo)){
			Avisos.avisosFichero("Tipo de grupo incorrecto");
			return;
		} 
		if(!Proyecto.mapAsignaturas.get(idSiglas).comprobarGrupo(Integer.parseInt(idGrupo),
				tipoGrupo.toCharArray()[0],idSiglas)){
			Avisos.avisosFichero("Grupo Inexistente");
			return;
		}
		if(Avisos.haySolapeEnAlumno(Proyecto.mapAlumnos.get(dni), Proyecto.mapAsignaturas.get(idSiglas),
				tipoGrupo.toCharArray()[0], Integer.parseInt(idGrupo))){
			Avisos.avisosFichero("Se genera solape");
			return;
		} 
		
		Proyecto.mapAlumnos.get(dni).asignarGrupo(Proyecto.mapAsignaturas.get(idSiglas),
		tipoGrupo.toCharArray()[0], Integer.parseInt(idGrupo));
		
		Avisos.avisosFichero("OK");
	
	}	
	/**
	 * Metodo que asigna un grupo a una docencia recibida de un alumno
	 * @param asignatura
	 * @param tipoGrupo
	 * @param idGrupo
	 */
	public void asignarGrupo(Asignatura asignatura, char tipoGrupo, int idGrupo) {
		DocenciaRecibida.get(asignatura.getIdAsignatura()).anhadeGrupo(idGrupo, tipoGrupo);
		return;
		
	}
	/**
	 * Devuelve el LinkedHashMap DocenciaRecibida
	 * @return
	 */
	public LinkedHashMap<Integer, Asignatura> getDocenciaRecibida(){
		return DocenciaRecibida;
	}
	/**
	 * Devuelve el LinkedHashMap AsignaturasSuperadas
	 * @return
	 */
	public LinkedHashMap<Integer, Notas> getAsignaturasSuperadas(){
		return AsignaturasSuperadas;
	}
	/**
	 * Metodo boolean que comprueba si hay solape en el horario del alumno
	 * @param horaInicio
	 * @param horaFin
	 * @param dia
	 * @return
	 */
	public boolean horarioSolapeAlumno(int horaInicio, int horaFin, char dia) {
		boolean retorno= false;
		Set<Integer> claves = DocenciaRecibida.keySet();
		for(int key:claves){
			ArrayList<Grupos> gruposAlumno = DocenciaRecibida.get(key).getGrupos();
			ArrayList<Grupos> grupos = Proyecto.mapAsignaturas.get(key).getGrupos();
			for(int i=0; i<gruposAlumno.size(); i++){
				for(int j=0; j<grupos.size(); j++){
					if(gruposAlumno.get(i).getTipoGrupo()==grupos.get(j).getTipoGrupo()&&gruposAlumno.get(i).getIdGrupo()==grupos.get(j)
							.getIdGrupo()){
						if(grupos.get(j).getDia()!=dia)
							continue;
						else{
							if(horaInicio-grupos.get(j).getHoraFin()<0 || grupos.get(j).getHoraInicio()==horaInicio){
								retorno = true;
								break;
							}
						}
					}
				}
			}
			if(retorno==true){
				break;		
			}
		}
		return retorno;
	}
	/**
	 * Metodo que obtiene el expediente de un alumno que nos dan en el fichero de ejecucion para esta funcion
	 * @param arrayDatos
	 * @throws IOException
	 */
    public static void ObtenerExpediente(String[] arrayDatos) throws IOException{
		if(arrayDatos.length != 3){
			Avisos.avisosFichero("Numero de parametros incorrecto");
			return;
		}
		String dni = arrayDatos[1].trim();
		String output = arrayDatos[2].trim();		
    	if(Proyecto.mapAlumnos.get(dni) == null){
    		Avisos.avisosFichero("Alumno inexistente");
    		return;
    	}	
    	if(Proyecto.mapAlumnos.get(dni).getAsignaturasSuperadas().size() == 0){
    		Avisos.avisosFichero("Expediente vacio");
    		return;
    	}
    	Proyecto.mapAlumnos.get(dni).cargaExpediente(output);
    	Avisos.avisosFichero("OK");
    	return;
    	
    }
    /**
     * Metodo que escribe en un fichero las asignaturas superadas del alumno ordenadas por curso y por nombre y su nota media
     * @param output
     * @throws IOException
     */
	private void cargaExpediente(String output) throws IOException {
		  File f=new File(output);
		  BufferedWriter bufer = new BufferedWriter(new FileWriter(f));
		  ArrayList<Notas> notas = new ArrayList<Notas>(AsignaturasSuperadas.values());
		  Set<Integer> clave = AsignaturasSuperadas.keySet();
		  for(int key:clave){
				AsignaturasSuperadas.get(key).setCurso(Proyecto.mapAsignaturas.get(key).getCurso());
				AsignaturasSuperadas.get(key).setNombreAsig(Proyecto.mapAsignaturas.get(key).getNombreAsignatura());
		  }
		  Collections.sort(notas);  
		  Collections.sort(notas, new comparaPorNombre());
		  for (int i = 0; i < notas.size(); i++) {
			  bufer.write(notas.get(i).getCurso()+" "+notas.get(i).getNombre()+" "+ notas.get(i).getNota()+" "
						+notas.get(i).getAnhoAcademico()+"\n");
		  }
		  
		  
		  float notaMedia = 0;
		  for(int j=0; j<notas.size(); j++){
			   notaMedia += notas.get(j).getNota();
		  }
		  notaMedia = notaMedia/notas.size();
		  bufer.write(" Nota media: " +notaMedia);
		  	  
		  bufer.close();	
		  return;
	}
	//Necesario implementar metodo abstracto de la interfaz comparable

	/**
	 * Metodo evaluar que comprueba si la nota total entre la teorica y la practica es mayor que 5, si es mayor que 5, la anhade a la lista
	 * de asignaturas superadas y lo borra de la lista docenciaRecibida
	 * @param idSiglas
	 * @param notas
	 */
	public void evaluar(int idSiglas, Notas notas) {
		if(notas.getNota()>=5){
			AsignaturasSuperadas.put(idSiglas, notas);
			DocenciaRecibida.remove(idSiglas);
		}
	return;
	}
	/**
	 * Metodo para calcular nota de las asignaturas superadas
	 * @return
	 */
	public float CalcularNota(){
		Set<Integer> claves = AsignaturasSuperadas.keySet();
		float aux=0;
		int aux1=0;
		
		for(int key:claves){
			aux += AsignaturasSuperadas.get(key).getNota();
			aux1++;
		}
		if(aux1==0){
			return 0;
		}
	return aux/aux1;
	}
	/**
	 * Metodo de la amplicacion del proyecto para ordenar alumnos por notas
	 * @param arrayDatos
	 * @throws IOException
	 */
	public static void OrdenaAlumnosPorNotas(String[] arrayDatos) throws IOException{
		
		if(arrayDatos.length!=2){
			Avisos.avisosFichero("Numero de argumentos incorrecto");
			return;
		}
	List<Alumno> lista = new LinkedList<Alumno>(Proyecto.mapAlumnos.values());
	BufferedWriter buffer=new BufferedWriter(new FileWriter(arrayDatos[1].trim()));
	Collections.sort(lista);
	Collections.sort(lista, new ComparadorNotas());
	for(int i=0;i<lista.size();i++){
		buffer.write(lista.get(i).getApellidos()+" "+lista.get(i).getNombre()+" "+lista.get(i).getDNI()+" "+
				lista.get(i).CalcularNota()+"\n");
	}
	buffer.close();
	Avisos.avisosFichero("OK");
	return;
	}
	/**
	 * CompareTo del metodo OrdenarAlumnosPorNotas
	 */
	public int compareTo(Alumno alumno){
		return getApellidos().compareTo(alumno.getApellidos());
		
		
	}
 }
/**
 * Clase para comparar notas entre dos objetos alumno
 *
 *
 */
class ComparadorNotas implements Comparator<Alumno>{
	public int compare(Alumno al1,Alumno al2){
		if(al1.CalcularNota()<al2.CalcularNota()){
			return -1;
		}
		return 1;
	}
}
	
