package biz.neustar.netnumber.config;

import biz.neustar.netnumber.sftp.SFTPConnector;

/**
 * 
 * @author vishal.lakhmapurkar
 * 
 */
public class AppConfig {

	private String bootstarpFileSeperator = null;
	private String incrementalFileSeperator = null;
	private String customerName = null;
	private String archiveBootstrapOutputPath = null;
	private String archiveBootstrapTmpPath = null;
	private String fileNameSeperator = null;
	private String fileNameExtension = null;

	private SFTPConnector sftpConnector =null;

	public void setSftpConnector(SFTPConnector sftpConnector) {
		this.sftpConnector = sftpConnector;
	}

	public SFTPConnector getSftpConnector() {
		return sftpConnector;
	}

	public String getFileNameExtension() {
		return fileNameExtension;
	}

	public void setFileNameExtension(String fileNameExtension) {
		this.fileNameExtension = fileNameExtension;
	}

	public String getFileNameSeperator() {
		return fileNameSeperator;
	}

	public void setFileNameSeperator(String fileNameSeperator) {
		this.fileNameSeperator = fileNameSeperator;
	}

	public String getArchiveBootstrapOutputPath() {
		return archiveBootstrapOutputPath;
	}

	public void setArchiveBootstrapOutputPath(String archiveBootstrapOutputPath) {
		this.archiveBootstrapOutputPath = archiveBootstrapOutputPath;
	}

	public String getArchiveBootstrapTmpPath() {
		return archiveBootstrapTmpPath;
	}

	public void setArchiveBootstrapTmpPath(String archiveBootstrapTmpPath) {
		this.archiveBootstrapTmpPath = archiveBootstrapTmpPath;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getBootstarpFileSeperator() {
		return bootstarpFileSeperator;
	}

	public void setBootstarpFileSeperator(String bootstarpFileSeperator) {
		this.bootstarpFileSeperator = bootstarpFileSeperator;
	}

	public String getIncrementalFileSeperator() {
		return incrementalFileSeperator;
	}

	public void setIncrementalFileSeperator(String incrementalFileSeperator) {
		this.incrementalFileSeperator = incrementalFileSeperator;
	}

}
