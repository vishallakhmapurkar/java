package biz.neustar.netnumber.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import biz.neustar.netnumber.config.AppConfig;
import biz.neustar.netnumber.service.SubscriptionVersionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:netnumber-application-context.xml")
public class SubscriptionVersionTest {
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private SubscriptionVersionService subscriptionVersionService;
    
    
    private static final String SMS_URI = "SMSURI";
    
    
    @Test
    public void testSVFileWrite(){
    	boolean isFileSuccessFullyWritten = false;
    	
    	isFileSuccessFullyWritten=subscriptionVersionService.writeBootstrapSVQueryResultToFile(appConfig.getArchiveBootstrapOutputPath(), appConfig.getBootstarpFileSeperator(), SMS_URI);
    	
    }
    
}
