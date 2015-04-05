package newadvancesecuritysystem;

/*
Nogenlunde log, skal finpudset !
*/

import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import javax.swing.border.*;
import java.net.*;
import java.io.*;
import java.applet.*;

public class ClientGUI extends JFrame implements AdjustmentListener, ActionListener, WindowListener, KeyListener, Runnable
{
	private Socket socket;
	private PrintStream output;
	private BufferedReader input;
	private JTextField textInput;
	private Thread internalThread;
	private JTextArea textareaChat;
	private JButton buttonSend;
	private JButton buttonConnect;
	private JLabel labStatus;
	private JLabel labHvem;
	private JPanel panUr;
	private JPanel panUser;
	private JPanel panChat;
	private JPanel panStatus;
	private TitledBorder titledBorder;
	private JScrollBar scrollBar;
	private JScrollPane scrollChat;
	private JScrollPane scrollClient;
	private JList listClient;
	private String host;
	private int port;
	private boolean running;
	private JMenuBar menu;
	private JMenu menuFunkt;
	private JMenu menuFiler;
	private JMenu menuHelp;
	private JMenuItem helpAbout;
	private JMenuItem funktStamdata;
	private JMenuItem filerGem,bckmenu;
	private JMenuItem filerQuit;
	private StamData stamData;
	private String filename;
	private FileManager fileManager;
	private AudioClip audioSound;
    private URL urlDest;
	private String audioFile;
	
	public ClientGUI()
	{
		fileManager = new FileManager();
		filename = "data.dat";
		initialize();
		
		stamData = (StamData) fileManager.loadFile(filename);
		if (stamData == null)
			stamData = new StamData();
                
	}
	
	public void run()
	{
		while(running)
    	{
			while (socket != null)
			{
	      		try 
	      		{
					String besked = input.readLine();
					if (besked != null)
					{
						if (besked.charAt(0) == '#' && besked.charAt(1) == 'G' && besked.charAt(2) == 'E' && besked.charAt(3) == 'T')
							setClientList(besked, getAntal(besked));
						else
						{
							if(this.getFocusOwner() == null)
								if(stamData.getLyd())
									audioSound.play();
							scrollBar.setValue(scrollBar.getMaximum());	
							textareaChat.append(besked + "\n");
							scrollBar.setValue(scrollBar.getMaximum());
						}
					}
					else
						break;
	      		}
				
				catch(IOException e){}
	      
	      		catch(Exception e)
				{
					System.out.println(e);
	      		}
			}
			repaint();
    	}	
	}
	
