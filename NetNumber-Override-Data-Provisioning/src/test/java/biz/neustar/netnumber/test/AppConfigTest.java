package biz.neustar.netnumber.test;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import biz.neustar.netnumber.config.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:netnumber-application-context.xml")
public class AppConfigTest {
    @Autowired
    private AppConfig appConf;
    @Test
    public void ConfTest(){
	System.out.println(appConf.getFileNameExtension());
	assertNotNull(appConf.getBootstarpFileSeperator());
	File bootStrpFile = new File("c:\\log.txt");
	System.out.println(bootStrpFile.getName());
    }
    
}
