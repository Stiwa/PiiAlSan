package Proyecto;

public class Notas {
	
	private float nota;
	private String anhoAcademico;
	
	
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
	
	
}
