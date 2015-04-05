package newadvancesecuritysystem;

import java.io.*;

public class StamData implements Serializable
{
	private String navn;
	private String host;
	private String port;
	private boolean lyd;
	
	public StamData()
	{
		this.navn = null;
		this.host = null;
		this.port = null;
		this.lyd = true;		
	}
	
	public StamData(String navn, String host, String port, boolean lyd)
	{
		this.navn = navn;
		this.host = host;
		this.port = port;
		this.lyd = lyd;
	}
	
	public String getNavn(){ return navn; }
	public String getHost(){ return host; }
	public String getPort(){ return port; }
	public boolean getLyd(){ return lyd; }
	
	public void setNavn(String navn){ this.navn = navn; }
	public void setHost(String host){ this.host = host; }
	public void setPort(String port){ this.port = port; }
	public void setLyd(boolean lyd){ this.lyd = lyd; }
}