package Proyecto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Set;

//Clase donde vamos a implementar pequeños metodos que pueden usarse desde las diferentes clases del proyecto
public class Util {

 
 //Recibe un String fecha, lo subdivide en dia, mes, año y devuelve un GregorianCalendar
 public static GregorianCalendar PasarAGregorianCalendar(String s){
  
  String auxCalendar[] = s.trim().split("/");
  int dia = Integer.parseInt(auxCalendar[0].trim());
  int mes = Integer.parseInt(auxCalendar[1].trim());
  int anho = Integer.parseInt(auxCalendar[2].trim());
  GregorianCalendar c = new GregorianCalendar();
  c.set(anho, mes, dia);
  
  return c;
 }

  
 
 //METODO PARA ESCRIBIR LOS MAPAS ALUMNO, PROFESOR Y ASIGNATURA A FICHEROS .TXT
public static void MapaAFichero(String nomFich) throws IOException {
  File f=new File(nomFich);
  BufferedWriter bufer = new BufferedWriter(new FileWriter(f));
  Set<String> keys = Proyecto.mapAlumnos.keySet();
  for(String key:keys){
	bufer.write("*"+"\nalumno\n"+Proyecto.mapAlumnos.get(key).aString());
	  
  }
  keys=Proyecto.mapProfesores.keySet();
  bufer.close();
 // for(String key:keys1){
//	  if(Proyecto.mapAlumnos.get(key).equals("Profesor")){
//		  bufer.write("Profesor : "+Proyecto.mapProfesores.get(key));
//	  }
 
		  
  
	
  }
  
 
  
 }
 
 
