package biz.neustar.netnumber.sftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;

import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SFTPConnector implements SFTPProcessor {

	public static final Logger LOGGER = Logger.getLogger(SFTPConnector.class);

	private static final String STRICT_HOST_CHECK = "StrictHostKeyChecking";
	private static final String FILE_EXTENSION = ".xrf";
	protected static final String SFTP_CHANNEL = "sftp";

	private static final String NO_HOST_CHECK = "no";

	private String hostName;

	private int port;

	private String userName;

	private String password;

	protected String bootStrapRemoteDir;
	protected String incrementalRemoteDir;

	private String remotePathSeparator;

	private int retryCount;

	private int sleepTime;

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBootStrapRemoteDir() {
		return bootStrapRemoteDir;
	}

	public void setBootStrapRemoteDir(String bootStrapRemoteDir) {
		this.bootStrapRemoteDir = bootStrapRemoteDir;
	}

	public String getIncrementalRemoteDir() {
		return incrementalRemoteDir;
	}

	public void setIncrementalRemoteDir(String incrementalRemoteDir) {
		this.incrementalRemoteDir = incrementalRemoteDir;
	}

	public String getRemotePathSeparator() {
		return remotePathSeparator;
	}

	public void setRemotePathSeparator(String remotePathSeparator) {
		this.remotePathSeparator = remotePathSeparator;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}

	public static Logger getLogger() {
		return LOGGER;
	}

	public static String getStrictHostCheck() {
		return STRICT_HOST_CHECK;
	}

	public static String getSftpChannel() {
		return SFTP_CHANNEL;
	}

	public static String getNoHostCheck() {
		return NO_HOST_CHECK;
	}

	private Session getSftpSession() throws JSchException {

		JSch jSchInstance = new JSch();

		Session sftpSession = jSchInstance.getSession(userName, hostName, port);
		sftpSession.setPassword(password);

		// To Ignore known host check
		java.util.Properties config = new java.util.Properties();
		config.put(STRICT_HOST_CHECK, NO_HOST_CHECK);
		sftpSession.setConfig(config);
		sftpSession.connect();
		return sftpSession;
	}

	private ChannelSftp getChannelSftp(Session session) throws JSchException {
		Channel channel = session.openChannel("sftp");
		channel.connect();
		return (ChannelSftp) channel;
	}
	
	private void renameFile(ChannelSftp channelSftp,String oldFile,String newName) throws SftpException{
		
		channelSftp.rename(oldFile,newName);
	}

	public Map<String, Boolean> uploadFiles(
			Map<String, Boolean> filesAndStatusMap,boolean isBootStrap)
			throws NetNumberOverrideDataProvisioningException {
		if (filesAndStatusMap != null) {
			Session session = null;
			ChannelSftp channelSftp = null;
			for (Map.Entry<String, Boolean> entry : filesAndStatusMap
					.entrySet()) {
				
				try {
					
				   File bootStrpFile = new File(entry.getKey());

				   // get sftp session
					session = getSftpSession();
					// create open channel
					channelSftp = getChannelSftp(session);
					
					//creating remote directory
					 //Change to the remote directory
					String remoteDir = null;
					
		            if(isBootStrap)
		            	remoteDir = bootStrapRemoteDir;
					else
		            	remoteDir = incrementalRemoteDir;
		            
		             channelSftp.cd(remoteDir);
		            
		            //put file to channel
		            channelSftp.put(new FileInputStream(bootStrpFile), bootStrpFile.getName());
		            //renaming file after uploading 
		            String fileAfterRename= remoteDir+remotePathSeparator+(bootStrpFile.getName().substring(0, bootStrpFile.getName().indexOf(FILE_EXTENSION)));
		            renameFile(channelSftp,remoteDir+remotePathSeparator+bootStrpFile.getName(),fileAfterRename);
		           //disconnect from sftp
		            channelSftp.quit();
		            
				} catch (JSchException e) {
					 throw new NetNumberOverrideDataProvisioningException("Error occur during file uploading",e);
				} catch (SftpException e) {
					 throw new NetNumberOverrideDataProvisioningException("Error occur during file uploading",e);
				} catch (IOException e) {
					 throw new NetNumberOverrideDataProvisioningException("Error occur during file uploading",e);
				}

			}

		}
		return null;
	}
}
