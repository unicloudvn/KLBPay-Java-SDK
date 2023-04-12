package vn.unicloud.sdk.payment.transaction.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.unicloud.sdk.payment.transaction.model.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryTransactionResponse {

    private TransactionStatus status;

    private String refTransactionId;

    private Long amount;

}
