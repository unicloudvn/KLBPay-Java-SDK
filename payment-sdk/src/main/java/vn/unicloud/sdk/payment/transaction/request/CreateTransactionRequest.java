package vn.unicloud.sdk.payment.transaction.request;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import vn.unicloud.sdk.payment.transaction.model.*;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransactionRequest {

    private String refTransactionId;

    private Long amount;

    private String description;

    private Long timeout;

    private String title;

    private String language;

    private CustomerInfo customerInfo;

    private String successUrl;

    private String failUrl;

    private Integer redirectAfter;

    private String bankAccountNo;

}
