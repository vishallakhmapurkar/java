package newadvancesecuritysystem;

import java.io.*;
import java.net.*;

public class Server extends Thread
{
	private ClientMonitor monitor;
	private Client client;
	private int port;
	private Log log;
	
	public Server(ClientMonitor monitor, int port)
	{
		this.monitor = monitor;
		this.port = port;
		log = new Log();
	}
	
	public void run()
	{
		try 
		{
			ServerSocket s = new ServerSocket(port);
			log.addToLog("Server run on " + s.getInetAddress().getHostAddress() + ":" + port);
			while (true) 
			{
				Socket incoming = s.accept();
				client = new Client(incoming, incoming.getInetAddress().getHostAddress(), this);
				client.start();
				client.transmit("(" + Dato.getDato() + " " + Dato.getTid() + ") Welocome to the world of chatting " + getMonitor().getAntalClient() + "Vishal lakhmapurkar");
				monitor.addClient(client);
			}
		}
		catch (Exception e) {
			log.addToLog("SERVERFEJL: " + e);
		}
	}
	
	public void broadcast(String besked)
	{
		for(int i=0;i<monitor.getAntalClient();i++)
		{
			monitor.getClient(i).transmit(besked);
		}
	}
	
	public void disconnectClient(Client client)
	{
		monitor.delClient(client);
	}
	
	public ClientMonitor getMonitor(){ return monitor; }
	public void setMonitor(ClientMonitor monitor){ this.monitor = monitor; }
	public Log getLog(){ return log; }
	public void setLog(Log log){ this.log = log; }
}

