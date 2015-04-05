package newadvancesecuritysystem;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.text.*;
import javax.swing.event.*;
import java.io.*;

public class Split extends JFrame
{
     private JButton splitbutton,joinbutton,ok,bckk;
     public JTextField at ,text2;
     private JRadioButton num,size;
     private ButtonGroup radioGroup;
     public JTextField text1;
     public File file;
     Color bck;
     public JLabel lb1,lb2,lb3,lb4;
     public JFileChooser choose;
     public static String name;
     public static int check=0;
     public static long  no,no2;

public Split()
{     super("FILE SPLITTER");

     Container c= getContentPane();
     c.setLayout(null);
      bck = new Color(246,245,228);
           c.setBackground(bck);
           setDefaultLookAndFeelDecorated(true);
            setIconImage(new ImageIcon("icon.jpg").getImage());
     splitbutton= new JButton("Select");
     splitbutton.setToolTipText("Select file you want to split...");
     splitbutton.setBounds(20,220,70,30);
     c.add(splitbutton);
     joinbutton = new JButton("JOIN");
     joinbutton.setBounds(90,220,70,30);
     c.add(joinbutton);


     lb4 = new JLabel("Option Select ");
     lb4.setBounds(20,150,100,20);
     c.add(lb4);
     num=new JRadioButton(" NUMBER");
     num.setBackground(bck);
     num.setBounds(120,150,120,20);
     size=  new JRadioButton("SIZE");
     size.setBackground(bck);
     size.setBounds(260,150,120,20);

     lb1 = new JLabel("Number of Files ");
     lb1.setBounds(20,20,100,20);
     c.add(lb1);
     text1=new JTextField(20);
     text1.setBounds(120,20,200,20);
     c.add(text1);
     lb2 = new JLabel("Size of Files ");
     lb2.setBounds(20,60,100,20);
     c.add(lb2);
     text2=new JTextField(20);
     text2.setBounds(120,60,200,20);
     c.add(text2);
     lb3 = new JLabel("File to Split ");
     lb3.setBounds(20,100,100,20);
     c.add(lb3);
     at=new JTextField(20);
     at.setBounds(120,100,200,20);
     c.add(at);
     ok = new JButton("Split");
     ok.setToolTipText("Split the File...");
     ok.setBounds(170,220,70,30);
     c.add(ok);
      bckk = new JButton("Back");
     bckk.setToolTipText("back to main window...");
     bckk.setBounds(240,220,70,30);
     c.add(bckk);




       num.enable(false);
       size.enable(false);
       text1.enable(false);
       text2.enable(false);
       at.enable(true);

       c.add(num);
       c.add(size);
setUndecorated(true);



      radioGroup= new ButtonGroup();
      radioGroup.add(num);
      radioGroup.add(size);
      setSize(340,300);
      setResizable(false);
      show();
      splitbutton .addActionListener (
      new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        saved();
        num.enable(true);
        size.enable(true);
      }
      }                      );


      ok.addActionListener (
      new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {    String siz,path;
            long k;
            if(check==3)
            {
             path=smr(name);
             join(path);}

           if(check==1)
           {
            siz=text1.getText();
            no=Integer.parseInt(siz);
            k=no2/no;
            split1(name,k);
              }
          else  {
                 if (check==2) {
                 siz=text2.getText();

                no=Integer.parseInt(siz);
                 split1(name,no);}
                   }
                  }
                }                     );

      num.addActionListener (
      new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
           text2.enable(false);
           text1.enable(true);
            check=1;


      }
      }                      );
      size.addActionListener (
      new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
           text1.enable(false);
            text2.enable(true);
            check=2;
      }
      }                      );

     joinbutton.addActionListener (
      new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {      check=3;
            saved();
            splitbutton.enable(false);


      }
      }                      );
       bckk.addActionListener (
      new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
          Main m=new Main();
          m.show();
          
D();

      }
      }                      );








       }

public void D()
{
  dispose();
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
           no2= file.length();
           at.setText(name);
                    }
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
          if(a[j]=='1')
          {

            a[j]='\0';
            break;
           }

          else
           {

            out = out+a[j];
             }
               }


         return out;
          }










     	public void join(String FilePath)
	{
		long leninfile=0,leng=0;
		int count=1,data=0;
		try
		{
			File filename=new File(FilePath);

			RandomAccessFile outfile = new RandomAccessFile(filename,"rw");
			while(true)
			{
				filename=new File(FilePath + count + ".sp");
				if (filename.exists())
				{
					RandomAccessFile infile = new RandomAccessFile(filename,"r");
					data=infile.read();
					while(data!=-1)
					{
						outfile.write(data);
						data=infile.read();
					}
					leng++;
					infile.close();
					count++;
				}
				else
				{
					break;
				}
			}
			outfile.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}





   /*  public static void main(String args[])
   	 {          Split dd=new Split();
   	 	   	 	dd.addWindowListener(
   	 		new WindowAdapter() {
   	 			public void windowClosing(WindowEvent e)
   	 			{
   	 				System.exit(0);
   	 			}
                                      }) ;
                                      }

*/





     public void split1(String FilePath,long splitlen)
       {
		long leninfile=0,leng=0;
		int count=1,data;
		try
		{
			File filename=new File(FilePath);
			RandomAccessFile infile = new RandomAccessFile(filename,"r");
			data=infile.read();
			while(data!=-1)
			{
				filename=new File(FilePath + count + ".sp");
				RandomAccessFile outfile = new RandomAccessFile(filename,"rw");
				while( data!=-1 && leng<splitlen)
				{
					outfile.write(data);
					leng++;
					data=infile.read();
				}
				leninfile+=leng;
				leng=0;
				outfile.close();
				count++;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

