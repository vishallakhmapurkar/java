package biz.neustar.netnumber.model;

import java.sql.Timestamp;

public class CarrierSMSURIMappingsModel {
	private String carrierID;
	private String customerName;
	private String smsuri;
	private String mmsuri;
	private String active;
	private Timestamp creationDate;
	private Timestamp modificationDate;

	
	public String getMmsuri() {
		return mmsuri;
	}

	public void setMmsuri(String mmsuri) {
		this.mmsuri = mmsuri;
	}

	public String getCarrierID() {
		return carrierID;
	}

	public void setCarrierID(String carrierID) {
		this.carrierID = carrierID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSmsuri() {
		return smsuri;
	}

	public void setSmsuri(String smsuri) {
		this.smsuri = smsuri;
	}

	public String isActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp timestamp) {
		this.creationDate = timestamp;
	}

	public Timestamp getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Timestamp modificationDate) {
		this.modificationDate = modificationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result
				+ ((carrierID == null) ? 0 : carrierID.hashCode());
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result
				+ ((customerName == null) ? 0 : customerName.hashCode());
		result = prime
				* result
				+ ((modificationDate == null) ? 0 : modificationDate.hashCode());
		result = prime * result + ((smsuri == null) ? 0 : smsuri.hashCode());
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
		CarrierSMSURIMappingsModel other = (CarrierSMSURIMappingsModel) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (carrierID == null) {
			if (other.carrierID != null)
				return false;
		} else if (!carrierID.equals(other.carrierID))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (modificationDate == null) {
			if (other.modificationDate != null)
				return false;
		} else if (!modificationDate.equals(other.modificationDate))
			return false;
		if (smsuri == null) {
			if (other.smsuri != null)
				return false;
		} else if (!smsuri.equals(other.smsuri))
			return false;
		return true;
	}

}
