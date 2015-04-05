package biz.neustar.netnumber.service;

import java.util.List;

import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;

public interface CarrierSmsUriMappingService<T> {
    public List<T> loadCarrierMappingsByCustomerName(String customerName) throws NetNumberOverrideDataProvisioningException;
    
}
