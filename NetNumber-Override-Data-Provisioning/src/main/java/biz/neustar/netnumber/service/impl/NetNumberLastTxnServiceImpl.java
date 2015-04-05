package biz.neustar.netnumber.service.impl;

import org.apache.log4j.Logger;

import biz.neustar.netnumber.dao.NetNumberLastTxnDao;
import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;
import biz.neustar.netnumber.service.NetNumberLastTxnService;


public class NetNumberLastTxnServiceImpl implements NetNumberLastTxnService {

	private NetNumberLastTxnDao netNumberLastTxnDao; 
	
    private static final Logger LOGGER = Logger.getLogger(NetNumberLastTxnServiceImpl.class);
	
	public void setNetNumberLastTxnDao(NetNumberLastTxnDao netNumberLastTxnDao) {
		this.netNumberLastTxnDao = netNumberLastTxnDao;
	}
	
	@Override
	public long getLastProcessedTxnIdForCust(String customerName) throws NetNumberOverrideDataProvisioningException{
		long lastTxnId = 0;
		
		try {
			LOGGER.debug("Fetching last transaction id for customre "+customerName);
			lastTxnId =  netNumberLastTxnDao.getLastProcessedTxnIdForCust(customerName);
		} catch (NetNumberOverrideDataProvisioningException e) {
		throw new NetNumberOverrideDataProvisioningException(e.getMessage(),e);
		}
		return lastTxnId;
	}
    
}
