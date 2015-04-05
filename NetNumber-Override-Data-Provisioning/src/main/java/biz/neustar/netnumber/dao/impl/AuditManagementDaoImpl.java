package biz.neustar.netnumber.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import biz.neustar.netnumber.dao.AuditManagementDao;
import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;
import biz.neustar.netnumber.model.NNAuditProcessModel;

public class AuditManagementDaoImpl implements
		AuditManagementDao<NNAuditProcessModel> {
	private static final Logger LOGGER = Logger
			.getLogger(AuditManagementDaoImpl.class);

	private static final String INSERT_NN_AUDIT_PROCESS = "INSERT INTO NN_PROCESS_AUDIT(ID,START_DATE,PROCESS_TYPE,CUSTOMER_NAME,EXEC_STATUS,NO_OF_FILES_GENERATED,END_TXN_ID,CREATED_DATE,START_TXN_ID)"
			+ " VALUES(NN_PROCESS_AUDIT_SEQ.NEXTVAL,SYSTIMESTAMP,?,?,?,?,?,SYSTIMESTAMP,?)";

	private static final String UPDATE_NN_AUDIT_PROCESS = "UPDATE NN_PROCESS_AUDIT SET END_DATE=SYSTIMESTAMP,EXEC_STATUS=?,NO_OF_FILES_GENERATED=?,END_TXN_ID=?,MODIFIED_DATE=SYSTIMESTAMP WHERE ID=? AND CUSTOMER_NAME=?";

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public long insertNNProcessAudit(
			final NNAuditProcessModel nnAuditProcessModel)
			throws NetNumberOverrideDataProvisioningException {

		LOGGER.debug("Query is " + INSERT_NN_AUDIT_PROCESS
				+ nnAuditProcessModel.toString());
		Long newId = 0L;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(
							INSERT_NN_AUDIT_PROCESS, new String[] { "id" });
					ps.setString(1, nnAuditProcessModel.getProcessType());
					ps.setString(2, nnAuditProcessModel.getCustomerName());
					ps.setString(3, nnAuditProcessModel.getProcessStatus());
					ps.setLong(4,
							nnAuditProcessModel.getGeneratedFilesCounter());
					ps.setLong(5, nnAuditProcessModel.getEndTxnId());
					ps.setLong(6, nnAuditProcessModel.getStartTxnId());
					return ps;
				}

			}, keyHolder);

			newId = keyHolder.getKey().longValue();
		} catch (DataAccessException e) {
			throw new NetNumberOverrideDataProvisioningException(
					"Failure occur during the insert operation in nn_audit table",
					e);
		}
		LOGGER.info("Inserted record id: " + newId);
		LOGGER.debug("Exiting AuditManagementDaoImpl.insert");
		return newId;
		// int count = jdbcTemplate.queryForInt(INSERT_NN_AUDIT_PROCESS,
		// valueArr.toArray());

		// return count;
	}

	@Override
	public int updateNNProcessAudit(NNAuditProcessModel nnAuditProcessModel)
			throws NetNumberOverrideDataProvisioningException {
		LOGGER.debug("Query is " + UPDATE_NN_AUDIT_PROCESS
				+ nnAuditProcessModel.toString());
		int resulteCount = 0;
		try {
			resulteCount = jdbcTemplate.update(
					UPDATE_NN_AUDIT_PROCESS,
					new Object[] { nnAuditProcessModel.getProcessStatus(),
							nnAuditProcessModel.getGeneratedFilesCounter(),
							nnAuditProcessModel.getEndTxnId(),
							nnAuditProcessModel.getId(),
							nnAuditProcessModel.getCustomerName() });
		} catch (DataAccessException e) {
			throw new NetNumberOverrideDataProvisioningException(
					"Failure occur during the update operation in nn_audit table",
					e);
		}

		return resulteCount;

	}

	/*
	 * private List<Object> prepareValueArrForInsert( final NNAuditProcessModel
	 * entity) {
	 * LOGGER.debug("Entering AuditManagementDaoImpl.prepareValueArrForSelect");
	 * List<Object> valueArr = new ArrayList<Object>();
	 * valueArr.add(entity.getProcessType());
	 * valueArr.add(entity.getCustomerName());
	 * valueArr.add(entity.getProcessStatus());
	 * valueArr.add(entity.getGeneratedFilesCounter());
	 * valueArr.add(entity.getEndTxnId());
	 * 
	 * LOGGER.info("Value array is " + valueArr);
	 * LOGGER.debug("Exiting AuditManagementDaoImpl.prepareValueArrForSelect");
	 * return valueArr; }
	 */
}