	private void initialize()
	{
		getContentPane().setLayout(null);
		setTitle("Chat");
		setResizable(false);
		addWindowListener(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon.gif"));
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		menu = new JMenuBar();
		menuFiler = new JMenu("Files");
                filerQuit= new JMenuItem("Exit");
                filerQuit.addActionListener(this);
		
		filerGem = new JMenuItem("Save data");
		filerGem.addActionListener(this);
		menuFunkt = new JMenu("Functions");
		funktStamdata = new JMenuItem("Change data");
		funktStamdata.addActionListener(this);
		menuHelp = new JMenu("Help");
		helpAbout = new JMenuItem("About");
		helpAbout.addActionListener(this);
		menuHelp.add(helpAbout);
		menuFunkt.add(funktStamdata);
		menuFiler.add(filerGem);
		menuFiler.addSeparator();
		menuFiler.add(filerQuit);
		menu.add(menuFiler);
		menu.add(menuFunkt);
		menu.add(menuHelp);
		getContentPane().add(menu, BorderLayout.NORTH);
		
		setJMenuBar(menu);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(new Dimension(800, 520));
		setResizable(false);
		setLocation((screenSize.width-this.getWidth())/2,(screenSize.height-this.getHeight())/2);
		setUndecorated(true);
		titledBorder = new TitledBorder("");
				
		panChat = new JPanel();
		panChat.setBounds(180, 20, 600, 400);
		panChat.setBorder(titledBorder);
		panChat.setLayout(null);
		getContentPane().add(panChat);
		
		panStatus = new JPanel();
		panStatus.setBounds(panChat.getX(), panChat.getHeight() + panChat.getY() + 10, panChat.getWidth(), 20);
		panStatus.setBorder(titledBorder);
		panStatus.setLayout(null);
		getContentPane().add(panStatus);
		
		panUser = new JPanel();
		panUser.setBounds(20, 20, 150, panChat.getHeight() + 10 + panStatus.getHeight());
		panUser.setBorder(titledBorder);
		panUser.setLayout(null);
		getContentPane().add(panUser);
				
		scrollChat = new JScrollPane();
		scrollChat.setBounds(10, 10, panChat.getWidth() - 20, (panChat.getHeight() - 50));
				
		textareaChat = new JTextArea();
		textareaChat.setBounds(5, 5, panChat.getWidth() - 30, (scrollChat.getHeight() - 10));
		textareaChat.setBorder(titledBorder);
		textareaChat.setLineWrap(true);
		textareaChat.setWrapStyleWord(true);		
		textareaChat.setEditable(false);
		scrollChat.add(textareaChat);
		
		scrollChat.setViewportView(textareaChat);        
        panChat.add(scrollChat);
				
		scrollBar = scrollChat.getVerticalScrollBar();
		scrollBar.addAdjustmentListener(this);
				
		scrollClient = new JScrollPane();
		scrollClient.setViewportView(listClient);     
		scrollClient.setBounds(10, 30, 130, panChat.getHeight() + 10 + panStatus.getHeight() - 40);
		panUser.add(scrollClient);
		
		listClient = new JList();
		listClient.setBounds(10, 10, scrollClient.getWidth() - 20, scrollClient.getHeight() - 20);
		listClient.setEnabled(false);
		listClient.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollClient.add(listClient);
		
		labHvem = new JLabel();
		labHvem.setText("Send to:");
		labHvem.setBounds(10, 5, panUser.getWidth()-100, 20);
		panUser.add(labHvem);
				
		textInput = new JTextField();
		textInput.setBounds(10, (scrollChat.getHeight() + scrollChat.getY() + 10), (panChat.getWidth() - panChat.getY() - 220), 20);
		textInput.setText("Connect to server...");
		textInput.setEnabled(false);
		textInput.setRequestFocusEnabled(true);
		textInput.addKeyListener(this);
		panChat.add(textInput);
						
		buttonSend = new JButton();
		buttonSend.setText("Send");
		buttonSend.setBounds((textInput.getWidth() + textInput.getX() +10), (scrollChat.getHeight() + scrollChat.getY() + 10), 100, 20);
		buttonSend.addActionListener(this);
		buttonSend.setEnabled(false);
		panChat.add(buttonSend);
		
		buttonConnect = new JButton();
		buttonConnect.setText("Connect");
		buttonConnect.setBounds((textInput.getWidth() + textInput.getX() + buttonSend.getWidth() +20), (scrollChat.getHeight() + scrollChat.getY() + 10), 100, 20);
		buttonConnect.addActionListener(this);
		panChat.add(buttonConnect);
		
		labStatus = new JLabel();
		labStatus.setText("Status: Not Connected.. Press connect to connect to server");
		labStatus.setBounds(10, 0, panStatus.getWidth()-100, 20);
		panStatus.add(labStatus);
		
		loadSound();
	}
	
	public void loadSound() 
	{
		String audioFile = "notify.wav";
		try
		{
            urlDest = new URL("file:" + audioFile);
        }
		catch (MalformedURLException e){ System.err.println(e.getMessage()); }
		
		audioSound = Applet.newAudioClip(urlDest);
	}
	
	private void connect()
	{
		if (stamData.getHost() != null && stamData.getHost().length() != 0 && stamData.getPort().length() != 0)
		{
			labStatus.setText("Status: Connecting to server ...");
			try
			{
				if(stamData.getHost() != null)
					host = stamData.getHost();
				if(stamData.getPort() != null)	
					port = Integer.valueOf(stamData.getPort()).intValue();
				socket = new Socket(host, port);
				output = new PrintStream(socket.getOutputStream());
				input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				internalThread = new Thread(this);
				internalThread.start();
				running = true;
				
				textInput.setEnabled(true);
				textInput.setText("");
				textInput.requestFocus();
				buttonSend.setEnabled(true);
				listClient.setEnabled(true);
				buttonConnect.setText("Disconnect");
				sendName();
				labStatus.setText("Status: Connected to " + host + " on port " + port);
			}
			catch(Exception e)
			{
				labStatus.setText("Status: Cannot connect to '" + host + " : " + port + "' try 'Change data'");		
			}
		}
		else
		{
			labStatus.setText("Status: Cannot connect to server try 'Change data'");		
		}
	}
	
	private void disconnect()
	{
		labStatus.setText("Status: Disconnecting to server ...");
		try
		{
			if (socket != null)
			{
				socket.close();
				socket = null;
			}
			running = false;
			textInput.setEnabled(false);
			textInput.setText("Connect to server...");
			buttonSend.setEnabled(false);
			listClient.setEnabled(false);
			setClientList("", 0);
			buttonConnect.setText("Connect");
			labStatus.setText("Status: Not Connected.. Press connect to connect to server");
			
		}
		catch(Exception e)
		{
			System.out.println("DISCONNECT: " + e);
		}
	}
	
	private void send()
	{
		if (textInput.getText().length() != 0)
		{
			String selectedName = (String) listClient.getSelectedValue();
			
			String besked = textInput.getText();
			
			if (selectedName != null && !selectedName.equals("VishalGood"))
			{
				besked = "�#PRIVATE�#" + selectedName + "�#" + besked;
			}
			
			try
			{
				output.println(besked);
			}
			catch(Exception e)
			{
				System.out.println("SEND: " + e);
			}
			textInput.setText("");
			textInput.requestFocus();
		}
	}
	
	private void retStamdata()
	{
		StamDataGUI sg = new StamDataGUI(stamData);
		sg.show();
	}
	
	private void afslut()
	{
		running = false;
		disconnect();
		gemData(stamData);
                Main m=new Main();
                this.dispose();
		//System.exit(0);
	}
	
	private void gemData(Object object)
	{
		try
		{
			fileManager.saveFile(object, filename);
		}
		catch(Exception e){}	
	}
	
	private void sendName()
	{
		output.println("CLIENTNAMETOSERVER " + stamData.getNavn());
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand()=="Connect")
			connect();
		else if(e.getActionCommand()=="Send")
			send();
		else if(e.getActionCommand()=="Disconnect")
			disconnect();
		else if(e.getActionCommand()=="Change data")			
			retStamdata();
		else if(e.getActionCommand()=="Save data")
			gemData(stamData);			
		else if(e.getActionCommand()=="Exit")
			afslut();
	}
	
