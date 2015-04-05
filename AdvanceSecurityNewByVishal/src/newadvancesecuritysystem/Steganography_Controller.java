package newadvancesecuritysystem;

import java.io.File;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Steganography_Controller
{
	private Steganography_View	view;
	private Steganography model;

	private JPanel decode_panel;
	private JPanel encode_panel;
	private JTextArea input;
	private JButton encodeButton,decodeButton;
	private JLabel image_input;
	private JMenuItem encode;
	private JMenuItem decode;
	private JMenuItem exit,BackToMainMenu;

	private Encode			enc;
	private Decode			dec;
	private EncodeButton	encButton;
	private DecodeButton	decButton;
private BackToMainMenu bck;
	private String			stat_path = "";
	private String			stat_name = "";

	public Steganography_Controller(Steganography_View aView, Steganography aModel)
	{
		view  = aView;
		model = aModel;
BackToMainMenu=view.getBacktomainMenu();
		encode_panel	= view.getTextPanel();
		decode_panel	= view.getImagePanel();
		input			= view.getText();
		image_input		= view.getImageInput();
		encodeButton	= view.getEButton();
		decodeButton	= view.getDButton();
		encode			= view.getEncode();
		decode			= view.getDecode();
		//exit			= view.getExit();

		enc = new Encode();
		encode.addActionListener(enc);
                bck=new BackToMainMenu();
                BackToMainMenu.addActionListener(bck);
		dec = new Decode();
		decode.addActionListener(dec);
		//exit.addActionListener(new Exit());
		encButton = new EncodeButton();
		encodeButton.addActionListener(encButton);
		decButton = new DecodeButton();
		decodeButton.addActionListener(decButton);

		encode_view();
	}

	private void encode_view()
	{
		update();
		view.setContentPane(encode_panel);
		view.setVisible(true);
	}

	private void decode_view()
	{
		update();
		view.setContentPane(decode_panel);
		view.setVisible(true);
	}

	private class Encode implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			encode_view();
		}
	}
private class BackToMainMenu implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			SteganoMain m=new SteganoMain();
                        m.show();
                        
		}
	}

	private class Decode implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			decode_view();

			JFileChooser chooser = new JFileChooser("./");
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			chooser.setFileFilter(new Image_Filter());
			int returnVal = chooser.showOpenDialog(view);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				File directory = chooser.getSelectedFile();
				try{
					String image = directory.getPath();
					stat_name = directory.getName();
					stat_path = directory.getPath();
					stat_path = stat_path.substring(0,stat_path.length()-stat_name.length()-1);
					stat_name = stat_name.substring(0, stat_name.length()-4);
					image_input.setIcon(new ImageIcon(ImageIO.read(new File(image))));
				}
				catch(Exception except)
				{
				JOptionPane.showMessageDialog(view, "The File cannot be opened!",
					"Error!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}

	
	private class EncodeButton implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JFileChooser chooser = new JFileChooser("./");
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			chooser.setFileFilter(new Image_Filter());
			int returnVal = chooser.showOpenDialog(view);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				File directory = chooser.getSelectedFile();
				try{
					String text = input.getText();
					String ext  = Image_Filter.getExtension(directory);
					String name = directory.getName();
					String path = directory.getPath();
					path = path.substring(0,path.length()-name.length()-1);
					name = name.substring(0, name.length()-4);

					String stegan = JOptionPane.showInputDialog(view,
									"Enter output file name:", "File name",
									JOptionPane.PLAIN_MESSAGE);

					if(model.encode(path,name,ext,stegan,text))
					{
						JOptionPane.showMessageDialog(view, "The Image was encoded Successfully!",
							"Success!", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(view, "The Image could not be encoded!",
							"Error!", JOptionPane.INFORMATION_MESSAGE);
					}
					decode_view();
					image_input.setIcon(new ImageIcon(ImageIO.read(new File(path + "/" + stegan + ".png"))));
				}
				catch(Exception except)
				{
					JOptionPane.showMessageDialog(view, "The File cannot be opened!",
					"Error!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}

	}

	private class DecodeButton implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String message = model.decode(stat_path, stat_name);
			System.out.println(stat_path + ", " + stat_name);
			if(message != "")
			{
				encode_view();
				JOptionPane.showMessageDialog(view, "The Image was decoded Successfully!",
							"Success!", JOptionPane.INFORMATION_MESSAGE);
				input.setText(message);
			}
			else
			{
				JOptionPane.showMessageDialog(view, "The Image could not be decoded!",
							"Error!", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public void update()
	{
		input.setText("");
		image_input.setIcon(null);
		stat_path = "";
		stat_name = "";
	}

	/*public static void main(String args[])
	{

            new Steganography_Controller(
									new Steganography_View("Steganography"),
									new Steganography()
									);
	}*/
}