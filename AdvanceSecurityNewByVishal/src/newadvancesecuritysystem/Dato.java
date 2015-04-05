package newadvancesecuritysystem;

import java.util.*;

class Dato
{
	private Dato()
	{
	}
	
	public static String getDato()
	{
		Calendar dato = Calendar.getInstance();
	
		String dag = String.valueOf(dato.get(Calendar.DATE));
		if (dag.length() <= 1)
			dag = "0" + dag;
		
		String maaned = String.valueOf((dato.get(Calendar.MONTH)+1));
		if (maaned.length() <= 1)
			maaned = "0" + maaned;
		
		String aar = String.valueOf(dato.get(Calendar.YEAR)).substring(2,4);
		
		return (dag + "-" + maaned + "-" + aar);
	}
	
	public static String getTid()
	{
		Calendar dato = Calendar.getInstance();
		
		String tid = "";
		if(dato.get(Calendar.AM)>0)
			tid = "" + (dato.get(Calendar.HOUR)+12);
		else
			tid = "" + dato.get(Calendar.HOUR);

		if(dato.get(Calendar.MINUTE)>9)	
			tid = tid + ":" + dato.get(Calendar.MINUTE);
		else
			tid = tid + ":0" + dato.get(Calendar.MINUTE);
		
		if(dato.get(Calendar.SECOND)>9)
			tid = tid + ":" + dato.get(Calendar.SECOND);
		else
			tid = tid + ":0" + dato.get(Calendar.SECOND);
		return tid;
	}
}
