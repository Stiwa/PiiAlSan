package Proyecto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Scanner;

import  Proyecto.Avisos;

public class Persona {
	private String Nombre = new String();
	private String Apellidos = new String();
	private String DNI = new String();
	private GregorianCalendar FechaNacimiento = new GregorianCalendar();
	private String Perfil = new String();
	
	protected Persona(){
	}
	
	protected Persona(String Nombre, String Apellidos, String DNI, GregorianCalendar FechaNacimiento){
		
		this.Nombre = Nombre;
		this.Apellidos = Apellidos;
		this.DNI = DNI;
		this.FechaNacimiento = FechaNacimiento;
	}
	
	protected Persona(String Nombre, String Apellidos, String DNI, GregorianCalendar FechaNacimiento, String Perfil){
		
		this.Nombre = Nombre;
		this.Apellidos = Apellidos;
		this.DNI = DNI;
		this.FechaNacimiento = FechaNacimiento;
		this.Perfil = Perfil;
	}
	
	public static void InsertaPersona(String datos, String perfil) throws IOException{ //En perfil recibimos el segundo elemento 
																					   //Del array de Strings, o sea, el tipo 
		
		String[] arrayDatos = datos.trim().split("\"");
		String[] linea = arrayDatos[0].trim().split("\\s+");
		
		
		//Comprobamos que el numero de parametros sea el correcto
		
		if(arrayDatos.length!=7 && arrayDatos.length!=5){
			Avisos.avisosFichero("Numero de parametros incorrecto");
			return;
		}
		if(Avisos.ComprobarDNI(linea[2]) == false){
			Avisos.avisosFichero("DNI incorrecto" +linea[2]);
		}
		
		String tipo = linea[1];
		
		tipo.trim();
		if(tipo.equals("alumno")){
		//Alumno.InsertaAlumno(linea);
			//NO SE METE NI ALUMNO NI PROFESOR EN EL MAPA PORQUE AL PARECER HAYA LGUN PROBLEMA CUANDO HACEMOS EL SPLIT EN LA LINEA DE EJECUCION
		   	
		}else if(tipo.equals("profesor")){
		//Profesor.InsertaProfesor(linea);
		}else
			Avisos.avisosFichero("Campo perfil incorrecto: <" +linea[1] +">");
		
	}
	
	public String getDNI(){
		return DNI;
	}
	public String getNombre(){
		return Nombre;
	}
	public String getApellidos(){
		return Apellidos;
	}

	
	
	public static void cargaPersonasAMapa(String nombreArchivo) throws IOException{
		
		try {
			Scanner input = new Scanner(new FileInputStream(nombreArchivo));
			
			while(input.hasNextLine()){
				
				if(input.nextLine().trim().equalsIgnoreCase("alumno")){
					
					//Variables que tienen todas las personas
					String perfil = input.nextLine().trim();
					String dni = input.nextLine().trim();
					String nombre = input.nextLine().trim();
					String apellidos = input.nextLine().trim();
					
					String aux[] = input.nextLine().split("/");
					int dia = Integer.parseInt(aux[0]);
					int mes = Integer.parseInt(aux[1]);
					int anho = Integer.parseInt(aux[2]);
					GregorianCalendar FechaNacimiento = new GregorianCalendar(dia, mes, anho);
					
					//Ahora vamos con las variables particulares de alumno y profesor
					if(perfil.trim().equals("alumno")){
						String aux1[] = input.nextLine().split("/");
						int diaIng = Integer.parseInt(aux1[0]);
						int mesIng = Integer.parseInt(aux1[1]);
						int anhoIng = Integer.parseInt(aux1[2]);
						GregorianCalendar fechaIng = new GregorianCalendar(diaIng, mesIng, anhoIng);
						
						//String asignaturasSuperadas = entrada.nextLine().trim();
						//String docenciaRecibida = entrada.nextLine().trim();
						Alumno nuevoAlumno = new Alumno(nombre.trim(), apellidos.trim(),dni.trim(), FechaNacimiento, fechaIng);
						//A�ade el nuevo alumno leido del fichero al mapa de alumnos
						Proyecto.mapAlumnos.put(dni, nuevoAlumno);
						
					}else if(perfil.trim().equals("profesor")){
						System.out.println("Profesor");
					}
				}
				//Avanza hasta el siguiente asterisco que marca la nueva persona a cargar
				
				
			}//Cierra el while
					
			input.close();
				
		}catch (FileNotFoundException e){
			  Avisos.avisosFichero("Error fichero: " +nombreArchivo);
			  System.exit(1);
		}
	}
	
	
	
}

