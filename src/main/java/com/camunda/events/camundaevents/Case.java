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

    public Case(String caseId, String caseType, String caseStatus, String investigationType, String caseCreator, String caseCreatorMail) {
        this.caseId = caseId.toUpperCase();
        this.caseType = caseType.toUpperCase();
        this.caseStatus = caseStatus.toUpperCase();
        this.investigationType = investigationType.toUpperCase();
        this.caseCreator = caseCreator.toUpperCase();
        this.caseCreatorMail = caseCreatorMail;
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
}
