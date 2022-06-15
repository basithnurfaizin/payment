package io.github.basithnurfaizin.payment.briva.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatusRequest {

    @JsonProperty("institutionCode")
    private String institutionCode;

    @JsonProperty("brivaNo")
    private String brivaNumber;

    @JsonProperty("custCode")
    private String customerCode;

    @JsonProperty("statusBayar")
    private String paymentStatus;

    public StatusRequest(String institutionCode, String brivaNumber, String customerCode, String paymentStatus) {
        this.institutionCode = institutionCode;
        this.brivaNumber = brivaNumber;
        this.customerCode = customerCode;
        this.paymentStatus = paymentStatus;
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public String getBrivaNumber() {
        return brivaNumber;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
}
