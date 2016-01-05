package Proyecto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Clase Profesor, que hereda de persona. En ella vamos a crear los objetos profesor necesarios.
 * Tambien se realizan diversos metodos relacionados con los profesores.
 */
public class Profesor extends Persona{
	
	private String Departamento = new String();
	private String Categoria = new String();
	private int HorasAsignables;
	private LinkedHashMap<Integer,Asignatura> DocenciaImpartida= new LinkedHashMap<Integer,Asignatura>();
	
	/**
	 * Constructor vacio.
	 */
	public Profesor(){
	}
	/**
	 * Constructor necesario para crear objetos Profesor en un metodo de esta clase.
	 * @param Nombre
	 * @param Apellidos
	 * @param DNI
	 * @param FechaNacimiento
	 * @param Perfil
	 * @param Categoria
	 * @param Departamento
	 * @param HorasAsignables
	 */
	public Profesor(String Nombre, String Apellidos, String DNI, 
		Calendar FechaNacimiento, String Perfil, String Categoria,String Departamento,int HorasAsignables ){
	
	super(Nombre,Apellidos,DNI,FechaNacimiento,Perfil);
	
	this.Categoria=Categoria;
	this.Departamento=Departamento;
	this.HorasAsignables=HorasAsignables;
	
	}
	/**
	 * Constructor que se utiliza para crear los objetos profesor del fichero ejecucion a la hora de cargarlos al mapa.
	 * @param DNI
	 * @param Nombre
	 * @param Apellidos
	 * @param FechaNacimiento
	 * @param Categoria
	 * @param Departamento
	 * @param HorasAsignables
	 */
	public Profesor(String DNI,String Nombre,String Apellidos,Calendar FechaNacimiento,String Categoria,
			String Departamento, int HorasAsignables){
		super(Nombre,Apellidos,DNI,FechaNacimiento);
	
		this.Categoria=Categoria;
		this.Departamento=Departamento;
		this.HorasAsignables=HorasAsignables;
	}
	/**
	 * Constructor de profesor que es necesario para cargar del fichero personas al mapa de profesores.
	 * @param DNI
	 * @param Nombre
	 * @param Apellidos
	 * @param FechaNacimiento
	 * @param Categoria
	 * @param Departamento
	 * @param HorasAsignables
	 * @param DocenciaImpartida
	 */
	public Profesor(String DNI,String Nombre,String Apellidos,Calendar FechaNacimiento,String Categoria,
			String Departamento, int HorasAsignables, String DocenciaImpartida){

		super(Nombre,Apellidos,DNI,FechaNacimiento);	
		this.Categoria=Categoria;
		this.Departamento=Departamento;
		this.HorasAsignables=HorasAsignables;
		
		if(DocenciaImpartida.length()!=0){
			String grupoDatos[] = DocenciaImpartida.split(";");
			
			for(int i=0; i<grupoDatos.length; i++) {
				String arrayDocente[] = grupoDatos[i].trim().split(" ");
				
				//Para el caso de cuando no esta asignado ningun grupo de teoria o de practica
				if(arrayDocente.length==1){
					//Hay que crear un objeto asignatura pero con solo el dato del id, ya que no tiene grupos asignados
					Asignatura a = new Asignatura(Integer.parseInt(arrayDocente[0])); 
					this.DocenciaImpartida.put(Integer.parseInt(arrayDocente[0]), a);
				}
				else {
					if(this.DocenciaImpartida.get(Integer.parseInt(arrayDocente[0]))!=null){
						Grupos g = new Grupos(arrayDocente[1].toCharArray()[0], Integer.parseInt(arrayDocente[2]));
						this.DocenciaImpartida.get(Integer.parseInt(arrayDocente[0])).getGrupos().add(g);
					}
					else {
						Asignatura a = new Asignatura(Integer.parseInt(arrayDocente[0]),Integer.parseInt(arrayDocente[2]), 
								arrayDocente[1].toCharArray()[0]);
						
						this.DocenciaImpartida.put(Integer.parseInt(arrayDocente[0]), a);
					}
				}
			}//Cierra el for
			
		}
	}
	/**
	 * Getter del campo Categoria de cada profesor
	 * @return
	 */
	public String getCategoria(){
		return Categoria;
		
	}
	/**
	 * Getter del campo HorasAsignables de cada profesor
	 * @return
	 */
	public int getHorasAsignables(){
		return HorasAsignables;
	}
	/**
	 * Funcionalidad del programa que se encarga de asignarle carga docente a un profesor.
	 * Recibe un array de Strings el cual contiene la informacion del profesor y de la asignatura a anhadir a la docencia.
	 * Hace las pertinentes comprobaciones y en caso correcto, anhade a la docencia impartida la nueva carga docente.
	 * @param arrayDatos
	 * @throws IOException
	 */
	public static void AsignaCargaDocente(String[] arrayDatos) throws IOException{
		
		if(arrayDatos.length != 5){
			Avisos.avisosFichero("ACDOC--Numero de argumentos incorrecto");
			return;
		}
		String dni = arrayDatos[1].trim();
		if(Proyecto.mapProfesores.get(dni)==null){
			Avisos.avisosFichero("ACDOC--Profesor inexistente");
			return;
		}
		
		//anhadimos a docenciaImpartida la carga docente
		String siglas = arrayDatos[2].trim();
		int idSiglas = Util.PasarSiglasAId(siglas);
		if(Proyecto.mapAsignaturas.get(idSiglas)==null){
			Avisos.avisosFichero("ACDOC--Asignatura Inexistente");
			return;
		} 
		String tipoGrupo = arrayDatos[3].trim();
		if(!tipoGrupo.equalsIgnoreCase("A") && !tipoGrupo.equalsIgnoreCase("B")){
			Avisos.avisosFichero("ACDOC--Tipo de grupo incorrecto");
			return;
		}
		int idGrupo = Integer.parseInt(arrayDatos[4].trim());
		
		if(!Avisos.ExistenciaGrupo(Proyecto.mapAsignaturas.get(idSiglas).getGrupos(), tipoGrupo.charAt(0), idGrupo )) {
			Avisos.avisosFichero("ACDOC--Grupo Inexistente");
			return;
		}
		if(Avisos.ComprobarGrupoAsignado(idSiglas, idGrupo, tipoGrupo.toCharArray()[0])){		
			Avisos.avisosFichero("ACDOC--Grupo ya asignado");
			return;
		}
		
		if(!Avisos.ComprobarHorasAsigSuperiorAlMax(Proyecto.mapProfesores.get(dni),Proyecto.mapAsignaturas.get(idSiglas),idGrupo,
				tipoGrupo.toCharArray()[0])){
			Avisos.avisosFichero("ACDOC--Horas asignables superior al maximo");
			return;
		}
		
		if(Avisos.haySolapeEnProfesor(Proyecto.mapProfesores.get(dni),Proyecto.mapAsignaturas.get(idSiglas),
				idGrupo,tipoGrupo.toCharArray()[0])){
			Avisos.avisosFichero("ACDOC--Se genera solape");
			return;
		}
		
		Proyecto.mapProfesores.get(dni).anhadeDocencia(idSiglas,idGrupo,
				tipoGrupo.toCharArray()[0]);
		Avisos.avisosFichero("ACDOC--OK");				
		
		return;
	}
	/**
	 * Anhade a la docencia impartida del profesor los nuevos campos que se le indican a traves de AsignaCargaDocente
	 * @param idSiglas
	 * @param idGrupo
	 * @param tipoGrupo
	 */
	public void anhadeDocencia(int idSiglas, int idGrupo, char tipoGrupo) {
		if(DocenciaImpartida.get(idSiglas)!=null){
			DocenciaImpartida.get(idSiglas).anhadeGrupo(idGrupo, tipoGrupo);
		}
		else
			DocenciaImpartida.put(idSiglas, new Asignatura(idSiglas, idGrupo, tipoGrupo));
		return;
		
	}
	/**
	 * Metodo auxiliar de obtener clases profesor (implementado debajo) que realiza las ordenaciones y las escribe.
	 * @param output
	 * @throws IOException
	 */
	public void ObtenerClasesProfesor(String output) throws IOException{
		File f = new File(output);
		BufferedWriter bufer = new BufferedWriter(new FileWriter(f));
		Set<Integer> keys = DocenciaImpartida.keySet();
		ArrayList<Grupos> gruposImprimir = new ArrayList<Grupos>(); 
		for(int key:keys){
			ArrayList<Grupos> grupos = Proyecto.mapAsignaturas.get(key).getGrupos();
			ArrayList<Grupos> gruposProfesor = DocenciaImpartida.get(key).getGrupos();
			for(int i = 0; i<gruposProfesor.size(); i++){
				for(int j=0; j<grupos.size(); j++){
					if(grupos.get(j).getIdGrupo()==gruposProfesor.get(i).getIdGrupo()&&
							gruposProfesor.get(i).getTipoGrupo()==grupos.get(j).getTipoGrupo()){
							gruposImprimir.add(grupos.get(j));
					}
				}
			}
			Collections.sort(gruposImprimir, new comparaPorHoras());
			Collections.sort(gruposImprimir);
			for(int i=0; i<gruposImprimir.size(); i++){
				bufer.write(gruposImprimir.get(i).getDia()+"; "+gruposImprimir.get(i).getHoraInicio()+" ;"
						+Proyecto.mapAsignaturas.get(key).getSiglas()+"; "+
						gruposImprimir.get(i).getTipoGrupo()+"; "+gruposImprimir.get(i).getIdGrupo()+"\n");
			}
		}
		bufer.close();
	
			return;
	}
	/**
	 * Metodo que realiza la funcionalidad del proyecto de obtener el calendario de clases del profesor.
	 * Calcula con su docencia impartida los horarios de clases (generando los avisos oportunos).
	 * Escribe en el fichero (cuyo nombre recibe) el horario del profesor ordenado segun varios criterios.
	 * @param arrayDatos
	 * @throws IOException
	 */
	public static void ObtenerCalendarioClases(String[] arrayDatos) throws IOException{
		if(arrayDatos.length!=3){
			Avisos.avisosFichero("CALENP--Numero de argumentos incorrecto");
			return;
		}
		String dniProf=arrayDatos[1].trim();
		String nombrefichero=arrayDatos[2].trim();
		if(Proyecto.mapProfesores.get(dniProf)==null){
			Avisos.avisosFichero("CALENP--Profesor inexistente");
			return;
		}
		if(!Avisos.comprobarAsignacionesProfesor(dniProf)){
			Avisos.avisosFichero("CALENP--Profesor sin asignaciones");
			return;
		}
		
		Proyecto.mapProfesores.get(dniProf).ObtenerClasesProfesor(nombrefichero);
		
		Avisos.avisosFichero("CALENP--OK");
		return;
	}
	/**
	 * Metodo que pasa la docencia impartida del profesor, que va a estar cargada en un mapa, a un String.
	 * Dicho string tendra cada asignatura impartida (con sus grupos) separada por ;
	 * @return String 
	 */
	public String DocenciaImpartidaToString(){
		String docencia ="";
		boolean ponPuntoComa = false;
		Set<Integer> clave = DocenciaImpartida.keySet();
		for(int key:clave){
			ArrayList<Grupos> Grupos = DocenciaImpartida.get(key).getGrupos();
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
	 * Sobreescritura del metodo toString para retornar los campos de profesor que se quieren escribir.
	 * Primero llama al toString del padre (Persona) y luego anhade los campos particulares de profesor
	 */
	public String toString(){
		
		return (super.toString()+"\n"+Categoria +"\n" +Departamento +"\n" +HorasAsignables +"\n" +DocenciaImpartidaToString()
		+"\n");
	}
	/**
	 * Getter del mapa DocenciaImpartida
	 * @return
	 */
	public LinkedHashMap<Integer, Asignatura> getDocenciaImpartida(){
		return DocenciaImpartida;
	}	
	/**
	 * Recibe el identificador del grupo (numero entero) y su tipo (caracter A/B) y comprueba si existe coincidencia.
	 * Devuelve true si coincide. False en caso contrario
	 * @param IdGrupo
	 * @param TipoGrupo
	 * @param IdAsignatura
	 * @return boolean
	 */
	public boolean comprobarGrupo(int IdGrupo, char TipoGrupo, int IdAsignatura){
		if(DocenciaImpartida.get(IdAsignatura) == null){
			return false;
		}
		ArrayList<Grupos> grupos = new ArrayList<Grupos>(DocenciaImpartida.get(IdAsignatura).getGrupos());
		for(int i=0; i<grupos.size(); i++){
			if(grupos.get(i).getIdGrupo() == IdGrupo && grupos.get(i).getTipoGrupo() == (TipoGrupo)){
				return true;
			}
		}
		return false;
	}
	/**
	 * Metodo que comprueba si el numero de horas que se le pretende asignar al profesor no se excede de su maximo.
	 * Retorna true si se le pueden asignar, false en caso contrario.
	 * @param horasNuevaAsig
	 * @return boolean
	 */
	public boolean ComprobarHorasAsignables(int horasNuevaAsig){
		boolean retorno=true;
		int horasYaAsignadas=0;
		Set<Integer>claves=DocenciaImpartida.keySet();
		for(int key:claves){
			ArrayList<Grupos> GruposProf = DocenciaImpartida.get(key).getGrupos();
			ArrayList<Grupos> grupos = Proyecto.mapAsignaturas.get(key).getGrupos();
			
			for(int i=0; i<GruposProf.size(); i++){
				for(int j=0; j<grupos.size();j++){
					if(grupos.get(j).getIdGrupo()==GruposProf.get(i).getIdGrupo()&&
					grupos.get(j).getTipoGrupo()==GruposProf.get(i).getTipoGrupo()){
				
						horasYaAsignadas = (horasYaAsignadas + grupos.get(j).getDuracion());
					}
				}
			}

		}
		if((horasYaAsignadas+horasNuevaAsig)>this.HorasAsignables){
			retorno=false;
		}
		return retorno;
	}
	/**
	 * Comprueba si el horario del profesor se solapa con la nueva docencia que se le esta pretendiendo asignar.
	 * Devuelve un boolean con true/false dependiendo del caso.
	 * @param horaInicio
	 * @param horaFin
	 * @param dia
	 * @return boolean
	 */
	public boolean horarioSolapeProfesor(int horaInicio, int horaFin, char dia) {
		boolean retorno= false;
		Set<Integer> claves = DocenciaImpartida.keySet();
		for(int key:claves){
			ArrayList<Grupos> gruposProfesor = DocenciaImpartida.get(key).getGrupos();
			ArrayList<Grupos> grupos = Proyecto.mapAsignaturas.get(key).getGrupos();
			for(int i=0; i<gruposProfesor.size(); i++){
				for(int j=0; j<grupos.size(); j++){
					if(gruposProfesor.get(i).getTipoGrupo()==grupos.get(j).getTipoGrupo()&&gruposProfesor.get(i).getIdGrupo()==grupos.get(j)
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

}


