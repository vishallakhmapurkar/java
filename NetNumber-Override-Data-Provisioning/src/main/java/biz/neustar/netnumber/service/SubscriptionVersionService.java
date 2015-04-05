package biz.neustar.netnumber.service;

import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;


public interface SubscriptionVersionService {
	boolean writeBootstrapSVQueryResultToFile(final String filePath,final String fileNameSeperator,
			String SMSURI) throws NetNumberOverrideDataProvisioningException;
}
