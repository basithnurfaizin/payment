package id.nurfaizin.payment.enums;

public enum MessageConstant {

    VIRTUAL_ACCOUNT_EXIST("false","13","Data Customer Sudah Ada"),
    VIRTUAL_ACCOUNT_NOT_FOUND("false","14","Data Customer Tidak Ditemukan");
    private final String status;
    private final String message;
    private final String errorMessage;

    MessageConstant(String status, String message, String errorMessage) {
        this.status = status;
        this.message = message;
        this.errorMessage = errorMessage;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
