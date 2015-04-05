package biz.neustar.netnumber.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import biz.neustar.netnumber.dao.CarrierSmsUriMappingDao;
import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;
import biz.neustar.netnumber.model.CarrierSMSURIMappingsModel;

public class CarrierSmsUriMappingDaoImpl implements
		CarrierSmsUriMappingDao<CarrierSMSURIMappingsModel> {

	private static final Logger LOGGER = Logger
			.getLogger(CarrierSmsUriMappingDaoImpl.class);

	private JdbcTemplate jdbcTemplate;

	private static final String FETCH_CUSTOMER_CARRIER_SMS_MAPPINGS = "SELECT CARRIER_ID,CUSTOMER_NAME,IS_ACTIVE,SMSURI,MMSURI,CREATED_DATE,MODIFIED_DATE FROM NN_CARRIER_SMSURI_MAPPING WHERE CUSTOMER_NAME =? AND IS_ACTIVE='Y'";

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<CarrierSMSURIMappingsModel> loadCarrierMappingsByCustomerName(
			String customerName)
			throws NetNumberOverrideDataProvisioningException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Fetching Carrier - SMSURI & MMSURI  mappings for customer : - "
					+ customerName);
		}
		List<CarrierSMSURIMappingsModel> carrierSMSURIMappingsModels = new ArrayList<CarrierSMSURIMappingsModel>();

		try {
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(
					FETCH_CUSTOMER_CARRIER_SMS_MAPPINGS,
					new Object[] { customerName });
			for (Map<String, Object> map : rows) {
				CarrierSMSURIMappingsModel carrierSMSURIMappingsModel = new CarrierSMSURIMappingsModel();
				carrierSMSURIMappingsModel.setCarrierID((String) map
						.get("CARRIER_ID"));
				carrierSMSURIMappingsModel.setActive((String) map
						.get("IS_ACTIVE"));
				carrierSMSURIMappingsModel.setCustomerName((String) map
						.get("CUSTOMER_NAME"));
				carrierSMSURIMappingsModel
						.setSmsuri((String) map.get("SMSURI"));
				carrierSMSURIMappingsModel
				.setMmsuri((String) map.get("MMSURI"));
				carrierSMSURIMappingsModel.setCreationDate((Timestamp) map
						.get("CREATED_DATE"));
				carrierSMSURIMappingsModel.setModificationDate((Timestamp) map
						.get("MODIFIED_DATE"));

				carrierSMSURIMappingsModels.add(carrierSMSURIMappingsModel);
			}

			/*
			 * carrierSMSURIMappingsModels = jdbcTemplate.query(
			 * FETCH_CUSTOMER_CARRIER_SMS_MAPPINGS, new Object[] { customerName
			 * }, new RowMapper<CarrierSMSURIMappingsModel>() { public
			 * CarrierSMSURIMappingsModel mapRow(ResultSet rs, int rowNum)
			 * throws SQLException { CarrierSMSURIMappingsModel
			 * carrierSMSURIMappingsModel = new CarrierSMSURIMappingsModel();
			 * carrierSMSURIMappingsModel.setCarrierID(rs
			 * .getString("CARRIER_ID"));
			 * carrierSMSURIMappingsModel.setActive(rs .getString("IS_ACTIVE"));
			 * carrierSMSURIMappingsModel.setCustomerName(rs
			 * .getString("CUSTOMER_NAME"));
			 * carrierSMSURIMappingsModel.setSmsuri(rs .getString("SMSURI"));
			 * carrierSMSURIMappingsModel.setCreationDate(rs
			 * .getLong("CREATED_DATE"));
			 * carrierSMSURIMappingsModel.setModificationDate(rs
			 * .getLong("MODIFIED_DATE")); return carrierSMSURIMappingsModel; }
			 * });
			 */
		} catch (DataAccessException e) {
			throw new NetNumberOverrideDataProvisioningException(
					"database error for query  "
							+ FETCH_CUSTOMER_CARRIER_SMS_MAPPINGS
							+ "for customer " + customerName, e);
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Number of customer for  Carrier SMS URI : - "
					+ carrierSMSURIMappingsModels.size());
		}
		return carrierSMSURIMappingsModels;

	}
}
