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

import biz.neustar.netnumber.dao.SubscriptionVersionDao;
import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class SubscriptionVersionDaoImpl implements SubscriptionVersionDao {

	private static final Logger LOGGER = Logger
			.getLogger(SubscriptionVersionDaoImpl.class);

	private static String SV_QUERY_FOR_INCREMENTAL_FILE = "SELECT TN,OPERATIONTYPE,OLDSMSURI,SMSURI FROM LSMSDOWNLOADLOG_INCR29JUN12 WHERE OBJECTTYPE = 6 AND LOGID > ? AND  LOGID <=? ";
	private static String SV_QUERY_FOR_BOOTSTRAP_FILE = "SELECT TN FROM SUBSCRIPTIONVERSION  WHERE TN =?";

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean writeBootstrapSVQueryResultToFile(final String filePath,
			final String fileContentSeperator, String SMSURI)
			throws NetNumberOverrideDataProvisioningException {

		return jdbcTemplate.query(SV_QUERY_FOR_BOOTSTRAP_FILE,
				new Object[] { SMSURI }, new ResultSetExtractor<Boolean>() {
					boolean isFileWrittenSucessfully = false;
					@Override
					public Boolean extractData(final ResultSet resultSet)
							throws SQLException, DataAccessException {
						BufferedWriter writer = null;
						File bootStrapFile = null;
						try {
							bootStrapFile = new File(filePath);

							if (isFileReadyToProcess(bootStrapFile)) {
								writer = Files.newWriter(bootStrapFile,
										Charsets.UTF_8);
								while (resultSet.next()) {
									writer.write(resultSet.getString("TN"));
									writer.newLine();
								}
								writer.flush();

								isFileWrittenSucessfully = true;
							}

						} catch (SQLException e) {

							throw new NetNumberOverrideDataProvisioningException(
									"Problem occurred while creating NetNumber Override Data Processing incremantal file,hence deleting file "
											+ filePath, e);

							//
						} catch (IOException ioe) {

							throw new NetNumberOverrideDataProvisioningException(
									"Problem occurred while writing data in archive file,hence deleting file"
											+ filePath, ioe);
						} finally {
							try {
								if (writer != null)
									writer.close();

							} catch (IOException e) {
								throw new NetNumberOverrideDataProvisioningException(
										"Problem occurred while closing  archive file"
												+ filePath, e);
							}
						}
						return isFileWrittenSucessfully;
					}
				});
	}

	private static boolean isFileReadyToProcess(File bootStrapFile) {
		boolean result = false;
		try {
			if (bootStrapFile != null) {
				if (bootStrapFile.exists()) {
					if (bootStrapFile.delete()) {
						if (bootStrapFile.createNewFile()) {
							if (bootStrapFile.canExecute()
									&& bootStrapFile.canRead()
									&& bootStrapFile.canWrite()) {
								result = true;
							}
						}
					}

				} else if (bootStrapFile.createNewFile()) {
					if (bootStrapFile.canExecute() && bootStrapFile.canRead()
							&& bootStrapFile.canWrite()) {
						result = true;
					}
				}
			}

		} catch (IOException e) {
			result = false;
		}
		return result;

	}
}
