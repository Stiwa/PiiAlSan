package Proyecto;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Set;


public class Alumno extends Persona {  //Falta a�adir interfaz comparable
	
	private Calendar FechaIngreso = Calendar.getInstance();
	//private String AsignaturasSuperadas;
	private LinkedHashMap<Integer, Asignatura> DocenciaRecibida = new LinkedHashMap<Integer, Asignatura>();
	private LinkedHashMap<Integer, Notas> AsignaturasSuperadas = new LinkedHashMap<Integer, Notas>();
	
	
	public Alumno(){
		
	}
	public Alumno(String Nombre, String Apellidos, String DNI, Calendar FechaNacimiento, String Perfil){
		
		super(Nombre, Apellidos,DNI,FechaNacimiento,Perfil);
	}
	public Alumno(String Nombre, String Apellidos, String DNI, 
			Calendar FechaNacimiento, String Perfil, Calendar FechaIngreso){
		
		super(Nombre, Apellidos,DNI,FechaNacimiento,Perfil);
		//el super al derivar de persona tiene que llevar dentro de el los parametros que recibe de persona
		this.FechaIngreso= FechaIngreso;
		
	}
	//Constructor necesario para cargar la lista de personas en los mapas.
	public Alumno(String Nombre, String Apellidos, String DNI, Calendar FechaNacimiento, 
			Calendar FechaIngreso, String AsignaturasSuperadas, String DocenciaRecibida){
		
		super(Nombre, Apellidos,DNI, FechaNacimiento);
		this.FechaIngreso = FechaIngreso;
		
		
		if(AsignaturasSuperadas.length() !=0){
			String grupoDatos[] = AsignaturasSuperadas.split(";");
			for(int i=0;i<grupoDatos.length;i++){
				String PartesDatos[]=grupoDatos[i].trim().split("\\s+");
				this.AsignaturasSuperadas.put(Integer.parseInt(PartesDatos[0].trim()),new Notas(Float.parseFloat(PartesDatos[2])
						,PartesDatos[1]));
				
			}
		}
		
		
		
		if(DocenciaRecibida.length()!=0){
			String grupoDatos[] = DocenciaRecibida.split(";");
			
			for(int i=0; i<grupoDatos.length; i++) {
				String arrayDocente[] = grupoDatos[i].trim().split(" ");
				
				//Para el caso de cuando no esta asignado ningun grupo de teoria o de practica
				if(arrayDocente.length==1){
					//Hay que crear un objeto asignatura pero con solo el dato del id, ya que no tiene grupos asignados
					Asignatura a = new Asignatura(Integer.parseInt(arrayDocente[0])); 
					this.DocenciaRecibida.put(Integer.parseInt(arrayDocente[0]), a);
				}
				else {
					if(this.DocenciaRecibida.get(Integer.parseInt(arrayDocente[0]))!=null){
						Grupos g = new Grupos(arrayDocente[1].toCharArray()[0], Integer.parseInt(arrayDocente[2]));
						this.DocenciaRecibida.get(Integer.parseInt(arrayDocente[0])).getGrupos().add(g);
					}
					else {
						Asignatura a = new Asignatura(Integer.parseInt(arrayDocente[0]),Integer.parseInt(arrayDocente[2]), 
								arrayDocente[1].toCharArray()[0]);
						
						this.DocenciaRecibida.put(Integer.parseInt(arrayDocente[0]), a);
					}
				}
			}//Cierra el for
			
		}
		
		
	}
	
	
	public String DocenciaRecibidaToString(){
		String docencia ="";
		boolean ponPuntoComa = false;
		Set<Integer> clave = DocenciaRecibida.keySet();
		for(int key:clave){
			ArrayList<Grupos> Grupos = DocenciaRecibida.get(key).getGrupos();
			for(int i=0; i<Grupos.size(); i++){
				if(ponPuntoComa)
					docencia += ";";
				docencia += key +" " +Grupos.get(i).getTipoGrupo() +" " +Integer.toString(Grupos.get(i).getIdGrupo());
				ponPuntoComa = true;
			}
		}
		
		return docencia;
	}
	
	public String AsignaturasSuperadasToString(){
		String AsigSup = "";
		boolean ponPuntoComa = false;
		Set<Integer> clave = AsignaturasSuperadas.keySet();
		for(int key:clave){
			if(ponPuntoComa)
				AsigSup += ";";
			
			AsigSup += Integer.toString(key) +" " +AsignaturasSuperadas.get(key).getAnhoAcademico() 
					+" " +AsignaturasSuperadas.get(key).getNota();
			ponPuntoComa = true;
		}
		return AsigSup;
	}
	
	
	public String toString(){

		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy"); 
		return (super.toString()+"\n"+sdf.format(FechaIngreso.getTime())+"\n" 
				+AsignaturasSuperadasToString() +"\n" +DocenciaRecibidaToString() +"\n");
	}
	
	
	public static void Matricula(String[] arrayDatos) throws IOException{
		
		if(arrayDatos.length != 3){
			Avisos.avisosFichero("Numero de parametros incorrecto");
			return;
		}
		
		String dni = arrayDatos[1].trim();
		String siglas = arrayDatos[2].trim();

    	if(Proyecto.mapAlumnos.get(dni) == null){
    		Avisos.avisosFichero("Alumno inexistente");
    		return;
    	}
	    int idSiglas = Util.PasarSiglasAId(siglas);
	    if(Proyecto.mapAsignaturas.get(idSiglas)==null){
	    	Avisos.avisosFichero("Asignatura inexistente");
    		return;
	    }
	    /*
	    if(!ComprobarMatricula(Proyecto.mapAlumnos.get(dni).DocenciaRecibida.values(),idSiglas)){
 	
    			Avisos.avisosFichero("Ya es alumno de la asignatura indicada");
    			return;
	    }*/
	    
	}   
	
	public boolean ComprobarMatricula(int idAsignatura){
		boolean retorno=true;
		
		if(DocenciaRecibida.get(idAsignatura) == null){
			retorno = false;
			return retorno;
		}
	
		return retorno;
	}
	
    		
 }
	

		
		
	
	
	


