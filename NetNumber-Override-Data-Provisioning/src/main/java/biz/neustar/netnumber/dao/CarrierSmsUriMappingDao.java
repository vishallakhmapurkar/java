package biz.neustar.netnumber.dao;

import java.util.List;


import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;


public interface CarrierSmsUriMappingDao<T> {

public List<T> loadCarrierMappingsByCustomerName(String customerName) throws NetNumberOverrideDataProvisioningException;  

}
