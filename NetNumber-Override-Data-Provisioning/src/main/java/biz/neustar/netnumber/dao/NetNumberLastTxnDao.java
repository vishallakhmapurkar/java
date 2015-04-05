package biz.neustar.netnumber.dao;

import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;

public interface NetNumberLastTxnDao {

	public long getLastProcessedTxnIdForCust(String customerName) throws NetNumberOverrideDataProvisioningException; 
	
}
