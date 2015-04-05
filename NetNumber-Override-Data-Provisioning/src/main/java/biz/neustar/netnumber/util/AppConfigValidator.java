package biz.neustar.netnumber.util;

import org.apache.commons.validator.GenericValidator;
import org.apache.log4j.Logger;

import biz.neustar.netnumber.config.AppConfig;

public class AppConfigValidator {
	private static final Logger LOGGER = Logger
			.getLogger(AppConfigValidator.class);
	
	public static boolean validateAppConfig(AppConfig appConfig) {
		if (GenericValidator.isBlankOrNull(appConfig
				.getArchiveBootstrapOutputPath())) {
			LOGGER.fatal("Process Exiting beacuse,Bootstrap output directory path can not be blank");
			return false;
		} else if (GenericValidator.isBlankOrNull(appConfig
				.getArchiveBootstrapTmpPath())) {
			LOGGER.fatal("Process Exiting beacuse,Bootstrap temp directory path can not be blank");
			return false;
		} else if (GenericValidator.isBlankOrNull(appConfig.getCustomerName())) {
			LOGGER.fatal("Process Exiting beacuse,customer name can not be blank");
			return false;
		}else if (GenericValidator.isBlankOrNull(appConfig.getSftpConnector().getHostName())) {
			LOGGER.fatal("Process Exiting beacuse,SFTP host name can't be blank");
			return false;
		}else if (GenericValidator.isBlankOrNull(appConfig.getSftpConnector().getUserName())) {
			LOGGER.fatal("Process Exiting beacuse,SFTP Username can not be blank");
			return false;
		}else if (GenericValidator.isBlankOrNull(appConfig.getSftpConnector().getPassword())) {
			LOGGER.fatal("Process Exiting beacuse,SFTP password  can not be blank");
			return false;
		}else if (GenericValidator.isBlankOrNull(String.valueOf(appConfig.getSftpConnector().getPort()))) {
			LOGGER.fatal("Process Exiting beacuse,SFTP port  name can not be blank");
			return false;
		}else if (GenericValidator.isBlankOrNull(appConfig.getSftpConnector().getBootStrapRemoteDir())) {
			LOGGER.fatal("Process Exiting beacuse,you remote directory path for bootstrap file name can not be blank");
			return false;
		} else if (GenericValidator.isBlankOrNull(appConfig.getSftpConnector().getIncrementalRemoteDir())) {
			LOGGER.fatal("Process Exiting beacuse,you remote directory path for incrmental file name can not be blank");
			return false;
		} else if (GenericValidator.isBlankOrNull(appConfig.getSftpConnector().getRemotePathSeparator())) {
			LOGGER.fatal("Process Exiting beacuse,you remote directory path seperator can not be blank");
			return false;
		} 
		else {
			return true;
		}

	}

}
