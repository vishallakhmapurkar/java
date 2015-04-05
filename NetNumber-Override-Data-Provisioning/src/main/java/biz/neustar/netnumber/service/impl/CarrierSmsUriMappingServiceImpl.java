package biz.neustar.netnumber.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import biz.neustar.netnumber.dao.CarrierSmsUriMappingDao;
import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;
import biz.neustar.netnumber.model.CarrierSMSURIMappingsModel;
import biz.neustar.netnumber.service.CarrierSmsUriMappingService;

public class CarrierSmsUriMappingServiceImpl implements CarrierSmsUriMappingService<CarrierSMSURIMappingsModel> {
    
    private CarrierSmsUriMappingDao<CarrierSMSURIMappingsModel> carrierSmsUriMappingDao;
    private static final Logger                                 LOGGER = Logger.getLogger(CarrierSmsUriMappingServiceImpl.class);
    
    public void setCarrierSmsUriMappingDao(CarrierSmsUriMappingDao<CarrierSMSURIMappingsModel> carrierSmsUriMappingDao) {
	this.carrierSmsUriMappingDao = carrierSmsUriMappingDao;
    }
    
    @Override
    public List<CarrierSMSURIMappingsModel> loadCarrierMappingsByCustomerName(String customerName) throws NetNumberOverrideDataProvisioningException {
	List<CarrierSMSURIMappingsModel> carrierSMSURIMappingsModels = null;
	try {
	    carrierSMSURIMappingsModels = carrierSmsUriMappingDao.loadCarrierMappingsByCustomerName(customerName);
	} catch (NetNumberOverrideDataProvisioningException e) {
		LOGGER.debug("Error occur duing load carrier mappings");
	    throw new NetNumberOverrideDataProvisioningException(e.getMessage(),e);
	}
	return carrierSMSURIMappingsModels;
    }
    
}
