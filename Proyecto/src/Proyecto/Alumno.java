package Proyecto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;


public class Alumno extends Persona {  
	
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
			if(Grupos.size() == 0){
				if(ponPuntoComa)
					docencia += "; " +key;
				else 
					docencia += key; 
			}
			for(int i=0; i<Grupos.size(); i++){
				if(ponPuntoComa)
					docencia += "; ";
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
	    if(Proyecto.mapAlumnos.get(dni).ComprobarSiMatriculado(idSiglas)){
	    	Avisos.avisosFichero("Ya es alumno de la asignatura indicada");
	    	return;
	    }
		if(!Avisos.comprobarPrerrequisitos(Proyecto.mapAsignaturas.get(idSiglas), Proyecto.mapAlumnos.get(dni))){
			Avisos.avisosFichero("No cumple requisitos");
			return;
		}
		//Anhadir a la docencia recibida
		Proyecto.mapAlumnos.get(dni).getDocenciaRecibida().put(idSiglas, new Asignatura(idSiglas));
		Avisos.avisosFichero("OK");   
	}   
	
	//Comprobaciones necesarias en alumnos porque necesitan la docencia recibida de cada objeto Alumno
	public boolean ComprobarSiMatriculado(int idAsignatura){
		boolean retorno=true;
		if(DocenciaRecibida.get(idAsignatura) == null){
			retorno = false;
			return retorno;
		}
		return retorno;
	}
	public boolean ComprobarSiAprobado(int idAsignatura){
		boolean retorno=true;
		if(AsignaturasSuperadas.get(idAsignatura) == null){
			retorno = false;
			return retorno;
		}
		return retorno;
	}
	public static void asignarGrupo(String[] arrayDatos)throws IOException{
		if(arrayDatos.length!=5){
			Avisos.avisosFichero("Numero de comandos incorrecto");
			return;
		}
		String siglas = arrayDatos[2].trim();
		String dni = arrayDatos[1].trim();
		String tipoGrupo=arrayDatos[3].trim();
		String idGrupo= arrayDatos[4].trim();
		int idSiglas = Util.PasarSiglasAId(siglas);
		
		if(Proyecto.mapAlumnos.get(dni)==null){
			Avisos.avisosFichero("Alumno inexistente");
			return;
		}
		if(Proyecto.mapAsignaturas.get(idSiglas)==null){
			Avisos.avisosFichero("Asignatura inexistente");
			return;
		}
		
		if(!Proyecto.mapAlumnos.get(dni).ComprobarSiMatriculado(Proyecto.mapAsignaturas.get(idSiglas).getIdAsignatura())){
			Avisos.avisosFichero("Alumno no matriculado");
			return;
		} 
		
		if(!Avisos.comprobarTipoGrupo(tipoGrupo)){
			Avisos.avisosFichero("Tipo de grupo incorrecto");
			return;
		} 
		
		if(!Proyecto.mapAsignaturas.get(idSiglas).comprobarGrupo(Integer.parseInt(idGrupo),
				tipoGrupo.toCharArray()[0],idSiglas)){
			Avisos.avisosFichero("Grupo Inexistente");
			return;
		}
		
		
		if(Avisos.haySolapeEnAlumno(Proyecto.mapAlumnos.get(dni), Proyecto.mapAsignaturas.get(idSiglas),
				tipoGrupo.toCharArray()[0], Integer.parseInt(idGrupo))){
			Avisos.avisosFichero("Se genera solape");
			return;
		} 
		
		
		Proyecto.mapAlumnos.get(dni).asignarGrupo(Proyecto.mapAsignaturas.get(idSiglas),
		tipoGrupo.toCharArray()[0], Integer.parseInt(idGrupo));
		
		Avisos.avisosFichero("OK");
		
	}
	
