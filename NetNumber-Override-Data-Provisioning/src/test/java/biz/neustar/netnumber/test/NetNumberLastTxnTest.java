package biz.neustar.netnumber.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import biz.neustar.netnumber.config.AppConfig;
import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;
import biz.neustar.netnumber.service.NetNumberLastTxnService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:netnumber-application-context.xml")
public class NetNumberLastTxnTest {
	@Autowired
	private NetNumberLastTxnService netNumberLastTxnService;
	@Autowired
	private AppConfig appConfig;
	@Test(expected=NetNumberOverrideDataProvisioningException.class)
	public void testLastTxnID(){
		long id = netNumberLastTxnService.getLastProcessedTxnIdForCust(appConfig.getCustomerName());
		System.out.println(id);
	}

}
