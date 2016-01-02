package Proyecto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Set;


public class Profesor extends Persona{
	
	private String Departamento = new String();
	private String Categoria = new String();
	private int HorasAsignables;
	//private String DocenciaImpartida = "";
	private LinkedHashMap<Integer,Asignatura> DocenciaImpartida= new LinkedHashMap<Integer,Asignatura>();
	
	
	public Profesor(){
	}
	public Profesor(String Nombre, String Apellidos, String DNI, 
		Calendar FechaNacimiento, String Perfil, String Categoria,String Departamento,int HorasAsignables ){
	
	super(Nombre,Apellidos,DNI,FechaNacimiento,Perfil);
	
	this.Categoria=Categoria;
	this.Departamento=Departamento;
	this.HorasAsignables=HorasAsignables;
	
	
	}
	
// ESTE CONSTRUCTOR DE PROFESOR ES EL DEL FICHERO DE EJECUCION PARA METERLO AL MAPA
	public Profesor(String DNI,String Nombre,String Apellidos,Calendar FechaNacimiento,String Categoria,
			String Departamento, int HorasAsignables){
		super(Nombre,Apellidos,DNI,FechaNacimiento);
	
		this.Categoria=Categoria;
		this.Departamento=Departamento;
		this.HorasAsignables=HorasAsignables;
	}
	//Necesario para cargar del fichero personas al mapa mapProfesores
	public Profesor(String DNI,String Nombre,String Apellidos,Calendar FechaNacimiento,String Categoria,
			String Departamento, int HorasAsignables, String DocenciaImpartida){

		super(Nombre,Apellidos,DNI,FechaNacimiento);	
		this.Categoria=Categoria;
		this.Departamento=Departamento;
		this.HorasAsignables=HorasAsignables;
		
		if(DocenciaImpartida.length()!=0){
			String grupoDatos[] = DocenciaImpartida.split(";");
			
			for(int i=0; i<grupoDatos.length; i++) {
				String arrayDocente[] = grupoDatos[i].trim().split(" ");
				
				//Para el caso de cuando no esta asignado ningun grupo de teoria o de practica
				if(arrayDocente.length==1){
					//Hay que crear un objeto asignatura pero con solo el dato del id, ya que no tiene grupos asignados
					Asignatura a = new Asignatura(Integer.parseInt(arrayDocente[0])); 
					this.DocenciaImpartida.put(Integer.parseInt(arrayDocente[0]), a);
				}
				else {
					if(this.DocenciaImpartida.get(Integer.parseInt(arrayDocente[0]))!=null){
						Grupos g = new Grupos(arrayDocente[1].toCharArray()[0], Integer.parseInt(arrayDocente[2]));
						this.DocenciaImpartida.get(Integer.parseInt(arrayDocente[0])).getGrupos().add(g);
					}
					else {
						Asignatura a = new Asignatura(Integer.parseInt(arrayDocente[0]),Integer.parseInt(arrayDocente[2]), 
								arrayDocente[1].toCharArray()[0]);
						
						this.DocenciaImpartida.put(Integer.parseInt(arrayDocente[0]), a);
					}
				}
			}//Cierra el for
			
		}
	}
	
	public String getCategoria(){
		return Categoria;
		
	}

	
	public static void AsignaCargaDocente(String[] arrayDatos) throws IOException{
		
		if(arrayDatos.length != 5){
			Avisos.avisosFichero("Numero de argumentos incorrecto");
			return;
		}
		
		String dni = arrayDatos[1].trim();
		if(Proyecto.mapProfesores.get(dni)==null){
			Avisos.avisosFichero("Profesor inexistente");
			return;
		}
		
		//anhadimos a docenciaImpartida la carga docente
		String siglas = arrayDatos[2].trim();
		int idSiglas = Util.PasarSiglasAId(siglas);
		if(Proyecto.mapAsignaturas.get(idSiglas)==null){
			Avisos.avisosFichero("Asignatura Inexistente");
			return;
		} 
		String tipoGrupo = arrayDatos[3].trim();
		if(!tipoGrupo.equalsIgnoreCase("A") && !tipoGrupo.equalsIgnoreCase("B")){
			Avisos.avisosFichero("Tipo de grupo incorrecto");
			return;
		}
		int idGrupo = Integer.parseInt(arrayDatos[4].trim());
		System.out.println(idGrupo +"\n" +tipoGrupo.charAt(0));
		
		if(!Avisos.ExistenciaGrupo(Proyecto.mapAsignaturas.get(idSiglas).getGrupos(), tipoGrupo.charAt(0), idGrupo )) {
			Avisos.avisosFichero("Grupo Inexistente");
			return;
		}
		
		if(Avisos.ComprobarGrupoAsignado(idSiglas, idGrupo, tipoGrupo.toCharArray()[0])){		
			Avisos.avisosFichero("Grupo ya asignado");
			return;
		}
		Avisos.avisosFichero("OK");

		/*
		mapaProfesores.get(comando[1]).addDocencia(Asignatura.siglasToIdentificador(mapaAsignaturas, comando[3]), Integer.parseInt(comando[4]),
				comando[3].toCharArray()[0]);
		
		Proyecto.mapProfesores.get(dni).anhadeDocencia()
				
		*/
		
		return;
	}
	

	public void ObtenerClasesProfesor(String Fichero){
		//	File f = new File();
		//	BufferedWriter salida = new BufferedWriter(new FileWriter(f));
		//Aqui hay que crear un for each con un mapa que recorra las docencias de cada profesor
			return;
		}
	/*
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
	}*/
	public String DocenciaImpartidaToString(){
		String docencia ="";
		boolean ponPuntoComa = false;
		Set<Integer> clave = DocenciaImpartida.keySet();
		for(int key:clave){
			ArrayList<Grupos> Grupos = DocenciaImpartida.get(key).getGrupos();
			for(int i=0; i<Grupos.size(); i++){
				if(ponPuntoComa)
					docencia += ";";
				docencia += key +" " +Grupos.get(i).getTipoGrupo() +" " +Integer.toString(Grupos.get(i).getIdGrupo());
				ponPuntoComa = true;
			}
		}
		
		return docencia;
	}
	
	
	public String toString(){
		
		return (super.toString()+"\n"+Categoria +"\n" +Departamento +"\n" +HorasAsignables +"\n" +DocenciaImpartidaToString()
		+"\n");
	}

	public LinkedHashMap<Integer, Asignatura> getDocenciaImpartida(){
		return DocenciaImpartida;
	}

}
	//xx

