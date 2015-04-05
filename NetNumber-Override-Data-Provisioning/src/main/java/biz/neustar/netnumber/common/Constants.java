/**
 * 
 */
package biz.neustar.netnumber.common;

/**
 * @author surbhit.shrivastava
 * 
 */

public class Constants {

	public static enum ProcessType {
		BOOTSTRAP, INCREMENTAL
	}

	public static enum OperationType {
		A, U, D
	}

	public static enum ProcessExecutionStatus {
		STARTED, SUCCESS, FAILURE
	}

	public static final int OBJECT_TYPE_SUBSCRIPTION_VERSION = 6 ;
	
}
