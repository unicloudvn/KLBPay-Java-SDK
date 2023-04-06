package vn.unicloud.sdk.payment.transaction.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelTransactionRequest {

    private String transactionId;

}
