package com.eteration.simplebanking.controller;


import java.io.Serializable;

// This class is a placeholder you can change the complete implementation
public class TransactionStatus implements Serializable {

    public TransactionStatus(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    private String approvalCode;

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }
}