	private int getAntal(String besked)
	{
		String antal = "";
		String beskedTemp = besked;
		for (int i=beskedTemp.length()-1;i>0;i--)
		{
			if (beskedTemp.charAt(i) == 32)
				break;
			else
				antal = beskedTemp.charAt(i) + antal;
		}
		return convertAntal(antal);
	}
			
	private void setClientList(String besked, int antalInt)
	{
		String selectedName = (String) listClient.getSelectedValue();
		int selectedIndex = 0;
		
		String[] userList = new String[antalInt+1];
		userList[0] = "VishalGood";
		int nr = 1;
		String navn = "";
		String beskedTemp = besked;
		
		for (int i=beskedTemp.length()-1;i>0;i--)
		{
			if (beskedTemp.charAt(i) == 32)
			{
				besked = beskedTemp.substring(14,i);
				break;
			}
		}
		
		int antalTaeller = 1;
		
		for (int i=0;i<besked.length();i++)
		{
			if(besked.charAt(i) != '�')
				navn = navn + besked.charAt(i);
			else
			{
				if (selectedName != null && navn.equals(selectedName))
					selectedIndex = antalTaeller;
					
				if (navn.equals(stamData.getNavn()))
				{
					nr++;
					navn = "";
				}
				else
				{
					userList[nr] = navn;
					nr++;
					navn = "";
				}
					
				antalTaeller++;
			}		
		}
		listClient.setListData(userList);
		listClient.setSelectedIndex(selectedIndex);
	}
	
	private int convertAntal(String antal)
	{
		return Integer.valueOf(antal).intValue();
	}
		
	public void adjustmentValueChanged(AdjustmentEvent e){}
	
	public void windowClosed(WindowEvent e){}
    public void windowIconified(WindowEvent e){}
    public void windowDeiconified(WindowEvent e){}
    public void windowActivated(WindowEvent e){}
    public void windowDeactivated(WindowEvent e){}
    public void windowOpened(WindowEvent e){}
    public void windowClosing(WindowEvent e)
	{
		try{
			socket.close();
			socket = null;
		}
		catch(IOException ex){
			System.out.println(ex);
		}
		finally
		{
			afslut();
    	}
    }
	
	public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==10)
			send();	
    }

}
