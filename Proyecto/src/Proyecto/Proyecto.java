package Proyecto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Proyecto {

	public static void main(String[] args) {
		//Abre el fichero de ejecución. En caso de no existir, error
		Scanner fichero = null;	
		  try {
		  	fichero = new Scanner(new File("ejecucion.txt"));
		  	
		  	String word = fichero.nextLine();
		    while (fichero.hasNextLine()) {
		    	String[] recorre;
		    	
		    	word.trim();
		    	
		    	if(word.charAt(0) == '*'){
		    		//System.out.println(word);
		    		if(fichero.hasNextLine()){
		    			word = fichero.nextLine();
		    		}
		    	}else{
		    		recorre = word.split(" ");	
		    		
		    		switch(recorre[0]){
			    		case "InsertaPersona" :
			    			System.out.println(word);
			    			break;
			    		case "AsignaCoordinador" :
			    			System.out.println(word);
			    			break;
			    		case "AsignaCargaDocente" :
			    			System.out.println(word);
			    			break;
			    		case "AsignaGrupo" :
			    			System.out.println(word);
			    			break;
			    		case "Matricula" :
			    			System.out.println(word);
			    			break;
			    		case "Evalua" :
			    			System.out.println(word);
			    			break;
			    		case "Expediente" :
			    			System.out.println(word);
			    			break;
			    		case "ObtenerCalendarioClases" :
			    			System.out.println(word);
			    			break;
			    			
			    		default : 
			    			System.out.println("Mal escrito.");
			    			break;
		    		}
		    		word = fichero.nextLine();
			    	// String[] auxiliar = word.split("\\*");
			    	// String[] aux = auxiliar[3].split(":");
			    	// String[] aux2 = auxiliar[4].split(":");
			    }
		    }
		  } catch (FileNotFoundException e){
			  System.err.println("Error fichero: ejecucion.txt");
			  System.exit(1);
		  }
		    fichero.close();
	    return;
	}

}
