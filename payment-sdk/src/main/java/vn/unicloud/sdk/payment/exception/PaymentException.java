package vn.unicloud.sdk.payment.exception;

public class PaymentException extends RuntimeException {

    private final PayResponseCode responseCode;

    public PaymentException(PayResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }

    public PayResponseCode getResponseCode() {
        return this.responseCode;
    }

    @Override
    public String getMessage() {
        return this.responseCode == null ? PayResponseCode.FAILED.getMessage() : this.responseCode.getMessage();
    }

    public int getCode() {
        return this.responseCode == null ? PayResponseCode.FAILED.getCode() : this.responseCode.getCode();
    }

}