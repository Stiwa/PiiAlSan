package Proyecto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
/**
 * Clase grupos
 *
 *
 */
public class Grupos implements Comparable<Grupos>{
	private char TipoGrupo;
	private int IdGrupo;
	private char Dia;
	private Calendar HoraInicio= Calendar.getInstance();
	private Calendar HoraFin= Calendar.getInstance();
	
	/**
	 * Constructor grupos
	 * @param TipoGrupo
	 * @param IdGrupo
	 */
	public Grupos (char TipoGrupo, int IdGrupo){
		this.TipoGrupo = TipoGrupo;
		this.IdGrupo = IdGrupo;
	}
	/**
	 * Constructor grupos
	 * @param TipoGrupo
	 * @param IdGrupo
	 * @param Dia
	 * @param HoraInicio
	 * @param HoraFin
	 */
	public Grupos(char TipoGrupo, int IdGrupo, char Dia, String HoraInicio, String HoraFin){
		int Horainic=Integer.parseInt(HoraInicio);
		int Horafinal=Integer.parseInt(HoraFin);
		
		this.TipoGrupo=TipoGrupo;
		this.IdGrupo=IdGrupo;
		this.Dia=Dia;
		this.HoraFin.set(2010, 3, 3,Horafinal,0,0);
		this.HoraInicio.set(2010, 3, 3,Horainic, 0, 0);
	}
	/**
	 * Devuelve la variable char TipoGrupo
	 * @return
	 */
	public char getTipoGrupo(){
		return TipoGrupo;
	}
	/**
	 * Devuelve la variable int IdGrupo
	 * @return
	 */
	public int getIdGrupo(){
		return IdGrupo;
	}
	/**
	 * Devuelve la variable char Dia
	 * @return
	 */
	public char getDia(){
		return Dia;
	}
	/**
	 * Devuelve la variable HoraInicio convertida a int
	 * @return
	 */
	public int getHoraInicio(){
    SimpleDateFormat date = new SimpleDateFormat("HH");
	String auxInic = date.format(HoraInicio.getTime());
	return Integer.parseInt(auxInic);
	}
	/**
	 * Devuelve la variable HoraFin convertida a int
	 * @return
	 */
	public int getHoraFin(){
		SimpleDateFormat date1 = new SimpleDateFormat("HH");
			String auxFin = date1.format(HoraFin.getTime());
			return Integer.parseInt(auxFin);		
		}
	/**
	 * Devuelve la variable int Duracion correspondiente a la resta de horaInicio y horaFin
	 * @return
	 */
	public int getDuracion(){
		SimpleDateFormat date2 = new SimpleDateFormat("HH");
		String auxInic = date2.format(HoraInicio.getTime());
		String auxFin = date2.format(HoraFin.getTime());
		return (Integer.parseInt(auxFin)-Integer.parseInt(auxInic));
				
	}
	/**
	 * Retorna los campos en una variable String
	 */
	public String toString(){
		return (Integer.toString(IdGrupo) +" " +TipoGrupo);
	}
	/**
	 * Retorna una variable String con los parametros IdGrupo,Dia,HoraInicio y HoraFin
	 * @return
	 */
	public String ficheroAsignaturas(){
		return (Integer.toString(IdGrupo) +" " +Dia +" " +Integer.toString(getHoraInicio()) +" " +Integer.toString(getHoraFin()));
	}
	/**
	 * CompareTo de los dias de la semana correspondientes a los grupos
	 */
	public int compareTo(Grupos grupos){
		if(getDia() == 'L' && (grupos.getDia() == 'M' || grupos.getDia() == 'X' || grupos.getDia() == 'J' ||
				grupos.getDia() == 'V'))
			return -1;
			else if(getDia()=='M'&&grupos.getDia()=='L')
					return 1;
			else if(getDia() == 'M' && (grupos.getDia() == 'X' || grupos.getDia() == 'J' || grupos.getDia() == 'V'))
				return -1;
			else if(getDia() == 'X' && (grupos.getDia() == 'M' || grupos.getDia() == 'L'))
				return 1;
			else if(getDia() == 'X' && (grupos.getDia() == 'J' || grupos.getDia() == 'V'))
				return -1;
			else if(getDia() == 'J' && (grupos.getDia() == 'M' || grupos.getDia() == 'L' || grupos.getDia() == 'X'))
				return 1;
			else if(getDia()=='J'&&grupos.getDia()=='V')
				return -1;
			else if(getDia() == 'V' && (grupos.getDia() == 'L' || grupos.getDia() == 'M' || grupos.getDia() == 'X' ||
					grupos.getDia() == 'J'))
				return 1;
			else
					return 0;
	}
}
/**
 * Clase Comparator para ordenar las horas de los grupos
 * 
 *
 */
class comparaPorHoras implements Comparator<Grupos>{
	public int compare(Grupos grupo1, Grupos grupo2){
		if(grupo2.getHoraInicio()>grupo1.getHoraInicio())
			return -1;
		else
			return 1;
	}
	
}
