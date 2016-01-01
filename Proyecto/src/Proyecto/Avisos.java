package Proyecto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.GregorianCalendar;
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
	public static boolean EsTitular(String arrayDatos){
		
		//no funciona porque asignatura.getcoordinador esta vacia y no se por que
		System.out.println("\n\n"+Asignatura.getCoordinador()+"\n\n\n");
			if(Asignatura.getCoordinador().equalsIgnoreCase(arrayDatos)){
			return true;
			
			}
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
	    x
		
		
		return true;
	}*/
	public static int numeroAsignaturasCoordinadas(String arrayDatos){
		int contador=0;
		Set<Integer> clave= Proyecto.mapAsignaturas.keySet();
	    for(Integer key:clave){
	    	
	    	if(arrayDatos.equals(Proyecto.mapAsignaturas.get(key)) ){
	    		contador++;
	    	}
	    	
	    }
	    System.out.println("EL CONTADOR ES: "+contador);

		return contador;
		
	}
	
	public static boolean ComprobarMatricula(String DocenciaRecibida, int idAsignatura){
		boolean retorno=true;
		
		if(DocenciaRecibida.length()==0){
			return retorno;
		}
		String[] aux=DocenciaRecibida.trim().split(";");
		for(int i=0;i<aux.length;i++){
			String[] aux2= aux[i].trim().split(" ");
			if(Integer.parseInt(aux2[0])==idAsignatura){
				retorno=false;
				break;
			}
		}
		return retorno;
	}
	
	
	
	
}
