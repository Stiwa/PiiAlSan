package Proyecto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Avisos {
	
	public static void avisosFichero(String comando) throws IOException{	
		Scanner fichero;
		
	  	try {
	  		File f = new File("avisos.txt");
	  		fichero = new Scanner(f); 
	  		BufferedWriter bufer = new BufferedWriter(new FileWriter(f, true));
	  		
	  		if (fichero != null){
	  			while(fichero.hasNextLine()){
	  				fichero.nextLine();
	  			}
	  		}
	  		
			bufer.write(comando);
			bufer.newLine();
			bufer.close();

	  	} catch (FileNotFoundException e){
	  		System.err.println("Error fichero: <avisos.txt>");
	  	}
		return;
	}
	
}
