package Proyecto;

import java.io.IOException;
import java.util.GregorianCalendar;

public class Persona {
	private String Nombre = new String();
	private String Apellidos = new String();
	private String DNI = new String();
	private GregorianCalendar FechaNacimiento = new GregorianCalendar();
	private String Perfil = new String();
	
	protected Persona(){
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
		//Comprobamos que el numero de parametros sea el correcto
		String[] arrayDatos = datos.trim().split("\"");
		if(arrayDatos.length!=7 && arrayDatos.length!=5){
			Avisos.avisosFichero("Numero de comandos incorrecto.");
			return;
		}
		if(Avisos.ComprobarDNI(arrayDatos[1]) == false){
			Avisos.avisosFichero("DNI incorrecto.");
		}
		
		String tipo = perfil.trim();
		if(tipo.equals("alumno")){
		   	//InsertaAlumno(datos);
			
			
		}else if(tipo.equals("profesor")){
			//InsertaProfesor(datos);
		}else
			Avisos.avisosFichero("Campo perfil incorrecto: <" +perfil +">");
		
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
	public static void InsertaPersona(String datos){
		System.out.println("funciona");
	}

}
//xx
