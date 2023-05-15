package vn.unicloud.sdk.payment.exception;

public enum PayResponseCode {
    // Common
    SUCCESS(0, "Success"),
    FAILED(1, "Failed"),
    INVALID_PARAM(2, "Invalid param"),
    AMOUNT_INVALID(507, "Invalid amount"),

    BANK_ACCOUNT_NOT_FOUND(1301, "Bank account not found"),

    // Payment
    PAYMENT_SECURITY_VIOLATION(1601, "Security violation"),
    PAYMENT_CLIENT_ID_INVALID(1602, "Invalid client id"),
    PAYMENT_INVALID_TRANSACTION_ID(1603, "Invalid transactionId"),
    DUPLICATE_REFERENCE_TRANSACTION_ID(1604, "Duplicate ref transaction id"),
    PAYMENT_TYPE_INVALID(1605, "Invalid payment type"),
    PAYMENT_INVALID_DATA(1606, "Invalid data"),
    PAYMENT_ORDER_COMPLETED(1607, "Order was completed"),
    PAYMENT_TRANSACTION_TIMEOUT(1608, "Scan QR code timeout"),
    PAYMENT_TRANSACTION_CANCELED(1609, "Canceled transaction"),
    PAYMENT_TRANSACTION_EXPIRED(1610, "Transaction expired"),
    PAYMENT_TRANSACTION_FAILED(1611, "Transaction failed"),
    PAYMENT_SERVICE_UNAVAILABLE(1612, "Service unavailable"),
    PAYMENT_TRANSACTION_STATUS_INVALID(1613, "Invalid transaction status"),


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
