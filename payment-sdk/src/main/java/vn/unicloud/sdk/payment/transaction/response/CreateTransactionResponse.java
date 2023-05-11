package vn.unicloud.sdk.payment.transaction.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.unicloud.sdk.payment.transaction.model.TransactionStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTransactionResponse {

    private String transactionId;

    private String refTransactionId;

    private String payLinkCode;

    private long timeout;

    private String url;

    private String virtualAccount;

    private String description;

    private long amount;

    private String qrCodeString;

    private TransactionStatus status;

    private String time;

    private String accountName;


}
