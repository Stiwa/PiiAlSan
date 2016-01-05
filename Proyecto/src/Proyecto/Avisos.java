package Proyecto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;


public class Avisos {
	/**
	 * Metodo que escribe al fichero avisos.txt los avisos que se vayan generando en la ejecucion del programa.
	 * Recibe la cadena que va a escribir al fichero.
	 * @param comando
	 * @throws IOException
	 */
	public static void avisosFichero(String comando) throws IOException{	
		Scanner fichero = null;

	  	try {
	  		fichero = new Scanner(new File("avisos.txt")); 
	  		if (fichero != null){
	  				fichero.nextLine();	
	  		}
	  		
	  		File f = new File("avisos.txt");
	  		BufferedWriter bufer = new BufferedWriter(new FileWriter(f, true));
	  	
			bufer.write(comando);
			bufer.newLine();
			bufer.close();

	  	} catch (FileNotFoundException e){
	  		System.err.println("Error fichero: <avisos.txt>");
	  	}
	  	finally{
	  		fichero.close();
	  	}
		return;
	}
	/**
	 * Comprueba si el DNI que recibe en forma de cadena es correcto o no.
	 * Retorna true si es correcto, false en caso contrario.
	 * @param DNI
	 * @return boolean
	 */
	public static boolean ComprobarDNI(String DNI){
		DNI.trim();
		
		if(DNI.length() <9 || DNI.length() >9){
			return false;
		}		
		//Comprobamos caracter a caracter que todos son correctos en un DNI		
		char[] elementos = DNI.toCharArray();
		if (elementos[DNI.length()-1] < 'A'  || elementos[DNI.length()-1] >'Z') {
			return false;
		}
		for(int i=0; i<DNI.length()-1; i++){
			if(elementos[i] < '0' || elementos[i] > '9'){
				return false;
			}
		}	
		return true;
	}	
	/**
	 * Comprueba si la fecha que recibe como Calendar tiene el formato y los valores permitidos por el software.
	 * Retorna true si es correcta y false en caso de erronea.
	 * @param Fecha
	 * @return boolean
	 */
	public static boolean ComprobarFecha(Calendar Fecha){
		
		//Comprobamos si la fecha introducida esta entre el minimo y el maximo
		//permitido
		Calendar FechaMaxima = Calendar.getInstance();
		Calendar FechaMinima = Calendar.getInstance();
		FechaMaxima.set(2020, 1, 1);
		FechaMinima.set(1950, 1, 1);
		try{
			Fecha.setLenient(false);
			Fecha.getTime();
		} catch(Exception time){
			return false;
		}
		
		if(Fecha.getTimeInMillis()-FechaMinima.getTimeInMillis()<0){
			return false;  //la fecha introducida es menor que la minima
		}
		if(FechaMaxima.getTimeInMillis()-Fecha.getTimeInMillis()<0){
			return false;  // la fecha introducida es mayor que la maxima
		}
		return true;
	}
	/**
	 * Verifica la existencia del fichero cuyo nombre recibe como un string.
	 * Devuelve falso si no existe, true si existe.
	 * @param nombre
	 * @return boolean
	 */
	public static boolean compruebaExistenciaFichero(String nombre){
		Scanner fichero = null;
		boolean retorno = true;
		try{
			fichero = new Scanner(new File(nombre));
			fichero.close();
		}catch(FileNotFoundException e){
			retorno = false;
		}
		return retorno;
	}
	/**
	 * Comprueba si la fecha de ingreso de un alumno es correcta segun los criterios especificados de edad.
	 * Retorna true si es correcta y false si no lo es.
	 * @param fechaNac
	 * @param fechaIng
	 * @return boolean
	 */
	public static boolean ComprobarFechaIngreso(Calendar fechaNac, Calendar fechaIng){
		
		int edadMinima = 15;
		int edadMaxima = 65;
		double difEnAnhos = fechaIng.getTimeInMillis()/1000/60/60/24/365-fechaNac.getTimeInMillis()/1000/60/60/24/365;
		if(edadMinima<difEnAnhos && edadMaxima>difEnAnhos){
			return true;
		}
		return false;
	}	
	/**
	 * Metodo que comprueba si el grupo que se esta pretendiendo asignar ya ha sido asignado previamente.
	 * Devuelve false si no ha sido asignado, true si lo ha sido.
	 * @param IdAsignatura
	 * @param IdGrupo
	 * @param TipoGrupo
	 * @return boolean
	 */
	public static boolean ComprobarGrupoAsignado(int IdAsignatura, int IdGrupo, char TipoGrupo ){
		boolean retorno = false;
		Set<String> clave = Proyecto.mapProfesores.keySet();
		//Cogemos todos los profesores 
		for(String key:clave){
			ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura> 
			(Proyecto.mapProfesores.get(key).getDocenciaImpartida().values() );
			//cogemos las asignaturas que imparte cada profesor
			for (int i=0; i<asignaturas.size(); i++){
				if(Proyecto.mapProfesores.get(key).comprobarGrupo(IdGrupo, TipoGrupo, IdAsignatura)){
					retorno = true;
				}
				if(!retorno) break;
			}
			if(!retorno) break;
		}	
		return retorno;
	}	
	/**
	 * Comprueba si el numero de horas asignables es el correcto dependiendo del tipo de profesor (titular o asociado)
	 * Retorna true si es correcto. False si es incorrecto.
	 * @param Horas
	 * @param TipoProfesor
	 * @return boolean
	 */
	public static boolean ComprobarHorasAsig(int Horas, String TipoProfesor){
		//Aqui depende de si el profesor es titular (20horas) o asocidado(15horas)
		if (Horas<0){
			return false;
		}
		if(TipoProfesor.equals("titular")){
			if(Horas>20){
				return false;
			}
			return true;
		}
		if (Horas>15){
			return false;
		}
		
		return true;
	}
	/**
	 * Metodo que verifica si la edad calculada es correcta.
	 * Retorna true si correcta, false si no.
	 * @param edad
	 * @return boolean
	 */
	public static boolean ComprobarEdad(int edad){
		//comprobacion de edad minima y maxima permitida
		int EdadMinima=15;
		int EdadMaxima=65;
		if(EdadMinima>edad){
			return false;	
		}
		if(EdadMaxima<edad){
			return false;
		}
		return true;
	}
	/**
	 * Metodo que comprueba si un profesor es titular.
	 * Retorna false si no lo es. True si lo es
	 * @param arrayDatos
	 * @return boolean
	 */
	public static boolean EsTitular(String arrayDatos){
			if(Proyecto.mapProfesores.get(arrayDatos).getCategoria().equals("titular"))
				return true;
	return false;
		
	}
	/**
	 * Calcula el numero de asignaturas que coordina cada profesor.
	 * Devuelve un entero con el numero de asignaturas coordinadas.
	 * @param arrayDatos
	 * @return entero (contador)
	 */
	public static int numeroAsignaturasCoordinadas(String arrayDatos){
		int contador=0;
		Set<Integer> clave= Proyecto.mapAsignaturas.keySet();
	    for(Integer key:clave){	
	    	if(arrayDatos.trim().equals(Proyecto.mapAsignaturas.get(key).getCoordinador()) ){
	    		contador++;
	    	}
	    	
	    }

		return contador;
		
	}	
	/**
	 * Comprueba la existencia del grupo del cual recibe su identificador y su tipo.
	 * Devuelve true si existe y false si no lo hace.
	 * @param grupos
	 * @param tipoGrupo
	 * @param idGrupo
	 * @return boolean
	 */
	public static boolean ExistenciaGrupo(ArrayList<Grupos> grupos, char tipoGrupo, int idGrupo){
		boolean retorno = false;
		
		for(int i=0; i<grupos.size(); i++){
			if(tipoGrupo == grupos.get(i).getTipoGrupo() && idGrupo == grupos.get(i).getIdGrupo()){
				retorno = true;
				break;
			}
		}
		
		return retorno;
	}
	/**
	 * Metodo que comprueba si el alumno ha cumplido los prerrequisitos necesarios de una asignatura.
	 * Recibe un objeto alumno y otro asignatura.
	 * Devuelve true si los ha cumplido y false si no lo ha hecho.
	 * @param asig
	 * @param al
	 * @return boolean
	 */
	public static boolean comprobarPrerrequisitos(Asignatura asig, Alumno al){
		boolean retorno = true;
		for(int i=0; i<asig.getPrerrequisitos().size(); i++){
			if(!al.ComprobarSiAprobado(asig.getPrerrequisitos().get(i))){
				retorno = false;;
			}
		}
		return retorno;
	}
	/**
	 * Verifica si el numero de horas asignables de un profesor es superior al maximo o no.
	 * Recibe un objeto profesor, una asignatura y la informacion del grupo que se pretende asignar.
	 * Retorna true si no es superior, false si lo es.
	 * @param p
	 * @param a
	 * @param idGrupo
	 * @param tipoGrupo
	 * @return boolean
	 */
	public static boolean ComprobarHorasAsigSuperiorAlMax(Profesor p,Asignatura a,int idGrupo,char tipoGrupo){

		boolean retorno = true;
		int horasNuevas=a.getGrupo(idGrupo, tipoGrupo).getDuracion();
		if(!p.ComprobarHorasAsignables(horasNuevas)){
			retorno=false;
		}
		
		
		return retorno;
		
	}
	/**
	 * Comprueba si el tipo de grupo introducido es el correcto segun las especificaciones (A o B)
	 * Retorna true si es correcto y false en caso contrario.
	 * @param tipoGrupo
	 * @return boolean
	 */
	public static boolean comprobarTipoGrupo(String tipoGrupo){
		boolean retorno= false;
		if(tipoGrupo.equalsIgnoreCase("A")||tipoGrupo.equalsIgnoreCase("B")){
			
			retorno= true;
		}
		return retorno;
	}
	/**
	 * Verifica si en el objeto alumno que recibe hay solape entre sus asignaturas y el nuevo grupo que se pretende asignar.
	 * Retorna true si hay solape, false si no lo hay.
	 * @param alumno
	 * @param asignatura
	 * @param tipoGrupo
	 * @param idGrupo
	 * @return boolean
	 */
	public static boolean haySolapeEnAlumno(Alumno alumno, Asignatura asignatura, char tipoGrupo, int idGrupo) {
		boolean retorno=false;
		if(alumno.horarioSolapeAlumno(asignatura.getGrupo(idGrupo, tipoGrupo).getHoraInicio(), 
				asignatura.getGrupo(idGrupo, tipoGrupo).getHoraFin(), asignatura.getGrupo(idGrupo, tipoGrupo).getDia())){
			
			retorno= true;
		}
		return retorno;
	}
	/**
	 * Comprueba si hay solape en los horarios del profesor que recibe entre su docencia impartida y la nueva a añadir.
	 * Devuelve true si hay solape y false si no lo hay.
	 * @param profesor
	 * @param asignatura
	 * @param idGrupo
	 * @param tipoGrupo
	 * @return boolean
	 */
	public static boolean haySolapeEnProfesor(Profesor profesor, Asignatura asignatura, int idGrupo, char tipoGrupo) {
		boolean retorno= false;
		if(profesor.horarioSolapeProfesor(asignatura.getGrupo(idGrupo, tipoGrupo).getHoraInicio(),
				asignatura.getGrupo(idGrupo, tipoGrupo).getHoraFin(),asignatura.getGrupo(idGrupo, tipoGrupo).getDia())){
			retorno=true;
		}
		return retorno;
	}
	/**
	 * Metodo que comprueba si la asignatura de la cual recibe el identificador ya ha sido evaluada.
	 * Retorna true si ha sido evaluada y false si no lo ha sido.
	 * @param idSiglas
	 * @param ficheroNotas
	 * @param anhoAcademico
	 * @return boolean
	 * @throws IOException
	 */
	public static boolean comprobarAsignaturaYaEvaluada(int idSiglas, String ficheroNotas, String anhoAcademico) throws IOException{
		boolean retorno = false;
		Scanner fichero = new Scanner(new File(ficheroNotas));
		while(fichero.hasNextLine()){
			String linea = fichero.nextLine().trim();
			String [] arrayLinea = linea.split("\\s+");
			if(arrayLinea.length != 3){
				Avisos.avisosFichero("Numero de parametros incorrecto " +linea);
				break;
			}
			Set<String> clave = Proyecto.mapAlumnos.keySet();
			for(String key:clave){
				LinkedHashMap <Integer, Notas> mapAsig = Proyecto.mapAlumnos.get(key).getAsignaturasSuperadas();
				if(mapAsig.get(idSiglas) == null){
					retorno = false;
					continue;
				}
				if(mapAsig.get(idSiglas).getAnhoAcademico().equals(anhoAcademico)){
					retorno = true;
					break;
				}	
			}
			fichero.nextLine();
		}
		
		fichero.close();
		return retorno;
	}
	/**
	 * Metodo que comprueba si el profesor tiene asignaciones.
	 * Retorna true si las tiene y false sino.
	 * @param dniProf
	 * @return boolean
	 */
	public static boolean comprobarAsignacionesProfesor(String dniProf){
		boolean retorno = true;
		Set<Integer> clave = Proyecto.mapProfesores.get(dniProf).getDocenciaImpartida().keySet();
		for(Integer key:clave){
			ArrayList<Grupos> grupos =Proyecto.mapProfesores.get(dniProf)
					.getDocenciaImpartida().get(key).getGrupos();	
			if(grupos == null){
				retorno = false;
				break;
			}
		}
		return retorno;
	} 
	/**
	 * Realiza las comprobaciones del fichero de notas que se utiliza en evalua y genera los avisos pertinentes.
	 * @param idSiglas
	 * @param ficheroNotas
	 * @param cursoAcademico
	 * @throws IOException
	 */
	public static void comprobarFicheroNotas(int idSiglas, String ficheroNotas, String cursoAcademico) throws IOException {
		Scanner fichero = new Scanner(new FileReader(ficheroNotas));
		while(fichero.hasNextLine()){
			String linea = fichero.nextLine().trim();
			String [] arrayLinea = linea.split("\\s+");
			if(arrayLinea.length != 3){
				Avisos.avisosFichero("Numero de parametros incorrecto " +linea);
				break;
			}
			String dni = arrayLinea[0].trim();
			float notaTeoria = Float.parseFloat(arrayLinea[1].trim());
			float notaPractica = Float.parseFloat(arrayLinea[2].trim());
		
			if(Proyecto.mapAlumnos.get(dni) == null){
				Avisos.avisosFichero("Alumno inexistente: <" +dni +">");
			}
			else if(!Proyecto.mapAlumnos.get(dni).ComprobarSiMatriculado(idSiglas)){
				Avisos.avisosFichero("Alumno no matriculado: <" +dni +">");
			}
			else if((notaTeoria < 0 || notaTeoria > 5) || (notaPractica < 0 || notaPractica > 5)){
				Avisos.avisosFichero("Nota grupo A/B incorrecta");
			}	
			else{
				Proyecto.mapAlumnos.get(dni).evaluar(idSiglas, new Notas(notaTeoria+ 
						notaPractica, cursoAcademico));
			}
		}
	
		
		fichero.close();
		return;
		
	}
	
	
}
