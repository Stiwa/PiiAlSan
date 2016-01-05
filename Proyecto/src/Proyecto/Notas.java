package Proyecto;

import java.util.Comparator;

/**
 * Clase donde se van a comparar y obtener las notas de los alumnos
 *
 *
 */

public class Notas implements Comparable<Notas> {
	
	private float nota;
	private String anhoAcademico;
	private int curso;
	private String nombreAsig;
	
	/**
	 * Constructor notas
	 * @param nota
	 * @param anhoAcademico
	 */
	public Notas(float nota, String anhoAcademico){
		this.nota = nota;
		this.anhoAcademico = anhoAcademico;
	}
	/**
	 * Devuelve la variable float nota
	 * @return
	 */
	public float getNota(){
		return nota;
	}	
	/** 
	 * Devuelve la variable String anhoAcademico
	 * @return
	 */
	public String getAnhoAcademico(){
		return anhoAcademico;
	}
	/**
	 * Devuelve la variable int curso
	 * @return
	 */
	public int getCurso() {
		return curso;
	}
	/**
	 * setter de la variable curso
	 * @param curso
	 */
	public void setCurso(int curso) {
		this.curso = curso;
	}
	/**
	 * Devuelve la variabke String nombreAsig
	 * @return
	 */
	public String getNombre() {
		return nombreAsig;
	}
	/**
	 * Setter de la variable nombreAsig
	 * @param nombre
	 */
	public void setNombreAsig(String nombre) {
		this.nombreAsig = nombre;
	}
	/**
	 * Metodo que compara los cursos, para posteriormente ordenarlos con un collections.sort
	 */
	public int compareTo(Notas nota) {
		if(curso<nota.getCurso())
			return -1;
		if(curso>nota.getCurso())
			return 1;
		return 0;
	}	
	
}
/**
 * Clase que compara dos objetos notas para despues ordenarlos por nombre
 *
 *
 */
class comparaPorNombre implements Comparator<Notas>{
	public int compare(Notas nota1, Notas nota2){
		return nota1.getNombre().compareTo(nota2.getNombre());
	}
}
