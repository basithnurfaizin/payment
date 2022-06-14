package id.nurfaizin.payment.briva.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VirtualAccountDTO {

    @JsonProperty("institutionCode")
    private String institutionCode;

    @JsonProperty("brivaNo")
    private Long accountNumber;

    @JsonProperty("custCode")
    private Long customerCode;

    @JsonProperty("nama")
    private String name;

    private Integer amount;

    @JsonProperty("keterangan")
    private String description;

    private String expiredDate;

    public VirtualAccountDTO() {

    }

    public VirtualAccountDTO(String institutionCode, Long accountNumber, Long customerCode, String name, Integer amount, String description, String expiredDate) {
        this.institutionCode = institutionCode;
        this.accountNumber = accountNumber;
        this.customerCode = customerCode;
        this.name = name;
        this.amount = amount;
        this.description = description;
        this.expiredDate = expiredDate;
    }

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
}
