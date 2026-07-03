package com.example.purchase_requisition.dto;

public class RequisitionSummaryResponse {

    private String employeeId;
    private String product;
    private Integer quantity;
    private Double amount;
    private String status;

    public RequisitionSummaryResponse() {
    }

    public RequisitionSummaryResponse(String employeeId, String product, Integer quantity, Double amount, String status) {
        this.employeeId = employeeId;
        this.product = product;
        this.quantity = quantity;
        this.amount = amount;
        this.status = status;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
