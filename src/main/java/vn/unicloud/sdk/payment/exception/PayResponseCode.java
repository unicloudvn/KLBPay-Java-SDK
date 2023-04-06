package vn.unicloud.sdk.payment.exception;

public enum PayResponseCode {
    // Common
    SUCCESS(0, "Success"),
    FAILED(1, "Failed"),
    INVALID_PARAM(2, "Invalid param"),

    // Payment
    PAYMENT_SECURITY_VIOLATION(1601, "Security violation"),
    PAYMENT_ORDER_COMPLETED(1602, "Order was completed"),
    PAYMENT_AMOUNT_INVALID(1603, "Invalid amount"),
    PAYMENT_TRANSACTION_CANCELED(1604, "Canceled transaction"),
    PAYMENT_TRANSACTION_EXPIRED(1605, "Transaction expired"),
    PAYMENT_TRANSACTION_INVALID(1606, "Invalid transaction"),
    PAYMENT_TRANSACTION_FAILED(1607, "Transaction failed"),
    PAYMENT_SERVICE_UNAVAILABLE(1608, "Service unavailable"),
    PAYMENT_INVALID_CLIENT_ID(1609, "Invalid client id"),

    ;

    private final int code;
    private final String message;

    PayResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static PayResponseCode valueOf(long value) {
        for (PayResponseCode responseCode : PayResponseCode.values()) {
            if (responseCode.getCode() == value) {
                return responseCode;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
