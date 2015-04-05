package biz.neustar.netnumber.dao;

import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;

public interface NumberPoolBlockDao {
	boolean writeBootstrapBLOCKQueryResultToFile(final String filePath,
			final String fileNameSeperator, String SMSURI,String MMSURI)
			throws NetNumberOverrideDataProvisioningException;
}
