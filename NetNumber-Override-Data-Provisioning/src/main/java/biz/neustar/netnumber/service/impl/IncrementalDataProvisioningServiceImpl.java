/**
 * 
 */
package biz.neustar.netnumber.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import biz.neustar.netnumber.config.AppConfig;
import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;
import biz.neustar.netnumber.model.CarrierSMSURIMappingsModel;
import biz.neustar.netnumber.model.LSMSDownloadLog;
import biz.neustar.netnumber.model.NNAuditProcessModel;
import biz.neustar.netnumber.service.AuditManagementService;
import biz.neustar.netnumber.service.CarrierSmsUriMappingService;
import biz.neustar.netnumber.service.DataProvisioningService;
import biz.neustar.netnumber.service.LSMSDownloadLogService;
import biz.neustar.netnumber.service.NetNumberLastTxnService;
import biz.neustar.netnumber.service.SubscriptionVersionService;
import biz.neustar.netnumber.util.DateUtils;

/**
 * @author surbhit.shrivastava
 *
 */


public class IncrementalDataProvisioningServiceImpl implements DataProvisioningService {


	private CarrierSmsUriMappingService<CarrierSMSURIMappingsModel> carrierSmsUriMappingService;
	private LSMSDownloadLogService lsmsDownloadLogService;
	private AuditManagementService<NNAuditProcessModel> auditManagementService;
	private SubscriptionVersionService subscriptionVersionService;
	private NetNumberLastTxnService numberLastTxnService ;  
	private Map<String, Boolean> fileStatusMap = new HashMap<String, Boolean>();
	private AppConfig appConf;
	
	private static final Logger LOGGER = Logger.getLogger(IncrementalDataProvisioningServiceImpl.class); 
	
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
	
	
	public void setNumberLastTxnService(
			NetNumberLastTxnService numberLastTxnService) {
		this.numberLastTxnService = numberLastTxnService;
	}
	
	@Override
	public List<File> generateFiles()
			throws NetNumberOverrideDataProvisioningException {

		LOGGER.info("Starting generation of incremental files");

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Input configuration for process : "+appConf.toString());
		}
		
		String customerName = null; 
		String incrementalFileName = null;
		long lastProcessedTxnId = 0 ;
		long maxLogId = 0 ;
		LSMSDownloadLog downloadLog  = null;
		
		customerName = appConf.getCustomerName();
		if (customerName!=null && customerName.trim().length()>0) {
			
//			load carrier - smsuri mapping
			List<CarrierSMSURIMappingsModel> carrierSMSURIMappings = carrierSmsUriMappingService
					.loadCarrierMappingsByCustomerName(customerName);
			
			if (carrierSMSURIMappings!=null && !carrierSMSURIMappings.isEmpty()) {
				
				lastProcessedTxnId = numberLastTxnService.getLastProcessedTxnIdForCust(customerName);
				
				maxLogId =lsmsDownloadLogService.getMaxLogID();
				
//				TODO : insert into process audit for process start
			
				String tempDirPath = appConf.getArchiveBootstrapTmpPath();
				
				for(CarrierSMSURIMappingsModel carrierSMSURIMapping :carrierSMSURIMappings)
				{
					incrementalFileName = DateUtils.getCurrentDateAsString()
							+ appConf.getFileNameSeperator()
							+ carrierSMSURIMapping.getCarrierID();
					downloadLog  = new LSMSDownloadLog();
					
					downloadLog.setSmsURI(carrierSMSURIMapping.getSmsuri());
					downloadLog.setLastProcessedTxnId(lastProcessedTxnId);
					downloadLog.setMaxLogId(maxLogId);
					downloadLog.setCarrierId(carrierSMSURIMapping.getCarrierID());
					downloadLog.setCustomerName(customerName);
					lsmsDownloadLogService.writeIncrLsmsDwnldLogQueryResToFile(downloadLog, tempDirPath+ File.separator+incrementalFileName, "-");
					
					carrierSMSURIMapping.getSmsuri();
				}
			}else{
//				Log Error message as No Carrier - SMSURI mapping found for customer and Exit process.  
			}
		}else{
//			Log Error message for empty customer Name and Exit process.  
		}

//		TODO: Fetch CarrierId list for which file needs to be generated
		
//		TODO: Iterate over CarrierId's    
		
//		TODO: Load CarrierId - SMSURI mappings for this Carrier ID  
		
//		TODO: Write File for this CarrierID   
			
		return null;
	}
}
