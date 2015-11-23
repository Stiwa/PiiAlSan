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
		    while (fichero.hasNext()) {
		    	 String word = fichero.nextLine(); 
		    	// String[] auxiliar = word.split("\\*");
		    	// String[] aux = auxiliar[3].split(":");
		    	// String[] aux2 = auxiliar[4].split(":");
		    }
		  } catch (FileNotFoundException e){
			  System.err.println("Error fichero: <fichero>");
			  System.exit(1);
		  }
		    fichero.close();
	    return;
	}

}
