package Proyecto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import  Proyecto.Avisos;

public class Persona {
	private String Nombre = new String();
	private String Apellidos = new String();
	private String DNI = new String();
	private Calendar FechaNacimiento = Calendar.getInstance();
	private String Perfil = new String();
	
	protected Persona(){
	}
	
	protected Persona(String Nombre, String Apellidos, String DNI, Calendar FechaNacimiento){
		
		this.Nombre = Nombre;
		this.Apellidos = Apellidos;
		this.DNI = DNI;
		this.FechaNacimiento = FechaNacimiento;
	}
	
	protected Persona(String Nombre, String Apellidos, String DNI, Calendar FechaNacimiento, String Perfil){
		
		this.Nombre = Nombre;
		this.Apellidos = Apellidos;
		this.DNI = DNI;
		this.FechaNacimiento = FechaNacimiento;
		this.Perfil = Perfil;
	}
	//En datos recibimos toda la linea del fichero de ejecucion. En perfil recibimos el el tipo (al/prof)
	public static void InsertaPersona(String datos, String perfil) throws IOException{  
																						
		String[] arrayDatos = datos.trim().split("\"");
		
		String[] lineaDatos = arrayDatos[0].trim().split("\\s+");
			
		//Comprobamos que el numero de parametros sea el correcto
		if(arrayDatos.length!=7 && arrayDatos.length!=5){
			Avisos.avisosFichero("Numero de argumentos incorrecto");
			return;
		}
		String nombre=arrayDatos[1].trim();
		String apellidos=arrayDatos[3].trim();
		String perfil2=lineaDatos[1].trim();
		String dni=lineaDatos[2].trim();
		
		
		if(Avisos.ComprobarDNI(dni) == false){
			Avisos.avisosFichero("DNI incorrecto");
			return;
		}

		
		if(perfil2.equals("alumno")){
			String[] lineaDatos2=arrayDatos[4].trim().split("\\s+");
			if(lineaDatos2.length!=2){
				Avisos.avisosFichero("Numero de parametros incorrecto");
				return;
			}
			Calendar fechaNac= Util.PasarACalendar(lineaDatos2[0].trim());
			if(!Avisos.ComprobarFecha(fechaNac)){
				Avisos.avisosFichero("Fecha incorrecta");
				return;
			}
			
			Calendar fechaIng=Util.PasarACalendar(lineaDatos2[1].trim());
			if(!Avisos.ComprobarFecha(fechaIng)){
				Avisos.avisosFichero("Fecha incorrecta");
				return;
			}
			
			if(!Avisos.ComprobarFechaIngreso(fechaNac, fechaIng)){
				Avisos.avisosFichero("Fecha ingreso incorrecta");
				return;
			}
			
			if(Proyecto.mapAlumnos.get(dni) != null){
				Avisos.avisosFichero("Alumno ya existente");
				return;
			}
			
			Proyecto.mapAlumnos.put(dni, new Alumno(nombre, apellidos, dni, 
				fechaNac, perfil2, fechaIng));
		   	
		}else {
			String[] lineaDatos2=arrayDatos[4].trim().split("\\s+");
			if(lineaDatos2.length!=2){
				Avisos.avisosFichero("Numero de argumentos incorrecto");
				return;
			}
				Calendar fechaNac= Util.PasarACalendar(lineaDatos2[0].trim());
			
			if(!Avisos.ComprobarFecha(fechaNac)){
				Avisos.avisosFichero("Fecha incorrecta");
				return;
			}
			String categoria = lineaDatos2[1].trim();
			String[] lineaDatos3 = arrayDatos[5].trim().split("\\s+");
			String departamento = lineaDatos3[0];
			String[] lineaDatos4 = arrayDatos[6].trim().split("\\s+");
			int horasAsignables = Integer.parseInt(lineaDatos4[0]);
			
			if(!Avisos.ComprobarHorasAsig(horasAsignables, categoria)){
				Avisos.avisosFichero("Numero de horas incorrecto");
				return;
			}
			
			if(Proyecto.mapProfesores.get(dni) != null){
				Avisos.avisosFichero("Profesor ya existente");
				return;
			}
			
			
			Proyecto.mapProfesores.put(dni, new Profesor(nombre, apellidos, dni, 
				fechaNac, perfil2, categoria, departamento, horasAsignables));
			
		}
		
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
	

	//Comprobado
	public static void cargaPersonasAMapa(String nombreArchivo) throws IOException{
		
		try {
			Scanner input = new Scanner(new FileInputStream(nombreArchivo));
			
			while(input.hasNextLine()){
					
					//Variables que tienen todas las personas
					String perfil = input.nextLine().trim();
					String dni = input.nextLine().trim();
					String nombre = input.nextLine().trim();
					String apellidos = input.nextLine().trim();
					String aux = input.nextLine().trim();
					Calendar FechaNacimiento = Util.PasarACalendar(aux);
					
					//Ahora vamos con las variables particulares de alumno y profesor
						if(perfil.trim().equals("alumno")){
						String aux1 = input.nextLine().trim();
						Calendar fechaIng = Util.PasarACalendar(aux1);
						
						String AsignaturasSuperadas = input.nextLine().trim();
						String DocenciaRecibida = input.nextLine().trim();
						
						Alumno cargaAlumno = new Alumno(nombre.trim(), apellidos.trim(),dni.trim(), FechaNacimiento, fechaIng, 
								AsignaturasSuperadas, DocenciaRecibida);
						//Anhade el nuevo alumno leido del fichero al mapa de alumnos
						Proyecto.mapAlumnos.put(dni, cargaAlumno);
						
						//Para saltar el asterisco
						if(input.hasNextLine()){
							input.nextLine();
						}
						
					}
						if(perfil.trim().equals("profesor")){
						String Categoria = input.nextLine().trim();
						String Departamento = input.nextLine().trim();
						int HorasAsignables = Integer.parseInt(input.nextLine().trim() );
						String DocenciaImpartida = input.nextLine().trim();
						
						Profesor cargaProfesor = new Profesor(dni.trim(), nombre.trim(), apellidos.trim(), FechaNacimiento,
								Categoria, Departamento, HorasAsignables, DocenciaImpartida);
						//Carga profesor a mapa
						Proyecto.mapProfesores.put(dni, cargaProfesor);
						
						if(input.hasNextLine()){
							input.nextLine();
						}
					}
				}
				
			//Cierra el while.
					
			input.close();
				
		}catch (FileNotFoundException e){
			  Avisos.avisosFichero("Error fichero: " +nombreArchivo);
			  System.exit(1);
		}
	}
	public static void ObtenerCalendarioClases(String[] arrayDatos) throws IOException{
		String dniProf=arrayDatos[1].trim();
		String nombrefichero=arrayDatos[2].trim();
		if(arrayDatos.length!=3){
			Avisos.avisosFichero("Numero de comandos Incorrecto");
			return;
		}
		if(Proyecto.mapProfesores.get(dniProf)==null){
			Avisos.avisosFichero("Profesor inexistente");
			return;
		}
		//solo falta crear un metodo en la clase profesor que se llame obtenerClasesProfesor que
		//recorre la lista docencia impartida para obtener las clases y los grupos y despues se llama a la funcion asi:
		// Proyecto.mapProfesores.get(dniProf).ObtenerClasesProfesor(nombrefichero);
		return;
	}
	public String toString(){
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy"); 
		return DNI+"\n"+Nombre+"\n"+Apellidos+"\n"+sdf.format(FechaNacimiento.getTime());
		
	}
	
	
}

