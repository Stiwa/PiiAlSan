package Proyecto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Set;

//Clase donde vamos a implementar pequenhos metodos que pueden usarse desde las diferentes clases del proyecto.
public class Util {

 
	
	//Recibe un String fecha, lo subdivide en dia, mes, anho y devuelve un Calendar
	public static Calendar PasarACalendar(String s){
	  
		  String[] auxCalendar = s.trim().split("/");
		  int dia = Integer.parseInt(auxCalendar[0]);
		  int mes = Integer.parseInt(auxCalendar[1]);
		  int anho = Integer.parseInt(auxCalendar[2]);
		 
		  Calendar c = Calendar.getInstance();
		  c.set(anho, mes, dia);
		  
		  return c;
	}
	//METODO PARA ESCRIBIR LOS MAPAS ALUMNO, PROFESOR Y ASIGNATURA A FICHEROS .TXT
	public static void MapaAFichero(String nomFich) throws IOException {
		boolean ponAsterisco = false;
		  File f=new File(nomFich);
		  BufferedWriter bufer = new BufferedWriter(new FileWriter(f));
		  if(nomFich.equalsIgnoreCase("personas.txt")){
			  Set<String> clave = Proyecto.mapAlumnos.keySet();
			  for(String key:clave){
				  if(ponAsterisco) 
					  bufer.write("*"+"\nalumno\n"+Proyecto.mapAlumnos.get(key).toString());
				  else {
					  bufer.write("alumno\n"+Proyecto.mapAlumnos.get(key).toString());  
					  ponAsterisco = true;
				  }
			  }
			  ponAsterisco = false;
			  clave=Proyecto.mapProfesores.keySet();
			  for(String key:clave){
				  bufer.write("*"+"\nProfesor\n"+Proyecto.mapProfesores.get(key).toString());
			  }
	
		  }
		  else if(nomFich.equalsIgnoreCase("asignaturas.txt")){
			  Set<Integer> clave = Proyecto.mapAsignaturas.keySet();
			  for(Integer key:clave){
				  if(ponAsterisco)
					  bufer.write("*"+"\n"+Proyecto.mapAsignaturas.get(key).toString());
				  else{
					  ponAsterisco = true;
					  bufer.write(Proyecto.mapAsignaturas.get(key).toString());
				  }
			  }
			  ponAsterisco = false;
		  }
		  bufer.close();	  
	}	
	public static int PasarSiglasAId(String siglas){
		
		Set<Integer> keys = Proyecto.mapAsignaturas.keySet();
		Asignatura asignatura = null;
		for(int key:keys){
			if(Proyecto.mapAsignaturas.get(key).getSiglas().equalsIgnoreCase(siglas)){
				asignatura = Proyecto.mapAsignaturas.get(key);
				break;
			}
		}
		if(asignatura == null){
			return 0;
		}
		return asignatura.getIdAsignatura();
		
	}
	
	
}
 
 
