/**
 * 
 */
package biz.neustar.netnumber.model;

/**
 * @author surbhit.shrivastava
 *
 */
public class LSMSDownloadLog {

	private long logId = 0;
	private String tn = null;
	private String carrierId = null;
	private String customerName = null;
	private String operationType = null;
	private String smsURI = null;
	private String oldSmsURI = null;
	private long maxLogId = 0;
	private long lastProcessedTxnId = 0;
	
	/**
	 * @return the logId
	 */
	public long getLogId() {
		return logId;
	}
	/**
	 * @param logId the logId to set
	 */
	public void setLogId(long logId) {
		this.logId = logId;
	}
	/**
	 * @return the tn
	 */
	public String getTn() {
		return tn;
	}
	/**
	 * @param tn the tn to set
	 */
	public void setTn(String tn) {
		this.tn = tn;
	}
	/**
	 * @return the operationType
	 */
	public String getOperationType() {
		return operationType;
	}
	/**
	 * @param operationType the operationType to set
	 */
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	/**
	 * @return the smsURI
	 */
	public String getSmsURI() {
		return smsURI;
	}
	/**
	 * @param smsURI the smsURI to set
	 */
	public void setSmsURI(String smsURI) {
		this.smsURI = smsURI;
	}
	/**
	 * @return the oldSmsURI
	 */
	public String getOldSmsURI() {
		return oldSmsURI;
	}
	/**
	 * @param oldSmsURI the oldSmsURI to set
	 */
	public void setOldSmsURI(String oldSmsURI) {
		this.oldSmsURI = oldSmsURI;
	}
	/**
	 * @return the maxLogId
	 */
	public long getMaxLogId() {
		return maxLogId;
	}
	/**
	 * @param maxLogId the maxLogId to set
	 */
	public void setMaxLogId(long maxLogId) {
		this.maxLogId = maxLogId;
	}
	/**
	 * @return the lastProcessedTxnId
	 */
	public long getLastProcessedTxnId() {
		return lastProcessedTxnId;
	}
	/**
	 * @param lastProcessedTxnId the lastProcessedTxnId to set
	 */
	public void setLastProcessedTxnId(long lastProcessedTxnId) {
		this.lastProcessedTxnId = lastProcessedTxnId;
	}
	/**
	 * @return the carrierId
	 */
	public String getCarrierId() {
		return carrierId;
	}
	/**
	 * @param carrierId the carrierId to set
	 */
	public void setCarrierId(String carrierId) {
		this.carrierId = carrierId;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
}
