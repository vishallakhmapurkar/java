package newadvancesecuritysystem;

import java.awt.event.*;
import java.awt.*;
import java.applet.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.media.*;


class ProgrAD extends JFrame  implements Runnable
{

      int height=0,width=0;
      JProgressBar pb;
      Thread th;
      Aud_sten ass;
      AudioTabrD audd;
       boolean stp=false,aliv=false;
      public ProgrAD()
      {

        Container c= getContentPane();
        c.setLayout(null);
        c.setBackground(Color.white);
        pb = new JProgressBar();
        pb.setBounds(0,0,372,20);
        pb.setForeground(Color.orange);
        audd = new AudioTabrD();
         audd.openFile();
        c.add(pb);
        th= new Thread(this," ");
        th.start();
        stp=true;


        width=(Toolkit.getDefaultToolkit().getScreenSize().width -getSize().width)/3;
		height=(Toolkit.getDefaultToolkit().getScreenSize().height-getSize().height)/3;
		setLocation(width,height);

        setUndecorated(true);
        setSize(372,20);
		setVisible(true);
		setResizable(false);

      }
        public void run()
             {

                    int min = 0,max=100;
                    pb.setMinimum(min);
                    pb.setMaximum(max);
                    pb.setValue(min);
                    for(int x= min; x <= max; x++)
                    {
                                   pb.setValue(x);
                                   try{
                                            Thread.sleep(100);
                                   }catch(Exception e)
                                   {
                                   }
                            }
                            //System.out.println("Finished........");
                            this.dispose();
                            JOptionPane.showMessageDialog(null, "The Song was Decoded Successfully!","Success!", JOptionPane.INFORMATION_MESSAGE);
                            //ass.dec();
                            audd.createPlayer();
                            audd.show(true);
                   }
      public static void main(String ar[])
      {
       ProgrAD bg = new ProgrAD();
       }


}
class AudioTabrD extends JFrame
 {
	  Player player;
	  File file;
	 Audio_Filter af;
	 Thread th;

	 public AudioTabrD()
	 {
		 super( "Audio Steagno" );

		 setDefaultLookAndFeelDecorated(true);
         setIconImage(new ImageIcon("icon.jpg").getImage());


		 setSize( 500, 54);
	 }
	 public void openFile()
	 {
		 JFileChooser fileChooser = new JFileChooser();
         fileChooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
		 int result = fileChooser.showOpenDialog( this );

		 if ( result == JFileChooser.CANCEL_OPTION )
			 file = null;
		 else
			 file = fileChooser.getSelectedFile();
			        String ext  = Audio_Filter.getExtension(file);
					String name = file.getName();
					String path = file.getPath();
       System.out.println("OPEN");
	 }

	 public void createPlayer()
	 {
		 System.out.println("dds");
         if ( file == null )
			 return;

		 removePreviousPlayer();

		 try
		 {
			 player = Manager.createPlayer( file.toURL() );
			 player.addControllerListener( new EventHandler() );
			 player.start();
		 }
		 catch ( Exception e )
		 {
			 JOptionPane.showMessageDialog( this, "Invalid file or location", "Error loading file",
			 JOptionPane.ERROR_MESSAGE );
		 }
	 }

	 public void removePreviousPlayer()
	 {
		 if ( player == null )
			 return;

		 player.close();

		 Component visual = player.getVisualComponent();
		 Component control = player.getControlPanelComponent();

		 Container c = getContentPane();

		 if ( visual != null )
			 c.remove( visual );

		 if ( control != null )
			 c.remove( control );
	 }

	 public class EventHandler implements ControllerListener
	 {
		 public void controllerUpdate( ControllerEvent e )
		 {
			 if ( e instanceof RealizeCompleteEvent )
			 {
				 Container c = getContentPane();

				 Component visualComponent = player.getVisualComponent();

				 if ( visualComponent != null )
					 c.add( visualComponent, BorderLayout.CENTER );

				 Component controlsComponent = player.getControlPanelComponent();

				 if ( controlsComponent != null )
					 c.add( controlsComponent, BorderLayout.SOUTH );

				 c.doLayout();
			 }
		 }
	 }
 }