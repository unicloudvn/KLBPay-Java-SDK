package vn.unicloud.sdk.payment.transaction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfo {

    private String fullName;

    private String email;

    private String phone;

    private String address;

}
