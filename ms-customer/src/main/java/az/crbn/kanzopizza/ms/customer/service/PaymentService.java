package az.crbn.kanzopizza.ms.customer.service;

import az.crbn.common.dto.customer.PayReqDto;
import az.crbn.common.dto.customer.RollbackReqDto;
import az.crbn.kanzopizza.ms.customer.service.dto.TopReqDto;

public interface PaymentService {
    void pay(PayReqDto reqDto);

    void rollback(RollbackReqDto reqDto);

    void top(TopReqDto reqDto);
}
