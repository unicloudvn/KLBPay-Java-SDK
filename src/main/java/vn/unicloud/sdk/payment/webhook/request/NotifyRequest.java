package vn.unicloud.sdk.payment.webhook.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotifyRequest {

    private String transactionId;

    private String refTransactionId;

    private String virtualAccount;

    private String actualAccount;

    private String fromBin;

    private String fromAccount;

    private boolean success;

    private Long amount;

    private String statusCode;

    private String txnNumber;

    private String transferDesc;

    private String time; // yyyy-MM-dd HH:mm:ss

}
