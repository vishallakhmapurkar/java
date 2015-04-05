package biz.neustar.netnumber.service.impl;

import org.apache.log4j.Logger;

import biz.neustar.netnumber.dao.AuditManagementDao;
import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;
import biz.neustar.netnumber.model.NNAuditProcessModel;
import biz.neustar.netnumber.service.AuditManagementService;

public class AuditManagementServiceImpl implements
		AuditManagementService<NNAuditProcessModel> {
	private AuditManagementDao<NNAuditProcessModel> auditManagementDao;
	private static final Logger LOGGER = Logger
			.getLogger(AuditManagementServiceImpl.class);

	public void setAuditManagementDao(
			AuditManagementDao<NNAuditProcessModel> auditManagementDao) {
		this.auditManagementDao = auditManagementDao;
	}

	@Override
	public long insertNNProcessAudit(NNAuditProcessModel nnAuditProcessModel) throws NetNumberOverrideDataProvisioningException{
		LOGGER.debug("inserting in audit table "+nnAuditProcessModel.toString());
		long genratedID = 0;
		try {
			genratedID = auditManagementDao
					.insertNNProcessAudit(nnAuditProcessModel);
		} catch (NetNumberOverrideDataProvisioningException e) {
			LOGGER.debug("error occur during inserting in audit table "+nnAuditProcessModel.toString());
			throw new NetNumberOverrideDataProvisioningException(
					e.getMessage(), e);
		}
		return genratedID;
	}

	@Override
	public int updateNNProcessAudit(NNAuditProcessModel nnAuditProcessModel)
			throws NetNumberOverrideDataProvisioningException {
		LOGGER.debug("updating in audit table "+nnAuditProcessModel.toString());
		int rowCount = 0;
		try {
			rowCount = auditManagementDao
					.updateNNProcessAudit(nnAuditProcessModel);
		} catch (NetNumberOverrideDataProvisioningException e) {
			LOGGER.debug("error occur during inserting in audit table "+nnAuditProcessModel.toString());
			throw new NetNumberOverrideDataProvisioningException(
					e.getMessage(), e);
		}
		return rowCount;
	}

}
