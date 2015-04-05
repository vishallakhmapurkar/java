package biz.neustar.netnumber.service;

import java.io.File;
import java.util.List;

import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;

public interface DataProvisioningService {

	public List<File> generateFiles() throws NetNumberOverrideDataProvisioningException;

	
}
