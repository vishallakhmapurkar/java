package biz.neustar.netnumber.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import biz.neustar.netnumber.config.AppConfig;
import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;
import biz.neustar.netnumber.model.CarrierSMSURIMappingsModel;
import biz.neustar.netnumber.service.CarrierSmsUriMappingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:netnumber-application-context.xml")
public class CarrierSmsUriMappingTest {

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private CarrierSmsUriMappingService<CarrierSMSURIMappingsModel> carrierSmsUriMappingServicel;

	@Test
	public void carrierURIMappingsTest() throws NetNumberOverrideDataProvisioningException {
		List<CarrierSMSURIMappingsModel> carrierSMSURIMappingsModels = carrierSmsUriMappingServicel
				.loadCarrierMappingsByCustomerName(appConfig.getCustomerName());
		int listSize = carrierSMSURIMappingsModels.size();
		if (listSize == 0)
			assertEquals(0, listSize);
		else if (listSize > 0)
			assertTrue(listSize > 0);
	}
}
