/**
 * 
 */
package biz.neustar.netnumber.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import biz.neustar.netnumber.common.Constants;
import biz.neustar.netnumber.config.AppConfig;
import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;
import biz.neustar.netnumber.model.CarrierSMSURIMappingsModel;
import biz.neustar.netnumber.model.NNAuditProcessModel;
import biz.neustar.netnumber.service.AuditManagementService;
import biz.neustar.netnumber.service.CarrierSmsUriMappingService;
import biz.neustar.netnumber.service.DataProvisioningService;
import biz.neustar.netnumber.service.LSMSDownloadLogService;
import biz.neustar.netnumber.service.SubscriptionVersionService;
import biz.neustar.netnumber.util.AppConfigValidator;
import biz.neustar.netnumber.util.DateUtils;

/**
 * @author surbhit.shrivastava
 * 
 */

public class BootStrapDataProvisioningServiceImpl implements
		DataProvisioningService {

	private CarrierSmsUriMappingService<CarrierSMSURIMappingsModel> carrierSmsUriMappingService;
	private LSMSDownloadLogService lsmsDownloadLogService;
	private AuditManagementService<NNAuditProcessModel> auditManagementService;
	private SubscriptionVersionService subscriptionVersionService;
	private Map<String, Boolean> fileStatusMap = new HashMap<String, Boolean>();
	private AppConfig appConf;

	private static final Logger LOGGER = Logger
			.getLogger(BootStrapDataProvisioningServiceImpl.class);

	public void setSubscriptionVersionService(
			SubscriptionVersionService subscriptionVersionService) {
		this.subscriptionVersionService = subscriptionVersionService;
	}

	public void setAppConf(AppConfig appConf) {
		this.appConf = appConf;
	}

	public void setCarrierSmsUriMappingService(
			CarrierSmsUriMappingService<CarrierSMSURIMappingsModel> carrierSmsUriMappingService) {
		this.carrierSmsUriMappingService = carrierSmsUriMappingService;
	}

	public void setLsmsDownloadLogService(
			LSMSDownloadLogService lsmsDownloadLogService) {
		this.lsmsDownloadLogService = lsmsDownloadLogService;
	}

	public void setAuditManagementService(
			AuditManagementService<NNAuditProcessModel> auditManagementService) {
		this.auditManagementService = auditManagementService;
	}

	private NNAuditProcessModel getNnAuditProcessModelForInsert(
			long generatedFileCounter, long logID) {
		NNAuditProcessModel nnAuditProcessModel = new NNAuditProcessModel();
		nnAuditProcessModel.setProcessType(Constants.ProcessType.BOOTSTRAP
				.toString());
		nnAuditProcessModel
				.setProcessStatus(Constants.ProcessExecutionStatus.STARTED
						.toString());
		nnAuditProcessModel.setGeneratedFilesCounter(generatedFileCounter);
		nnAuditProcessModel.setEndTxnId(logID);
		nnAuditProcessModel.setCustomerName(appConf.getCustomerName());
		nnAuditProcessModel.setStartTxnId(0);
		nnAuditProcessModel.setEndTxnId(0);
		return nnAuditProcessModel;
	}

	private NNAuditProcessModel getNnAuditProcessModelForUpdate(long id,
			long logID, int generatedFileCounter, String processStatus) {
		NNAuditProcessModel nnAuditProcessModel = new NNAuditProcessModel();
		nnAuditProcessModel.setProcessStatus(processStatus);
		nnAuditProcessModel.setId(id);
		nnAuditProcessModel.setGeneratedFilesCounter(generatedFileCounter);
		nnAuditProcessModel.setEndTxnId(logID);
		nnAuditProcessModel.setCustomerName(appConf.getCustomerName());
		return nnAuditProcessModel;
	}

	private boolean checkFilesReadyToUpload(Map<String, Boolean> filesStatusMap) {
		int noOfSuccess = 0;
		boolean result = false;
		if (filesStatusMap != null) {
			for (Map.Entry<String, Boolean> entry : filesStatusMap.entrySet()) {
				if (entry.getKey() != null && entry.getValue() != null) {
					if (entry.getValue()) {
						noOfSuccess++;
					}
				}
			}
			result = (noOfSuccess == filesStatusMap.size());
		}

		return result;
	}

	private boolean deleteFiles(Map<String, Boolean> filesStatusMap) {
		int noOffileDeleted = 0;
		boolean result = false;
		if (filesStatusMap != null) {
			for (Map.Entry<String, Boolean> entry : filesStatusMap.entrySet()) {

				File bootStrpFile = new File(
						appConf.getArchiveBootstrapTmpPath() + File.separator
								+ entry.getKey());
				if (bootStrpFile.exists()) {
					bootStrpFile.delete();
					noOffileDeleted++;
				}
			}
			result = (noOffileDeleted == filesStatusMap.size());
		}

		return result;
	}

	@Override
	public List<File> generateFiles()
			throws NetNumberOverrideDataProvisioningException {

		String fileName = null;
		String customerName = null;
		LOGGER.info("Starting generation of bootstrap file at time :"
				+ System.currentTimeMillis());
		if (AppConfigValidator.validateAppConfig(appConf)) {
			customerName = appConf.getCustomerName();
			try {
				long processId = 0;
				// load carrier ids
				List<CarrierSMSURIMappingsModel> carrierSMSURIMappingsModels = carrierSmsUriMappingService
						.loadCarrierMappingsByCustomerName(customerName);
				if (carrierSMSURIMappingsModels.size() > 0
						&& !carrierSMSURIMappingsModels.isEmpty()
						&& carrierSMSURIMappingsModels != null) {
					// get max log id
					long logId = lsmsDownloadLogService.getMaxLogID();
					// process started
					processId = auditManagementService
							.insertNNProcessAudit(getNnAuditProcessModelForInsert(
									carrierSMSURIMappingsModels.size(), logId));
					if (processId > 0) {

						for (CarrierSMSURIMappingsModel carrierSMSURIMappingsModel : carrierSMSURIMappingsModels) {

							if (carrierSMSURIMappingsModel.getCarrierID() != null
									&& carrierSMSURIMappingsModel.getSmsuri() != null) {

								fileName =appConf.getArchiveBootstrapTmpPath()
										+ File.separator
										+ DateUtils.getCurrentDateAsString()
										+ appConf.getFileNameSeperator()
										+ carrierSMSURIMappingsModel
												.getCarrierID()
										+appConf.getFileNameExtension();

								boolean result = subscriptionVersionService
										.writeBootstrapSVQueryResultToFile(fileName,
												appConf.getBootstarpFileSeperator(),
												"4072396389");

								if (fileStatusMap != null) {
									fileStatusMap.put(fileName, result);
								}

							}
						}
						if (checkFilesReadyToUpload(fileStatusMap)) {
							appConf.getSftpConnector().uploadFiles(fileStatusMap, true);
							
						} else if (deleteFiles(fileStatusMap)) {
							int status = auditManagementService
									.updateNNProcessAudit(getNnAuditProcessModelForUpdate(
											processId,
											logId,
											0,
											Constants.ProcessExecutionStatus.FAILURE
													.toString()));
							if (status > 0) {
								LOGGER.fatal("Exiting because, error occur for generating files");
								System.exit(0);

							}
						} else {
							LOGGER.fatal("Exiting because, error occur for deleting files");
							System.exit(0);
						}

					}

				} else {
					LOGGER.fatal("Exiting process because no carrier ids and sms uris for customer "
							+ appConf.getCustomerName());
					System.exit(0);
				}
			} catch (NetNumberOverrideDataProvisioningException e) {
				LOGGER.error(e.getMessage(), e);
				System.exit(0);
			}

		} else {
			LOGGER.fatal("Exiting process time " + System.currentTimeMillis());
			System.exit(0);
		}

		return null;

	}
}
