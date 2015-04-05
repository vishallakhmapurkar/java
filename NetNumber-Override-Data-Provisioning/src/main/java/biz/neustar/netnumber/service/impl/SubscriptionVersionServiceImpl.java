package biz.neustar.netnumber.service.impl;

import org.apache.log4j.Logger;

import biz.neustar.netnumber.dao.SubscriptionVersionDao;
import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;

public class SubscriptionVersionServiceImpl implements
		biz.neustar.netnumber.service.SubscriptionVersionService {

	private SubscriptionVersionDao subscriptionVersionDao;
	private static final Logger LOGGER = Logger
			.getLogger(SubscriptionVersionServiceImpl.class);

	public void setSubscriptionVersionDao(
			SubscriptionVersionDao subscriptionVersionDao) {
		this.subscriptionVersionDao = subscriptionVersionDao;
	}

	@Override
	public boolean writeBootstrapSVQueryResultToFile(final String filePath,
			final String fileContentSeperator, String SMSURI)
			throws NetNumberOverrideDataProvisioningException {
		boolean isFileProcessedSuccesfully = false;
		try {
			isFileProcessedSuccesfully = subscriptionVersionDao
					.writeBootstrapSVQueryResultToFile(filePath,
							fileContentSeperator, SMSURI);
		} catch (NetNumberOverrideDataProvisioningException e) {
			throw new NetNumberOverrideDataProvisioningException(
					e.getMessage(), e);
		}
		return isFileProcessedSuccesfully;
	}

}
