package Proyecto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Scanner;


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
		if(DNI.length() != 9){
			return false;
		}
		//Comprobamos caracter a caracter que todos son correctos en un DNI
		char[] elementos = DNI.toCharArray();
		if ('0' > elementos[8] && elementos[8] >'9') {
			return false;
		}
		for(int i=0; i<DNI.length()-2; i++){
			if('A' > elementos[i] || elementos[i] < 'Z'){
				return false;
			}
		}	
		return true;
	}
	
	
	public static boolean ComprobarFecha(GregorianCalendar Fecha){
		
		//Comprobamos si la fecha introducida esta entre el minimo y el maximo
		//permitido
		GregorianCalendar FechaMaxima = null;
		GregorianCalendar FechaMinima = null;
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
	
	
}
