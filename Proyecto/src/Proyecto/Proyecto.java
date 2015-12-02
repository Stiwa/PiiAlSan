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
		    while (fichero.hasNextLine()) {
		    	
		    	String word = fichero.nextLine(); 
		    	String[] recorre;
		    	
		    	if(word.charAt(0) == '*'){
		    		if(fichero.hasNextLine()){
		    			word = fichero.nextLine();
		    		}
		    	}
		    		recorre = word.split("\\ ");	
		    		
		    		switch(recorre[0]){
			    		case "InsertaPersona" :
			    			System.out.println(word);
			    			break;
			    		case "AsignaCoordinador" :
			    			break;
			    		default : 
			    			System.out.println("Mal escrito.");
			    			break;
		    		}
		    	// String[] auxiliar = word.split("\\*");
		    	// String[] aux = auxiliar[3].split(":");
		    	// String[] aux2 = auxiliar[4].split(":");
		    }
		  } catch (FileNotFoundException e){
			  System.err.println("Error fichero: ejecucion.txt");
			  System.exit(1);
		  }
		    fichero.close();
	    return;
	}

}
