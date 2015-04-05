/**
 * 
 */
package biz.neustar.netnumber.main;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import biz.neustar.netnumber.exceptions.NetNumberOverrideDataProvisioningException;
import biz.neustar.netnumber.service.DataProvisioningService;

/**
 * @author surbhit.shrivastava
 * 
 * @version $Revision: 1.3 $
 */
public class BootStrapNetNumberOverrideDataProv {

	/**
	 * Field LOGGER.
	 */
	private static final Logger LOGGER = Logger
			.getLogger(BootStrapNetNumberOverrideDataProv.class);

	/**
	 * Field APPLICATION_CONTEXT_XML. (value is ""netnumber-application-context.xml"")
	 */
	private static final String APPLICATION_CONTEXT_XML = "netnumber-application-context.xml";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			LOGGER.info("Starting Boot Strap NetNumber override data provisioning process");
			ApplicationContext context = new ClassPathXmlApplicationContext(
					APPLICATION_CONTEXT_XML);
			DataProvisioningService dataProvisioningService = context.getBean(
					"bootstrapService", DataProvisioningService.class);
			List<File> files = dataProvisioningService.generateFiles();
			LOGGER.info("NetNumber override data provisioning process Finished Successfully.");
		} catch (NetNumberOverrideDataProvisioningException e) {
			LOGGER.error(
					"Error occured in NetNumber override data provisioning process execution",
					e);
			System.exit(0);
		} catch (Throwable th) {
			LOGGER.fatal(
					"Unhandled exception occured in NetNumber override data provisioning process",
					th);
			System.exit(0);
		}
	}
}
