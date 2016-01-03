package Proyecto;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Grupos {
	private char TipoGrupo;
	private int IdGrupo;
	private char Dia;
	private Calendar HoraInicio= Calendar.getInstance();
	private Calendar HoraFin= Calendar.getInstance();
	
	public Grupos (char TipoGrupo, int IdGrupo){
		this.TipoGrupo = TipoGrupo;
		this.IdGrupo = IdGrupo;
	}
	public Grupos(char TipoGrupo, int IdGrupo, char Dia, String HoraInicio, String HoraFin){
		int Horainic=Integer.parseInt(HoraInicio);
		int Horafinal=Integer.parseInt(HoraFin);
		
		this.TipoGrupo=TipoGrupo;
		this.IdGrupo=IdGrupo;
		this.Dia=Dia;
		this.HoraFin.set(2010, 3, 3,Horafinal,0,0);
		this.HoraInicio.set(2010, 3, 3,Horainic, 0, 0);
	}

	public char getTipoGrupo(){
		return TipoGrupo;
	}
	public int getIdGrupo(){
		return IdGrupo;
	}
	public char getDia(){
		return Dia;
	}
	public int getHoraInicio(){
    SimpleDateFormat date = new SimpleDateFormat("HH");
	String auxInic=date.format(HoraInicio);
	return Integer.parseInt(auxInic);
	}
	public int getHoraFin(){
		SimpleDateFormat date1 = new SimpleDateFormat("HH");
			String auxFin=date1.format(HoraFin);
			return Integer.parseInt(auxFin);		
		}
	public int getDuracion(){
		SimpleDateFormat date2 = new SimpleDateFormat("HH");
		String auxInic=date2.format(HoraInicio);
		String auxFin=date2.format(HoraFin);
		return (Integer.parseInt(auxFin)-Integer.parseInt(auxInic));
				
	}
	public String toString(){
		return (Integer.toString(IdGrupo) +" " +TipoGrupo);
	}
}
