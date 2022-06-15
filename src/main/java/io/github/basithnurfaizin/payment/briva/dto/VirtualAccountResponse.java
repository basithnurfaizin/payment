package io.github.basithnurfaizin.payment.briva.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VirtualAccountResponse {

    @JsonProperty("institutionCode")
    private String institutionCode;

    @JsonProperty("brivaNo")
    private Long accountNumber;

    @JsonProperty("BrivaNo")
    private Long brivaNo;

    @JsonProperty("custCode")
    private Long customerCode;

    @JsonProperty("CustCode")
    private Long custCode;

    @JsonProperty("nama")
    private String name;

    @JsonProperty("Nama")
    private String nama;

    private Integer amount;

    @JsonProperty("Amount")
    private String amountPay;

    @JsonProperty("keterangan")
    private String description;

    @JsonProperty("Keterangan")
    private String keterangan;

    private String expiredDate;

    @JsonProperty("statusBayar")
    private String status;

    @JsonProperty("lastUpdate")
    private String lastUpdate;

    public String getInstitutionCode() {
        return institutionCode;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public String getName() {
        return name;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public String getStatus() {
        return status;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public Long getBrivaNo() {
        return brivaNo;
    }

    @Override
    public String toString() {
        return "VirtualAccountResponse{" +
                "institutionCode='" + institutionCode + '\'' +
                ", accountNumber=" + accountNumber +
                ", brivaNo=" + brivaNo +
                ", customerCode=" + customerCode +
                ", custCode=" + custCode +
                ", name='" + name + '\'' +
                ", nama='" + nama + '\'' +
                ", amount=" + amount +
                ", amountPay='" + amountPay + '\'' +
                ", description='" + description + '\'' +
                ", keterangan='" + keterangan + '\'' +
                ", expiredDate='" + expiredDate + '\'' +
                ", status='" + status + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                '}';
    }
}
