package newadvancesecuritysystem;

import java.io.*;
import java.util.Vector;

public class Log implements Serializable
{
	private File file;
	private FileOutputStream outFile;
	private PrintWriter outStream;
	private Vector vectorBesked;

	public Log()
	{
		file = new File("src/log/" + "log(" + Dato.getDato() + ").txt");
		vectorBesked = new Vector();
	}
	
	public void addToLog(String besked)
	{
		besked = "(" + Dato.getDato() + " " + Dato.getTid() + ") " + besked;
		
		vectorBesked.add(besked);
		
		try
		{
			outFile = new FileOutputStream(file);
			outStream = new PrintWriter(outFile);
		}
		catch(Exception e){ System.out.println(e); }
		
		for (int i=vectorBesked.size();i>0;i--)
			outStream.println(vectorBesked.elementAt(i-1));
			
		closeLog();
	}
	
	public void closeLog()
	{
		outStream.close();	
	}
}
