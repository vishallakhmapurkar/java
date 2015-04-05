package biz.neustar.netnumber.model;

public class NNAuditProcessModel {
	private long id;
	private long startDate;
	private long endDate;
	private String processType;
	private String customerName;
	private String processStatus;
	private long generatedFilesCounter;
	private long endTxnId;
	private long startTxnId;
	private long createdDate;
	private long modifiedDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public long getGeneratedFilesCounter() {
		return generatedFilesCounter;
	}

	public void setGeneratedFilesCounter(long generatedFilesCounter) {
		this.generatedFilesCounter = generatedFilesCounter;
	}

	public long getEndTxnId() {
		return endTxnId;
	}

	public void setEndTxnId(long endTxnId) {
		this.endTxnId = endTxnId;
	}

	public long getStartTxnId() {
		return startTxnId;
	}

	public void setStartTxnId(long startTxnId) {
		this.startTxnId = startTxnId;
	}

	public long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}

	public long getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(long modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "NNAuditProcessModel [id=" + id + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", processType=" + processType
				+ ", customerName=" + customerName + ", processStatus="
				+ processStatus + ", generatedFilesCounter="
				+ generatedFilesCounter + ", endTxnId=" + endTxnId
				+ ", startTxnId=" + startTxnId + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (createdDate ^ (createdDate >>> 32));
		result = prime * result
				+ ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + (int) (endDate ^ (endDate >>> 32));
		result = prime * result + (int) (endTxnId ^ (endTxnId >>> 32));
		result = prime
				* result
				+ (int) (generatedFilesCounter ^ (generatedFilesCounter >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (modifiedDate ^ (modifiedDate >>> 32));
		result = prime * result
				+ ((processStatus == null) ? 0 : processStatus.hashCode());
		result = prime * result
				+ ((processType == null) ? 0 : processType.hashCode());
		result = prime * result + (int) (startDate ^ (startDate >>> 32));
		result = prime * result + (int) (startTxnId ^ (startTxnId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NNAuditProcessModel other = (NNAuditProcessModel) obj;
		if (createdDate != other.createdDate)
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (endDate != other.endDate)
			return false;
		if (endTxnId != other.endTxnId)
			return false;
		if (generatedFilesCounter != other.generatedFilesCounter)
			return false;
		if (id != other.id)
			return false;
		if (modifiedDate != other.modifiedDate)
			return false;
		if (processStatus == null) {
			if (other.processStatus != null)
				return false;
		} else if (!processStatus.equals(other.processStatus))
			return false;
		if (processType == null) {
			if (other.processType != null)
				return false;
		} else if (!processType.equals(other.processType))
			return false;
		if (startDate != other.startDate)
			return false;
		if (startTxnId != other.startTxnId)
			return false;
		return true;
	}


}
