package biz.neustar.netnumber.service;

import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;

public interface NetNumberLastTxnService {
    
	public long  getLastProcessedTxnIdForCust(String customerName) throws NetNumberOverrideDataProvisioningException; 
		
}
