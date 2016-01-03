package Proyecto;

public class Notas {
	
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
	
	
}
