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
		   //	Alumno.InsertaAlumno(linea);
		   	
		}else if(tipo.equals("profesor")){
			//Profesor.InsertaProfesor(datos);
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

	
	/*public static void cargaPersonasAMapa(String nombreArchivo) throws IOException{
		
		try {
			Scanner entrada = new Scanner(new FileInputStream(nombreArchivo));
			while(entrada.hasNextLine()){
				
				String perfil = entrada.nextLine().trim();	
				String dni = entrada.nextLine().trim();
				String nombre = entrada.nextLine().trim();
				String apellidos = entrada.nextLine().trim();
				
				String aux[] = entrada.nextLine().split("/");
				int dia = Integer.parseInt(aux[0]);
				int mes = Integer.parseInt(aux[1]);
				int anho = Integer.parseInt(aux[2]);
				GregorianCalendar FechaNacimiento = new GregorianCalendar(dia, mes, anho);
				
				if(perfil.trim().equals("alumno")){
					String aux1[] = entrada.nextLine().split("/");
					int diaIng = Integer.parseInt(aux1[0]);
					int mesIng = Integer.parseInt(aux1[1]);
					int anhoIng = Integer.parseInt(aux1[2]);
					GregorianCalendar fechaIng = new GregorianCalendar(diaIng, mesIng, anhoIng);
					
					//String asignaturasSuperadas = entrada.nextLine().trim();
					//String docenciaRecibida = entrada.nextLine().trim();
					Alumno nuevoAlumno = new Alumno(nombre.trim(), apellidos.trim(),dni.trim(), FechaNacimiento, fechaIng);
					Proyecto.mapAlumnos.put(dni, nuevoAlumno);
					
				}else if(perfil.trim().equals("profesor")){
					System.out.println("Profesor");
				}
			
				if(entrada.hasNextLine()){
					String word = entrada.nextLine();
					if (word == null){
						if(entrada.hasNextLine() ){
								entrada.nextLine();
						}
					}
						
					if(word.trim().charAt(0) != '*'){
						if(entrada.hasNextLine()){
							entrada.nextLine();
						}
					}	
					
				}
				
			}
			entrada.close();
			
		 }catch (FileNotFoundException e){
			  Avisos.avisosFichero("Error fichero: " +nombreArchivo);
			  System.exit(1);
		 }
	}*/
}

