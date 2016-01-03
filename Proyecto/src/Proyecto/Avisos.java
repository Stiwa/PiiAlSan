package Proyecto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;


public class Avisos {
	
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
		return;
	}
	
	
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
	
	//Comprobado
	public static boolean ComprobarFechaIngreso(Calendar fechaNac, Calendar fechaIng){
		
		int edadMinima = 15;
		int edadMaxima = 65;
		double difEnAnhos = fechaIng.getTimeInMillis()/1000/60/60/24/365-fechaNac.getTimeInMillis()/1000/60/60/24/365;
		if(edadMinima<difEnAnhos && edadMaxima>difEnAnhos){
			return true;
		}
		return false;
	}
	
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
	public static boolean EsTitular(String arrayDatos){
			if(Proyecto.mapProfesores.get(arrayDatos).getCategoria().equals("titular"))
				return true;
	return false;
		
	}
	/*
	public static boolean ComprobarSiglasExistentes(String siglas){
		
		int contador = 0;
	    Set<Integer> clave= Proyecto.mapAsignaturas.keySet();
	    	
	    	if(siglas.equals(Proyecto.mapAsignaturas.get(siglas)) ){
	    		contador++;
	    	}
	    	
	    }
	    if(contador == 0){
	    	return false;
	    }
		
		return true;
	}*/
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
	public static boolean comprobarPrerrequisitos(Asignatura asig, Alumno al){
		boolean retorno = true;
		for(int i=0; i<asig.getPrerrequisitos().size(); i++){
			if(!al.ComprobarSiAprobado(asig.getPrerrequisitos().get(i))){
				retorno = false;;
			}
		}
		return retorno;
	}
	public static boolean ComprobarHorasAsigSuperiorAlMax(Profesor p,Asignatura a,int idGrupo,char tipoGrupo){

		boolean retorno = true;
		int horasNuevas=a.getGrupo(idGrupo, tipoGrupo).getDuracion();
		if(!p.ComprobarHorasAsignables(horasNuevas)){
			retorno=false;
		}
		
		
		return retorno;
		
	}
	public static boolean comprobarTipoGrupo(String tipoGrupo){
		boolean retorno= false;
		if(tipoGrupo.equalsIgnoreCase("A")||tipoGrupo.equalsIgnoreCase("B")){
			
			retorno= true;
		}
		return retorno;
	}


	public static boolean haySolapeEnAlumno(Alumno alumno, Asignatura asignatura, char tipoGrupo, int idGrupo) {
		boolean retorno=false;
		if(alumno.horarioSolapeAlumno(asignatura.getGrupo(idGrupo, tipoGrupo).getHoraInicio(), asignatura.getGrupo(idGrupo, tipoGrupo).getHoraFin(), 
				asignatura.getGrupo(idGrupo, tipoGrupo).getDia())){
			
		
			retorno= true;
		}
		return retorno;
	}


	public static boolean haySolapeEnProfesor(Profesor profesor, Asignatura asignatura, int idGrupo, char tipoGrupo) {
		boolean retorno= true;
		if(profesor.horarioSolapeProfesor(asignatura.getGrupo(idGrupo, tipoGrupo).getHoraInicio(),asignatura.getGrupo(idGrupo, tipoGrupo).getHoraFin(),
				asignatura.getGrupo(idGrupo, tipoGrupo).getDia())){
			retorno=true;
		}
		return retorno;
	}
	
	
}
