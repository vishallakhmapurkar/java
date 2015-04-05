package biz.neustar.netnumber.dao;

import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;

public interface SubscriptionVersionDao {

	boolean writeBootstrapSVQueryResultToFile(final String filePath,
			final String fileNameSeperator, String SMSURI)
			throws NetNumberOverrideDataProvisioningException;
}
