package vn.unicloud.sdk.payment.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.unicloud.sdk.payment.exception.PayResponseCode;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBase<T> {

    private int code;
    private String message;
    private T data;

    public ResponseBase(T data) {
        this.data = data;
        this.message = PayResponseCode.SUCCESS.getMessage();
        this.code = PayResponseCode.SUCCESS.getCode();
    }

    public ResponseBase(int code, String message) {
        this.message = message;
        this.code = code;
    }

}
