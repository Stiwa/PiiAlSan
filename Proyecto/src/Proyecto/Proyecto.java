package Proyecto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;
import java.io.IOException;

//Clase princial del proyecto.
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
			    			//Profesor.AsignaCargaDocente(recorre);
			    			break;
			    		case "asignagrupo" :
			    			//System.out.println(word);
			    			break;
			    		case "matricula" :
			    			Alumno.Matricula(recorre);	
			    			break;
			    		case "evalua" :
			    			//System.out.println(word);
			    			break;
			    		case "expediente" :
			    			//System.out.println(word);
			    			break;
			    		case "obtenercalendarioclases" :
			    			//System.out.println(word);
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
		    //Imprime los alumnos por pantalla de prueba
		  /*  Set<String>keys= mapAlumnos.keySet();
		    for(String key:keys){
		    	System.out.println(mapAlumnos.get(key));
		    	
		    }
		    keys = mapProfesores.keySet();
		    System.out.println("Profesores\n");
		    for(String key:keys){
		    	System.out.println(mapProfesores.get(key));
		    	
		    }*/
		   Util.MapaAFichero("personas.txt");
		   Util.MapaAFichero("asignaturas.txt");
	    return;
	}
//xx
}
