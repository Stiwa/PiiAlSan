package Proyecto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.io.IOException;

/**
 * Proyecto Centro Universitario. Grupo PII113
 * Alejandro Santome Valverde (53614814C)
 * Santiago Salvador Rodriguez
 */

/**
 * Clase principal del proyecto en la que se abre el fichero de ejecucion y se hacen las llamadas a los metodos.
 * Al inicio se cargan las personas y las asignaturas a sus respectivos mapas.
 * Al terminar, se guarda en los ficheros los cambios realizados en el programa.
 */
public class Proyecto {


	static LinkedHashMap<String, Alumno> mapAlumnos = new LinkedHashMap<String, Alumno>();
	static LinkedHashMap<String, Profesor> mapProfesores = new LinkedHashMap<String, Profesor>();
	static LinkedHashMap<Integer, Asignatura> mapAsignaturas = new LinkedHashMap<Integer, Asignatura>();
	
	
	public static void main(String[] args) throws IOException {
		//Cargamos en los mapas el contenido de los ficheros personas.txt y asignaturas.txt
		Persona.cargaPersonasAMapa("personas.txt");
		Asignatura.cargaAsignaturasAMapa("asignaturas.txt");
		
		//Abre el fichero de ejecucion. En caso de no existir, error
		Scanner fichero=null;	
		  try {
		  	fichero = new Scanner(new File("ejecucion.txt"));
		  	
		  	String word = fichero.nextLine();
		    while (fichero.hasNextLine()) {
		    	String[] recorre;
		    	
		    	word.trim();
		    	
		    	if(word.charAt(0) == '*'){
		    		if(fichero.hasNextLine()){
		    			word = fichero.nextLine();
		    		}
		    	}else{
		    		recorre = word.split("\\s+");	
		    		
		    		//El nombre del comando puede introducirse en mayuscula o minuscula. lo pasamos a minus para trabajar
		    		switch(recorre[0].toLowerCase()){
			    		case "insertapersona" :
			    			Persona.InsertaPersona(word, recorre[1]);
			    			break;
			    		case "asignacoordinador" :
			    			Asignatura.AsignaCoordinador(recorre);
			    			break;
			    		case "asignacargadocente" :
			    			Profesor.AsignaCargaDocente(recorre);
			    			break;
			    		case "asignagrupo" :
			    			Alumno.asignarGrupo(recorre);
			    			break;
			    		case "matricula" :
			    			Alumno.Matricula(recorre);	
			    			break;
			    		case "evalua" :
			    			Asignatura.evaluaAsignatura(recorre);
			    			break;
			    		case "expediente" :
			    			Alumno.ObtenerExpediente(recorre);
			    			break;
			    		case "obtenercalendarioclases" :
			    			Profesor.ObtenerCalendarioClases(recorre);
			    			break;
			    		case "ordenaalumnosxnota" :
			    			Alumno.OrdenaAlumnosPorNotas(recorre);
			    			break;
			    			
			    		default :
			    			Avisos.avisosFichero("Comando incorrecto <" +recorre[0] +">") ;			

			    			break;
		    		}
		    		word = fichero.nextLine();
			    }
		    }
		  } catch (FileNotFoundException e){
			  Avisos.avisosFichero("Error fichero: ejecucion.txt");
			  System.exit(1);
		  }
		  
		    fichero.close();
		
		    Util.MapaAFichero("personas.txt");
		    Util.MapaAFichero("asignaturas.txt");
	    return;
	}

}
