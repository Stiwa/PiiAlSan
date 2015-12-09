package Proyecto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Avisos {
	
	public static void avisosFichero(String comando) throws IOException{	
	  	try {
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
	
}
