package biz.neustar.netnumber.dao;

import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;

public interface AuditManagementDao<T> {
	long insertNNProcessAudit(T t)
			throws NetNumberOverrideDataProvisioningException;

	int updateNNProcessAudit(T t) throws NetNumberOverrideDataProvisioningException;

}
