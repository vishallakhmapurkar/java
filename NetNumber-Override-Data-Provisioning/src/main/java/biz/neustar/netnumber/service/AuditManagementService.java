package biz.neustar.netnumber.service;

import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;

public interface AuditManagementService<T> {
	long insertNNProcessAudit(T t) throws NetNumberOverrideDataProvisioningException;
	int updateNNProcessAudit(T t) throws NetNumberOverrideDataProvisioningException;
}
