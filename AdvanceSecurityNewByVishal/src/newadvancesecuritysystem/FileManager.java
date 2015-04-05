package newadvancesecuritysystem;

import java.io.*;

class FileManager
{
	public FileManager()
	{
	}//constructor
	
	public void saveFile(Object object, String filename)
	{
		try 
		{
				File outFile = new File(filename);
				FileOutputStream outFileStream = new FileOutputStream(outFile);
				ObjectOutputStream outObjectStream = new ObjectOutputStream(outFileStream);
				outObjectStream.writeObject(object);
				outObjectStream.close();
		}//try			
		catch(IOException e)
		{
			System.out.println(e);
			object = null;
		}//catch		
	}//saveFile
		
	public Object loadFile(String filename)
	{
		Object object;
		
		try
		{
				File inFile = new File(filename);
				FileInputStream inFileStream = new FileInputStream(inFile);
				ObjectInputStream objectInputStream = new ObjectInputStream(inFileStream);
				object = objectInputStream.readObject();
				objectInputStream.close();
		}//try
		catch(Exception e)
		{
			System.out.println(e);
			object = null;
		}//catch
		
		return object;
	}//loadFile
}//class