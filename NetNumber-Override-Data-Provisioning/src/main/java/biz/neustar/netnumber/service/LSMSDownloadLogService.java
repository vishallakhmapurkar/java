package biz.neustar.netnumber.service;

import biz.neustar.netnumber.model.LSMSDownloadLog;


public interface LSMSDownloadLogService {

	long getMaxLogID();
	
	boolean writeIncrLsmsDwnldLogQueryResToFile(LSMSDownloadLog lsmsDownloadLog,String filePath,
			final String fileSepearator);
	
	
}
