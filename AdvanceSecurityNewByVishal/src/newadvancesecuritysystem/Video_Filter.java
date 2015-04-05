package newadvancesecuritysystem;

import java.io.*;


public class Video_Filter extends javax.swing.filechooser.FileFilter
{
	protected boolean isVideoFile(String ext)
	{
		return (ext.equals("mpeg"));//||ext.equals("png"));
	}

	public boolean accept(File f)
	{
	    if (f.isDirectory())
	    {
			return true;
	    }

	    String extension = getExtension(f);
		if (extension.equals("mpeg"));//||//extension.equals("png"))
		{
			return true;
		}
		//return false;
	}

	public String getDescription()
	{
		return "Supported Video Files";
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