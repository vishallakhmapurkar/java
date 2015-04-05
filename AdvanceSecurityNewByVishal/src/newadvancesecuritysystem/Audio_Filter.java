package newadvancesecuritysystem;

import java.io.*;


public class Audio_Filter extends javax.swing.filechooser.FileFilter
{
	protected boolean isAudioFile(String ext)
	{
		return (ext.equals("mp3"));//||ext.equals("png"));
	}

	public boolean accept(File f)
	{
	    if (f.isDirectory())
	    {
			return true;
	    }

	    String extension = getExtension(f);
		if (extension.equals("mp3"));//||//extension.equals("png"))
		{
			return true;
		}
		//return false;
	}

	public String getDescription()
	{
		return "Supported Audio Files";
	}

	protected static String getExtension(File f)
	{
		String s = f.getName();
		int i = s.lastIndexOf('.');
		if (i > 0 &&  i < s.length() - 1)
		  return s.substring(i+1).toLowerCase();
		return "";
	}
}