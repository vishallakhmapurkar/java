package biz.neustar.netnumber.service.impl;

import org.apache.log4j.Logger;

import biz.neustar.netnumber.dao.LsmsDownloadLogDao;
import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;
import biz.neustar.netnumber.model.LSMSDownloadLog;
import biz.neustar.netnumber.service.LSMSDownloadLogService;

public class LSMSDownloadLogServiceImpl implements LSMSDownloadLogService {

	private LsmsDownloadLogDao lsmsDownloadLogDao;
	private static final Logger LOGGER = Logger
			.getLogger(LSMSDownloadLogServiceImpl.class);

	public void setLsmsDownloadLogDao(LsmsDownloadLogDao lsmsDownloadLogDao) {
		this.lsmsDownloadLogDao = lsmsDownloadLogDao;
	}

	@Override
	public long getMaxLogID() throws NetNumberOverrideDataProvisioningException {
		long maxLogId = 0;
		try {
			maxLogId = lsmsDownloadLogDao.getMaxLogID();
		} catch (NetNumberOverrideDataProvisioningException e) {
			LOGGER.debug("Error during to feting log id");
			throw new NetNumberOverrideDataProvisioningException(
					e.getMessage(), e);
		}
		return maxLogId;
	}

	@Override
	public boolean writeIncrLsmsDwnldLogQueryResToFile(
			LSMSDownloadLog lsmsDownloadLog, String filePath,
			String fileSepearator) {
		// TODO Auto-generated method stub
		return false;
	}

}
