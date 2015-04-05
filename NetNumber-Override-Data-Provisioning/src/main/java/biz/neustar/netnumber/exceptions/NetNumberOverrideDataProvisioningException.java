/**
 * 
 */
package biz.neustar.netnumber.exceptions;

/**
 * @author surbhit.shrivastava
 *
 */
public class NetNumberOverrideDataProvisioningException extends RuntimeException {

	/**
	 * @param message
	 */
	public NetNumberOverrideDataProvisioningException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NetNumberOverrideDataProvisioningException(String message,
			Throwable cause) {
		super(message, cause);
	}

}
