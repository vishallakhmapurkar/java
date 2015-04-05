package biz.neustar.netnumber.sftp;

import java.util.Map;

import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;

public interface SFTPProcessor {
	Map<String, Boolean> uploadFiles(Map<String, Boolean> filesAndStatusMap,boolean isBootStrap) throws NetNumberOverrideDataProvisioningException;
}
