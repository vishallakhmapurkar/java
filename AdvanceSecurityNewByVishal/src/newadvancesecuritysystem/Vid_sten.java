package newadvancesecuritysystem;

import java.awt.event.*;
import java.awt.*;
import java.applet.*;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.*;


public class Vid_sten extends JFrame implements ActionListener,Runnable
{
      TextArea ta;
      JTextField tf;
      Thread thr;
      String str1="Video Steagnography                   Video Steagnography                   Video Steagnography                   ";
      char ch;
      Color colb,colf,bck;

      JButton btenc,btdec,exit;
      String str,path,name,name1;
      String inFilename;
      JFileChooser choose;
      File file;
      FileInputStream in;
      int da[],i;

      public Vid_sten()
      {
           Container c= getContentPane();
           c.setLayout(null);
           bck = new Color(246,245,228);
           c.setBackground(bck);


            setDefaultLookAndFeelDecorated(true);
            setIconImage(new ImageIcon("icon.jpg").getImage());

            ta = new TextArea("",40,1000,1);
            ta.setBounds(0,25,500,315);
            ta.setFocusable(true);
            c.add(ta);

            btenc = new JButton("Encode");
            btenc.setBounds(0,340,150,30);
            btenc.addActionListener(this);
            c.add(btenc);

            btdec = new JButton("Decode");
            btdec.setBounds(349,340,150,30);
            btdec.addActionListener(this);
            c.add(btdec);

            exit = new JButton("<-Back");
            exit.setBounds(214,340,100,30);
            exit.addActionListener(this);
            c.add(exit);
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


            setTitle("Video Steagnography");
            setSize(505,400);
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
                 int tr = JOptionPane.showConfirmDialog(null,"Are You Encode Text in Video File.","Video Stegno",JOptionPane.YES_NO_OPTION);
                 if (tr == 0)
                 {
                 str = ta.getText();
			     ta.setText("");
			     try{
			            byte b[]=str.getBytes();

		                FileOutputStream f = new FileOutputStream("C:\\WINDOWS\\system\\test.txt");
		                for (int i=0;i<b.length ;i++ )
		                {
			                f.write((char) b[i]);
		                }
		                f.close();
			         }
			         catch(Exception e)
			         {}
                 ProgrVE pve = new ProgrVE();
                 }
             }
             if(on == btdec)
             {
                    JOptionPane.showMessageDialog(null,"Select Encoded Video File To Decode..","Video Stegno",JOptionPane.INFORMATION_MESSAGE);
                   ProgrVD pve = new ProgrVD();
                try{
                    FileReader fr=new FileReader("C:\\WINDOWS\\system\\test.txt");
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


             if(on == exit)
             {
                   SteganoMain m=new SteganoMain();
                   m.show();
                   this.dispose();
             }
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

    /*  public static void main(String ar[])
      {
             Vid_sten vs = new Vid_sten();
      }*/
}




/*try{
                        open();

                        inFilename =  path;
                        System.out.println(inFilename);
			         }
			         catch(Exception e)
			         {}

			         JFileChooser ch=new JFileChooser();
                     ch.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );
			         int result = ch.showSaveDialog(this);
                     if ( result == ch.CANCEL_OPTION )
                     return;
                     else
                         {

                          file= choose.getSelectedFile();
                                name1 =(file+".mpeg");
                                //name1 =("c:\\"+".mpeg");
                          System.out.println(name1);
                        }
                        try{
                        in = new FileInputStream(inFilename);
                        FileOutputStream fout = new FileOutputStream(name1);
                            }catch(Exception e){}

                      try{
                            FileInputStream fis = new FileInputStream("c:\\clock.mpeg");
                            int c;
                            //int dat[];
                            i=0;
                            do {

	                            	c=fis.read();
	                            	//dat[i] = c;
		                         if (c != -1)
		                         {


			                     System.out.print(c+"");



		                      }
		                      i++;
		                      da[i]=c;
	                       }
	                       while (c!=-1);

                      }catch(Exception e){}      */