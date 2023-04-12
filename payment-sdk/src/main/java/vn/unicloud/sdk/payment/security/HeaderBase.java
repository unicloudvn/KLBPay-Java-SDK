package vn.unicloud.sdk.payment.security;

public class HeaderBase {

    public static final String TIMESTAMP = "x-api-time";
    public static final String SIGNATURE = "x-api-validate";
    public static final String CLIENT = "x-api-client";
    private HeaderBase() {
        throw new IllegalStateException();
    }

}
