package id.nurfaizin.payment.briva.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResponseBRIVA<T> {

    @JsonProperty("status")
    private Boolean status;
    @JsonProperty("responseDescription")
    private String responseDescription;
    @JsonProperty("responseCode")
    private String responseCode;
    @JsonProperty("data")
    private T data;
    private Object fault;
    @JsonProperty("errDesc")
    public String errDesc;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getFault() {
        return fault;
    }

    public void setFault(Object fault) {
        this.fault = fault;
    }

    public String getErrDesc() {
        return errDesc;
    }

    public void setErrDesc(String errDesc) {
        this.errDesc = errDesc;
    }
}
