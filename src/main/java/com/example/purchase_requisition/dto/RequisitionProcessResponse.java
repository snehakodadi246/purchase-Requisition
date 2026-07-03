package com.example.purchase_requisition.dto;

public class RequisitionProcessResponse {

    private Long requisitionId;
    private String action;
    private String status;

    public RequisitionProcessResponse() {
    }

    public RequisitionProcessResponse(Long requisitionId, String action, String status) {
        this.requisitionId = requisitionId;
        this.action = action;
        this.status = status;
    }

    public Long getRequisitionId() {
        return requisitionId;
    }

    public void setRequisitionId(Long requisitionId) {
        this.requisitionId = requisitionId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
