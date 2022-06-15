package io.github.basithnurfaizin.payment.briva.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatusResponse {

    private String institutionCode;

    @JsonProperty("brivaNo")
    private String brivaNumber;

    @JsonProperty("custCode")
    private String customerCode;

    @JsonProperty("statusBayar")
    private String paymentStatus;

    public String getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getBrivaNumber() {
        return brivaNumber;
    }

    public void setBrivaNumber(String brivaNumber) {
        this.brivaNumber = brivaNumber;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
