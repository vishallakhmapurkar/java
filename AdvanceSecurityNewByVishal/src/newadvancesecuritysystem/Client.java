package newadvancesecuritysystem;

import java.io.*;
import java.net.*;

class Client extends Thread {
	
	private Socket socket;
	private String name;
	private BufferedReader input;
	private PrintStream output;
	private Server server;
	
	public Client(Socket socket, String name, Server server) 
	{
		this.socket = socket;
		this.name = name;
		this.server = server;
		try
		{
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintStream(socket.getOutputStream());
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
	
	public void run() {
		
		try
		{
			while (true)
			{
				String str = input.readLine();
			
				if (str == null)
					break;
				else
				{
					if (str.length() > 17 && str.substring(0, 18).equals("CLIENTNAMETOSERVER"))
					{
						setNavn(str.substring(19, str.length()));
						server.broadcast("(" + Dato.getDato() + " " + Dato.getTid() + ") <" + name + "> connected to server");
						server.getLog().addToLog("(" + socket.getInetAddress().getHostAddress() + ") <" + name + "> connected to server");
						sendUsers();
					}
					else if (str.length() > 11 && str.substring(0, 11).equals("�#PRIVATE�#"))
						privateMessage(str);
					else
					{
						server.broadcast("(" + Dato.getDato() + " " + Dato.getTid() + ") <" + name + "> " + str);
						server.getLog().addToLog("(" + socket.getInetAddress().getHostAddress() + ") <" + name + "> " + str);
					}
				}
			}
			
			socket.close();
		}
		catch (Exception e)
		{
			server.getLog().addToLog("CLIENTFEJL (" + socket.getInetAddress().getHostAddress() + "): " + e);
		}
		finally
		{
			server.broadcast("(" + Dato.getDato() + " " + Dato.getTid() + ") <" + name + "> disconnected from server");	
			server.getLog().addToLog("(" + socket.getInetAddress().getHostAddress() + ") <" + name + "> disconnected from server");
			server.disconnectClient(this);
			sendUsers();
		}
	
	}
	
	public void transmit(String besked)
	{
		output.println(besked);
	}
	
	private void sendUsers()
	{
		server.broadcast("#GETUSERLIST# " + server.getMonitor().getClientList() + " " + server.getMonitor().getAntalClient());
	}
	
	private void privateMessage(String str)
	{
		String nameTemp = str.substring(11, str.length());
		String toName = "";
		String besked = "";
		Client toClient;
		boolean send = false;
		
		for (int i=0;i<nameTemp.length();i++)
		{
			if (nameTemp.charAt(i) == '�' && nameTemp.charAt(i+1) == '#')
			{
				besked = nameTemp.substring(i+2, nameTemp.length());
				break;
			}
			else
				toName = toName + nameTemp.charAt(i);
		}
		
		System.out.println("To " + toName);
		
		for (int j=0;j<server.getMonitor().getAntalClient();j++)
		{
			toClient = server.getMonitor().getClient(j);
			if (toClient.getNavn().equals(toName))
			{
				toClient.transmit("(" + Dato.getDato() + " " + Dato.getTid() + ") <message from " + getNavn() + "> " + besked);
				transmit("(" + Dato.getDato() + " " + Dato.getTid() + ") <message to " + toName + "> " + besked);
				server.getLog().addToLog("(" + socket.getInetAddress().getHostAddress() + ") <" + name + "> send message <" + toName + "> " + besked);
				System.out.println("client " + toName + " / " + toClient.getNavn());
				send = true;
				break;
			}
		}
		
		if (!send)
		{
			System.out.println(send);
			transmit("(" + Dato.getDato() + " " + Dato.getTid() + ") <" + toName + " er logget af s� privatbeskeden er ikke sendt> ");
			server.getLog().addToLog("(" + socket.getInetAddress().getHostAddress() + ") <" + name + "> sendte FORG�VES en privatebesked til <" + toName + "> " + besked);
		}
	}
	
	public String getNavn(){ return this.name; }
	public void setNavn(String name){ this.name = name; }
}
