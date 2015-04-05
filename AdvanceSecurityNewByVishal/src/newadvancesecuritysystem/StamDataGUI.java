package newadvancesecuritysystem;

import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import javax.swing.border.*;
import java.net.*;
import java.io.*;

public class StamDataGUI extends JDialog implements ActionListener, WindowListener, KeyListener
{
	private JLabel labNavn;
	private JLabel labHost;
	private JLabel labPort;
	private JLabel labLyd;
	private JTextField textNavn;
	private JTextField textHost;
	private JTextField textPort;
	private TitledBorder titledBorder;
	private JPanel panStamData;
	private JPanel panKnap;
	private JButton buttonOk;
	private JButton buttonFortryd;
	private JRadioButton radioJa;
	private JRadioButton radioNej;
	private ButtonGroup groupSound;
	private StamData stamData;
	
	public StamDataGUI(StamData stamData)
	{
		this.stamData = stamData;
		initialize();
	}
	
	private void initialize()
	{
		getContentPane().setLayout(null);
		setTitle("Change data");
		addWindowListener(this);
		addKeyListener(this);
		setModal(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(new Dimension(300, 255));
		setResizable(false);
		setLocation((screenSize.width-this.getWidth())/2,(screenSize.height-this.getHeight())/2);
		
		titledBorder = new TitledBorder("");
		
		panStamData = new JPanel();
		panStamData.setBounds(20, 20, this.getWidth() - 40, this.getHeight() - 115);
		panStamData.setBorder(titledBorder);
		panStamData.setLayout(null);
		getContentPane().add(panStamData);
		
		panKnap = new JPanel();
		panKnap.setBounds(20, panStamData.getHeight() + panStamData.getY() + 10, panStamData.getWidth() , 40);
		panKnap.setBorder(titledBorder);
		panKnap.setLayout(null);
		getContentPane().add(panKnap);
		
		labNavn = new JLabel();
		labNavn.setBounds(10, 10, 80, 20);
		labNavn.setText("Name: ");
		panStamData.add(labNavn);
		
		labHost = new JLabel();
		labHost.setBounds(10, 40, 80, 20);
		labHost.setText("Host: ");
		panStamData.add(labHost);
		
		labPort = new JLabel();
		labPort.setBounds(10, 70, 80, 20);
		labPort.setText("Port: ");
		panStamData.add(labPort);
		
		labLyd = new JLabel();
		labLyd.setBounds(10, 100, 80, 20);
		labLyd.setText("Use sounds? ");
		panStamData.add(labLyd);
		
		radioJa = new JRadioButton();
		radioJa.setBounds(120, 100, 50, 20);
		radioJa.setText("Yes");
		radioJa.setFocusPainted(false);
		panStamData.add(radioJa);
				
		radioNej = new JRadioButton();
		radioNej.setBounds(180, 100, 50, 20);
		radioNej.setText("No");
		radioNej.setFocusPainted(false);
		panStamData.add(radioNej);
		
		if(stamData.getLyd())
			radioJa.setSelected(true);
		else
			radioNej.setSelected(true);
		
		groupSound = new ButtonGroup();
		groupSound.add(radioJa);
		groupSound.add(radioNej);
					
		textNavn = new JTextField();
		textNavn.setBounds(100, 10, 150, 20);
		textNavn.setText(stamData.getNavn());
		textNavn.addKeyListener(this);
		panStamData.add(textNavn);
		
		textHost = new JTextField();
		textHost.setBounds(100, 40, 150, 20);
		textHost.setText(stamData.getHost());
		textHost.addKeyListener(this);
		panStamData.add(textHost);
		
		textPort = new JTextField();
		textPort.setBounds(100, 70, 150, 20);
		textPort.setText(stamData.getPort());
		textPort.addKeyListener(this);
		panStamData.add(textPort);
		
		buttonOk = new JButton();
		buttonOk.setText("Ok");
		buttonOk.setBounds(25, 10, 100, 20);
		buttonOk.addActionListener(this);
		panKnap.add(buttonOk);		
		
		buttonFortryd = new JButton();
		buttonFortryd.setText("Cancel");
		buttonFortryd.setBounds(buttonOk.getWidth() + buttonOk.getX() + 10 , 10, 100, 20);
		buttonFortryd.addActionListener(this);
		panKnap.add(buttonFortryd);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand()=="Ok")
			ok();
		else
			fortryd();	
	}
	
	private void ok()
	{
		stamData.setNavn(textNavn.getText());	
		stamData.setHost(textHost.getText());
		stamData.setPort(textPort.getText());
		
		if(radioJa.isSelected())
			stamData.setLyd(true);
		else
			stamData.setLyd(false);	
		
		dispose();
	}
	
	private void fortryd()
	{
		dispose();
	}
	
	public void windowClosed(WindowEvent e){}
    public void windowIconified(WindowEvent e){}
    public void windowDeiconified(WindowEvent e){}
    public void windowActivated(WindowEvent e){}
    public void windowDeactivated(WindowEvent e){}
    public void windowOpened(WindowEvent e){}
    public void windowClosing(WindowEvent e){ fortryd(); }
	
	public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==10)
			ok();
		else if (e.getKeyCode() == 27)
			fortryd();
    }
}