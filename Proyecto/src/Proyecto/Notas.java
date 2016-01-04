package Proyecto;

import java.util.Comparator;

public class Notas implements Comparable<Notas> {
	
	private float nota;
	private String anhoAcademico;
	private int curso;
	private String nombreAsig;
	
	
	public Notas(float nota, String anhoAcademico){
		this.nota = nota;
		this.anhoAcademico = anhoAcademico;
	}
	public float getNota(){
		return nota;
	}	
	public String getAnhoAcademico(){
		return anhoAcademico;
	}
	public int getCurso() {
		return curso;
	}
	public void setCurso(int curso) {
		this.curso = curso;
	}
	public String getNombre() {
		return nombreAsig;
	}
	public void setNombreAsig(String nombre) {
		this.nombreAsig = nombre;
	}
	public int compareTo(Notas nota) {
		if(curso<nota.getCurso())
			return -1;
		if(curso>nota.getCurso())
			return 1;
		return 0;
	}	
	
}
//Similar al de practica (vuelos)
class comparaPorNombre implements Comparator<Notas>{
	public int compare(Notas nota1, Notas nota2){
		return nota1.getNombre().compareTo(nota2.getNombre());
	}
}
