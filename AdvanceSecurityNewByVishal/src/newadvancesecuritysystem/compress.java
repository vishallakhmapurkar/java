package newadvancesecuritysystem;

import java.util.zip.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import javax.swing.*;

public class compress extends JFrame{

public JFileChooser choose;
private File file;
private String name,s;
private String path,name1;
   public compress()
       {
         try {
               open();
               String inFilename =  path;
               System.out.println(inFilename);
               JFileChooser ch=new JFileChooser();
               ch.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );

               int result = ch.showSaveDialog(this);
               if ( result == ch.CANCEL_OPTION )
                  return;
                else
                {
                  file= choose.getSelectedFile();
                  name1 =(file+".zip");
                }
                FileInputStream in = new FileInputStream(inFilename);
                ZipOutputStream out = new ZipOutputStream(new FileOutputStream(name1));

                // Add ZIP entry to output stream.
                out.putNextEntry(new ZipEntry(inFilename));

                byte[] buf = new byte[1024];
                int len,tlen;

                while ((len = in.read(buf)) > 0)
                {
                      out.write(buf, 0, len);
                }
                System.out.println(name1);
                out.closeEntry();
                out.close();
                in.close();
            } catch (IOException e) {}

}


   public static void main(String arg[])
	{

      compress z=new compress();
      z.addWindowListener(
   	 		new WindowAdapter() {
   	 			public void windowClosing(WindowEvent e)
   	 			{
   	 				System.exit(0);
   	 			}
   	 		                    }
   	 		           );

         }


 	public void open()
     {
       choose = new JFileChooser();
       choose.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
       int result = choose.showOpenDialog(this);


      if ( result == choose.CANCEL_OPTION )
         return;
       else
          {
          	name = new String(" ");
            file= choose.getSelectedFile();
            path = file.getPath ();
            System.out.println(path);
            name= file.getName();

          }
       }
  }