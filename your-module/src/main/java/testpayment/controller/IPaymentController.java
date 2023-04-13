package testpayment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.unicloud.sdk.payment.client.ResponseBase;
import vn.unicloud.sdk.payment.transaction.request.CreateTransactionRequest;

import javax.validation.Valid;

@Tag(name = "Payment C  ontroller", description = "Thao tác với Payment")
@RequestMapping(value = "/api/payment")
public interface IPaymentController {

    /**
     * @param request
     * @return
     */
    @Operation(
            summary = "1. Tạo một giao dịch mới",
            description = "- Tạo một giao dịch mới, trả về QR code"
    )
    @PostMapping("/v1/create")
    String createTransaction(
            @Valid @RequestBody CreateTransactionRequest request

    );

    @Operation(
            summary = "2. Kiểm tra trạng thái giao dịch",
            description = "- Kiểm tra trạng thái giao dịch, có thể trả về timeout hoặc thành công"
    )
    @PostMapping(value = "/v1/check")
    ResponseEntity<ResponseBase<String>> checkTransaction(
            @Valid @RequestBody CreateTransactionRequest request
    );

    @Operation(
            summary = "Huỷ giao dịch",
            description = "- Huỷ giao dịch từ phía merchant"
    )
    @PostMapping(value = "/v1/cancel")
    ResponseEntity<ResponseBase<String>> cancelTransaction(
            @Valid @RequestBody CreateTransactionRequest request

    );

}

