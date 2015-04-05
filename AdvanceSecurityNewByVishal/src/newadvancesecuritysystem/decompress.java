package newadvancesecuritysystem;

import java.util.zip.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;

public class decompress extends JFrame{
      public File file;
      public JTextField at ,text2;
      public JFileChooser choose;
      public static String name;
      
  public decompress()
  {
    try {
         saved();
         String inFilename = name;
         String outFilename = smr(name);

         ZipInputStream in = new ZipInputStream(
           new FileInputStream(inFilename));
         OutputStream out = new FileOutputStream(
           outFilename);

         ZipEntry entry;
         byte[] buf = new byte[1024];
         int len;

           if ((entry = in.getNextEntry()) != null) {
               while ((len = in.read(buf)) > 0) {
                   out.write(buf, 0, len);
               }
           }
         out.close();
         in.close();
     } catch (IOException e) {
     }

}





 public void saved()
     {
     choose = new JFileChooser();
              int result = choose.showOpenDialog( this );

      // user clicked Cancel button on dialog
      if ( result == choose.CANCEL_OPTION )
         return;
       else
          {
           file= choose.getSelectedFile();
           name = file.getPath();         

                    }
       }


   public static void main(String args[])
       {
         decompress f=new decompress();
         f.addWindowListener(
         new WindowAdapter() {
            public void windowClosing( WindowEvent e )
            {
               System.exit(0);
            }
         }
      );
      }
      
      
  public String smr(String input)
   {
 
  String out=new String("");
  int i=0,j=0;
  int temp=0;
  char a[];

  i=input.length ();
  a= input.toCharArray ();
  for(j=0;j<i;j++)
  {
   if(a[j]=='.')
   {
 	if(temp==1)
 	{
     a[j]='\0';
     break;
    }
   else
   {
    temp=temp+1;
     out=out+a[j];
    }
   }
 else
   {

    out = out+a[j];
   }
 }

 System.out.println (out);
  return out;
 }
}
