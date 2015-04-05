package newadvancesecuritysystem;

import java.awt.event.*;
import java.awt.*;
import java.applet.*;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.*;
import java.awt.event.*;
import java.awt.*;
import java.applet.*;
import java.io.*;
import javax.media.*;

public class Aud_sten extends JFrame implements ActionListener,Runnable
{
      TextArea ta;
      JTextField tf;
      Thread thr;
      String str1="Audio Steagnography                   Audio Steagnography                   Audio Steagnography                   ";
      char ch;
      JButton btenc,btdec,exit;
      Color colb,colf,bck;
      String str,path,name,name1;
      String inFilename;
      JFileChooser choose;
      File file;
      FileInputStream in;
      int da[],i;

      public Aud_sten()
      {
           Container c= getContentPane();
           c.setLayout(null);
           bck = new Color(246,245,228);
           c.setBackground(bck);

            setDefaultLookAndFeelDecorated(true);
            setIconImage(new ImageIcon("icon.jpg").getImage());

            tf = new JTextField(str1);
                tf.setBounds(0,0,593,25);
                tf.setHorizontalAlignment(JTextField.RIGHT);
                colb = new Color(242,239,206);
                colf = new Color(116,85,67);
                tf.setBackground(colb);
		        tf.setForeground(colf);
		        tf.setFont(new Font ("Copperplate Gothic Bold",1,20));

                tf.setEditable(false);
		        tf.setFocusable(false);

		        thr=new Thread(this," ");
		        thr.start();
		        c.add(tf);

		        ta = new TextArea("",40,1000,1);
            ta.setBounds(0,25,500,285);
            ta.setFocusable(true);
            c.add(ta);



            btenc = new JButton("Encode");
            btenc.setBounds(0,310,150,30);
            btenc.addActionListener(this);
            c.add(btenc);

            btdec = new JButton("Decode");
            btdec.setBounds(349,310,150,30);
            btdec.addActionListener(this);
            c.add(btdec);

            exit = new JButton("Back");
            exit.setBounds(214,310,70,30);
            exit.addActionListener(this);
            c.add(exit);

            setTitle("Audio Steagnography");
            setSize(505,372);
            setResizable(false);
		    setVisible(true);

            addWindowListener(new WindowAdapter()
							{public void windowClosing(WindowEvent e)
							{

								dispose();
								}

							});
       }
    public void run()
    {
           while(true)
		        {

			    try
			    {

				 ch=str1.charAt(0);
				 str1=str1.substring(1,str1.length());
				 str1=str1+ch;
				 Thread.sleep(155);
				 tf.setText(str1);

			    }
			    catch (Exception e)
			    {
			    }

		    }
    }
      public void actionPerformed(ActionEvent ae)
      {
             Object on =  ae.getSource();
             if(on == btenc)
             {
                 int tr = JOptionPane.showConfirmDialog(null,"Are You Encode Text in Audio File.","Audio Stegno",JOptionPane.YES_NO_OPTION);
                 if (tr == 0)
                 {
                    str = ta.getText();
			        ta.setText("");
			        try{
			            byte b[]=str.getBytes();

		                FileOutputStream f = new FileOutputStream("C:\\WINDOWS\\system\\ta.txt");
		                for (int i=0;i<b.length ;i++ )
		                {
			                f.write((char) b[i]);
		                }
		                f.close();
			         }
			         catch(Exception e)
			         {}
                     //AudioTabr mt = new AudioTabr();
                     ProgrAE prae = new ProgrAE();
                 }
             }
             if(on == btdec)
             {
                 JOptionPane.showMessageDialog(null,"Select Encoded Audio File To Decode..","Audio Stegno",JOptionPane.INFORMATION_MESSAGE);
                    ProgrAD prad = new ProgrAD();
                   //AudioTabr mt = new AudioTabr();
                   dec();
            }


             if(on == exit)
             {
                   SteganoMain m=new SteganoMain();
                   m.show();
                   this.dispose();
             }
      }
      public void dec()
      {
       try{

                    FileReader fr=new FileReader("C:\\WINDOWS\\system\\ta.txt");
		            BufferedReader br=new BufferedReader(fr);
		            String s;

		            while ((s=br.readLine())!=null)
		            {
                          //System.out.println(s);
		                  ta.append(s);
		            }

                   /*FileInputStream fis = new FileInputStream("C:\\WINDOWS\\system\\test.txt");
                   int c;
                   String cc;
	               do
	                {
		               c=fis.read();
		                if (c != -1)
		                {
			               System.out.print((char)c+"");
			               //c = (char)c;
			               //ta.append(cc);
			               ta.setText(c);
		                }
	                 }
	                 while (c!=-1);*/
	                 }catch(Exception e){}
      }


            public void open()
     {
       choose = new JFileChooser();
       choose.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
       int result = choose.showOpenDialog(this );


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



     /* public static void main(String ar[])
       {
              Aud_sten as = new Aud_sten();
       }*/
}