	public void asignarGrupo(Asignatura asignatura, char tipoGrupo, int idGrupo) {
		DocenciaRecibida.get(asignatura.getIdAsignatura()).anhadeGrupo(idGrupo, tipoGrupo);
		return;
		
	}
	public LinkedHashMap<Integer, Asignatura> getDocenciaRecibida(){
		return DocenciaRecibida;
	}
	public LinkedHashMap<Integer, Notas> getAsignaturasSuperadas(){
		return AsignaturasSuperadas;
	}
	public boolean horarioSolapeAlumno(int horaInicio, int horaFin, char dia) {
		boolean retorno= false;
		Set<Integer> claves = DocenciaRecibida.keySet();
		for(int key:claves){
			ArrayList<Grupos> gruposAlumno = DocenciaRecibida.get(key).getGrupos();
			ArrayList<Grupos> grupos = Proyecto.mapAsignaturas.get(key).getGrupos();
			for(int i=0; i<gruposAlumno.size(); i++){
				for(int j=0; j<grupos.size(); j++){
					if(gruposAlumno.get(i).getTipoGrupo()==grupos.get(j).getTipoGrupo()&&gruposAlumno.get(i).getIdGrupo()==grupos.get(j)
							.getIdGrupo()){
						if(grupos.get(j).getDia()!=dia)
							continue;
						else{
							if(horaInicio-grupos.get(j).getHoraFin()<0 || grupos.get(j).getHoraInicio()==horaInicio){
								retorno = true;
								break;
							}
						}
					}
				}
			}
			if(retorno==true){
				break;		
			}
		}
	return retorno;
	}
	
    public static void ObtenerExpediente(String[] arrayDatos) throws IOException{
		if(arrayDatos.length != 3){
			Avisos.avisosFichero("Numero de parametros incorrecto");
			return;
		}
		String dni = arrayDatos[1].trim();
		String output = arrayDatos[2].trim();		
    	if(Proyecto.mapAlumnos.get(dni) == null){
    		Avisos.avisosFichero("Alumno inexistente");
    		return;
    	}	
    	if(Proyecto.mapAlumnos.get(dni).getAsignaturasSuperadas().size() == 0){
    		Avisos.avisosFichero("Expediente vacio");
    		return;
    	}
    	Proyecto.mapAlumnos.get(dni).cargaExpediente(output);
    	Avisos.avisosFichero("OK");
    	
    }
	private void cargaExpediente(String output) throws IOException {
		  File f=new File(output);
		  BufferedWriter bufer = new BufferedWriter(new FileWriter(f));
		  ArrayList<Notas> notas = new ArrayList<Notas>(AsignaturasSuperadas.values());
		  Set<Integer> clave = AsignaturasSuperadas.keySet();
		  for(int key:clave){
				AsignaturasSuperadas.get(key).setCurso(Proyecto.mapAsignaturas.get(key).getCurso());
				AsignaturasSuperadas.get(key).setNombreAsig(Proyecto.mapAsignaturas.get(key).getNombreAsignatura());
		  }
		  Collections.sort(notas);  
		  Collections.sort(notas, new comparaPorNombre());
		  for (int i = 0; i < notas.size(); i++) {
			  bufer.write(notas.get(i).getCurso()+" "+notas.get(i).getNombre()+" "+ notas.get(i).getNota()+" "
						+notas.get(i).getAnhoAcademico()+"\n");
		  }
		  
		  
		  float notaMedia = 0;
		  for(int j=0; j<notas.size(); j++){
			   notaMedia += notas.get(j).getNota();
		  }
		  notaMedia = notaMedia/notas.size();
		  bufer.write(" Nota media: " +notaMedia);
		  	  
		  bufer.close();	  
	}
	//Necesario implementar metodo abstracto de la interfaz comparable
	public int compareTo(Object o) {
		return 0;
	}
	public void evaluar(int idSiglas, Notas notas) {
		if(notas.getNota()>=5){
			AsignaturasSuperadas.put(idSiglas, notas);
			DocenciaRecibida.remove(idSiglas);
		}
	return;
	}

 }
	
