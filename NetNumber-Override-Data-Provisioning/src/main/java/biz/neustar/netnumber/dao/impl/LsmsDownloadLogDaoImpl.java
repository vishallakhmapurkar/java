package biz.neustar.netnumber.dao.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import biz.neustar.netnumber.common.Constants;
import biz.neustar.netnumber.dao.LsmsDownloadLogDao;
import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;
import biz.neustar.netnumber.model.LSMSDownloadLog;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class LsmsDownloadLogDaoImpl implements LsmsDownloadLogDao {

	private static final Logger LOGGER = Logger
			.getLogger(LsmsDownloadLogDaoImpl.class);
	private static final String MAX_LOGID_QUERY = "SELECT MAX(LOGID) LOGID FROM LSMSDOWNLOADLOG ";

	private static final String GET_INCR_FILE_DATA_QUERY = "SELECT TN, OPERATIONTYPE, SMSURI, OLDSMSURI FROM LSMSDOWNLOADLOG WHERE OBJECTTYPE = ? AND LOGID > ? AND LOGID <= ? AND (SMSURI = ? OR OLDSMSURI = ?) ";

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public long getMaxLogID() throws NetNumberOverrideDataProvisioningException {

		long maxLogId = 0;
		try {
			maxLogId = jdbcTemplate.queryForLong(MAX_LOGID_QUERY);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("getting max log id " + maxLogId + "for query "
						+ MAX_LOGID_QUERY);
			}
		} catch (DataAccessException e) {
			throw new NetNumberOverrideDataProvisioningException(
					"query failure during the database operation ", e);
		}
		return maxLogId;
	}

	@Override
	public boolean writeIncrLsmsDwnldLogQueryResToFile(
			final LSMSDownloadLog downloadLog, final String filePath,
			final String fileSepearator)
			throws NetNumberOverrideDataProvisioningException {

		return jdbcTemplate.query(
				GET_INCR_FILE_DATA_QUERY,
				new Object[] { Constants.OBJECT_TYPE_SUBSCRIPTION_VERSION,
						downloadLog.getLastProcessedTxnId(),
						downloadLog.getMaxLogId(), downloadLog.getSmsURI() },
				new ResultSetExtractor<Boolean>() {

					boolean fileWritten = false;

					@Override
					public Boolean extractData(final ResultSet resultSet)
							throws SQLException, DataAccessException {

						BufferedWriter writer = null;
						File incrementalFile = null;
						String tn = null;
						String operationType = null;
						String smsURI = null;
						String oldSmsURI = null;
						String carrierSmsURI = null;
						int noOfRecords = 0;
						try {

							LOGGER.info("Generarting incremental file : "
									+ filePath + " for customer : "
									+ downloadLog.getCustomerName()
									+ " CarrierId : "
									+ downloadLog.getCarrierId());

							if (filePath != null
									&& filePath.trim().length() > 0) {

								incrementalFile = new File(filePath);

								if (incrementalFile.canWrite()) {

									carrierSmsURI = downloadLog.getSmsURI();

									writer = Files.newWriter(incrementalFile,
											Charsets.UTF_8);

									while (resultSet.next()) {

										tn = resultSet.getString("TN");
										operationType = resultSet
												.getString("OPERATIONTYPE");
										smsURI = resultSet.getString("SMSURI");
										oldSmsURI = resultSet
												.getString("OLDSMSURI");

										if (Constants.OperationType.A
												.equals(operationType)) {
											if (carrierSmsURI.equals(smsURI)) {
												writer.write(Constants.OperationType.A
														+ "," + tn);
											}
										} else if (Constants.OperationType.U
												.equals(operationType)) {
											if ((carrierSmsURI.equals(smsURI))
													&& (!carrierSmsURI
															.equals(oldSmsURI))) {
												writer.write(Constants.OperationType.U
														+ "," + tn);
											}
											if ((carrierSmsURI
													.equals(oldSmsURI))
													&& (!carrierSmsURI
															.equals(smsURI))) {
												writer.write(Constants.OperationType.D
														+ "," + tn);
											}
										} else if (Constants.OperationType.D
												.equals(operationType)) {
											writer.write(Constants.OperationType.D
													+ "," + tn);
										}
										writer.newLine();
										noOfRecords++;
									}
									writer.flush();
									fileWritten = true;
									// TODO : set fileName,
									// CarrierId,CustomerName,NoOfrecords
									LOGGER.info("Incremental file : "
											+ filePath
											+ " generated successfully for customer : "
											+ downloadLog.getCustomerName()
											+ " CarrierId : "
											+ downloadLog.getCarrierId());
								} else {
									LOGGER.error("Unable to write data on file : "
											+ filePath);
								}
							}
						} catch (SQLException e) {
							throw new SQLException(
									"Problem occurred while creating NetNumber Override Data Processing incremantal file : "
											+ filePath, e);
						} catch (IOException ioe) {
							throw new NetNumberOverrideDataProvisioningException(
									"Problem occurred while creating NetNumber Override Data Processing incremantal file : "
											+ filePath, ioe);
						} finally {
							try {
								if (writer != null) {
									writer.close();
								}
							} catch (IOException e) {
								LOGGER.warn(
										"Error occured while closing writer for file : "
												+ filePath, e);
							}
						}
						return fileWritten;
					}
				});

	}

}
