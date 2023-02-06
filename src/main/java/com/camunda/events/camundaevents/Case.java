package com.camunda.events.camundaevents;

import java.util.HashMap;
import java.util.Map;

public class Case {
    private String caseId;
    private String caseType;
    private String caseStatus;
    private String investigationType;
    private String caseCreator;
    private String caseCreatorMail;
    private String caseLevel;
    private String casePriority;
    private String customerId;
    private String customerMobile;
    private String customerAddress;
    private String customerZipcode;

//    public Case(String caseId, String caseType, String caseStatus, String investigationType, String caseCreator, String caseCreatorMail) {
//        this.caseId = caseId.toUpperCase();
//        this.caseType = caseType.toUpperCase();
//        this.caseStatus = caseStatus.toUpperCase();
//        this.investigationType = investigationType.toUpperCase();
//        this.caseCreator = caseCreator.toUpperCase();
//        this.caseCreatorMail = caseCreatorMail;
//    }

    public Case(String caseId, String caseType, String caseStatus, String investigationType, String caseCreator,
			String caseCreatorMail, String caseLevel, String casePriority, String customerId, String customerMobile,
			String customerAddress, String customerZipcode) {
		this.caseId = caseId.toUpperCase();
        this.caseType = caseType.toUpperCase();
        this.caseStatus = caseStatus.toUpperCase();
        this.investigationType = investigationType.toUpperCase();
        this.caseCreator = caseCreator.toUpperCase();
        this.caseCreatorMail = caseCreatorMail;
		this.caseLevel = caseLevel;
		this.casePriority = casePriority;
		this.customerId = customerId;
		this.customerMobile = customerMobile;
		this.customerAddress = customerAddress;
		this.customerZipcode = customerZipcode;
	}

	public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getInvestigationType() {
        return investigationType;
    }

    public void setInvestigationType(String investigationType) {
        this.investigationType = investigationType;
    }

    public String getCaseCreator() {
        return caseCreator;
    }

    public void setCaseCreator(String caseCreator) {
        this.caseCreator = caseCreator;
    }

    public String getCaseCreatorMail() {
        return caseCreatorMail;
    }

    public void setCaseCreatorMail(String caseCreatorMail) {
        this.caseCreatorMail = caseCreatorMail;
    }

	public String getCaseLevel() {
		return caseLevel;
	}

	public void setCaseLevel(String caseLevel) {
		this.caseLevel = caseLevel;
	}

	public String getCasePriority() {
		return casePriority;
	}

	public void setCasePriority(String casePriority) {
		this.casePriority = casePriority;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerZipcode() {
		return customerZipcode;
	}

	public void setCustomerZipcode(String customerZipcode) {
		this.customerZipcode = customerZipcode;
	}

	@Override
	public String toString() {
		return "Case [caseId=" + caseId + ", caseType=" + caseType + ", caseStatus=" + caseStatus
				+ ", investigationType=" + investigationType + ", caseCreator=" + caseCreator + ", caseCreatorMail="
				+ caseCreatorMail + ", caseLevel=" + caseLevel + ", casePriority=" + casePriority + ", customerId="
				+ customerId + ", customerMobile=" + customerMobile + ", customerAddress=" + customerAddress
				+ ", customerZipcode=" + customerZipcode + "]";
	}
}
