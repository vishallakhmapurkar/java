package newadvancesecuritysystem;

import java.util.Vector;

public class ClientMonitor
{
	private Vector clientVector;
	
	public ClientMonitor()
	{
		clientVector = new Vector();
	}
	
	public synchronized void addClient(Client client)
	{
		clientVector.add(client);
	}
	
	public synchronized void delClient(Client client)
	{
		clientVector.removeElement(client);
	}
	
	public synchronized Client getClient(int index)
	{
		return (Client) clientVector.elementAt(index);
	}
	
	public int getAntalClient()
	{
		return clientVector.size();
	}
	
	public String getClientList()
	{
		String clients = "";
		for (int i=0;i<getAntalClient();i++)
			clients = clients + getClient(i).getNavn() + "�";
		return clients;
	}
}