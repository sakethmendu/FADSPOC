package com.camunda.events.camundaevents;

import java.util.HashMap;
import java.util.Map;

public class Case {
    private String caseId;
    private String caseType;
    private String caseStatus;
    private String investigationType;

    public Case(String caseId, String caseType, String caseStatus, String investigationType) {
        this.caseId = caseId;
        this.caseType = caseType;
        this.caseStatus = caseStatus;
        this.investigationType = investigationType;
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
}
