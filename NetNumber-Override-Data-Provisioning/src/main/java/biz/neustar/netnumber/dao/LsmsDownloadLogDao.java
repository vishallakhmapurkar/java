package biz.neustar.netnumber.dao;

import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;
import biz.neustar.netnumber.model.LSMSDownloadLog;

public interface LsmsDownloadLogDao {

	long getMaxLogID() throws NetNumberOverrideDataProvisioningException;

	boolean writeIncrLsmsDwnldLogQueryResToFile(LSMSDownloadLog lsmsDownloadLog, String filePath, String fileSepearator) 
			throws NetNumberOverrideDataProvisioningException;	

	
}
