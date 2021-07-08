package az.crbn.kanzopizza.ms.customer.service.impl;

import az.crbn.common.dto.customer.PayReqDto;
import az.crbn.common.dto.customer.RollbackReqDto;
import az.crbn.common.security.auth.service.SecurityService;
import az.crbn.common.security.exception.UserNotFoundException;
import az.crbn.kanzopizza.ms.customer.exception.InsufficientFundException;
import az.crbn.kanzopizza.ms.customer.repository.CustomerRepository;
import az.crbn.kanzopizza.ms.customer.service.PaymentService;
import az.crbn.kanzopizza.ms.customer.service.dto.TopReqDto;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final CustomerRepository customerRepository;
    private final SecurityService securityService;

    @Override
    @Transactional
    public void pay(PayReqDto reqDto) {
        var currentUserUuid = securityService.getCurrentUserUuid().orElseThrow(UserNotFoundException::new);
        var customer = customerRepository.findByUserUuid(currentUserUuid).orElseThrow(UserNotFoundException::new);

        if (customer.getBalance().compareTo(reqDto.getTotalAmount()) < 0) {
            throw new InsufficientFundException();
        }

        // External payment service call...

        customerRepository.updateBalance(customer.getId(), customer.getBalance().subtract(reqDto.getTotalAmount()));
    }

    @Override
    @Transactional
    public void rollback(RollbackReqDto reqDto) {
        var currentUserUuid = securityService.getCurrentUserUuid().orElseThrow(UserNotFoundException::new);
        var customer = customerRepository.findByUserUuid(currentUserUuid).orElseThrow(UserNotFoundException::new);

        // External payment service call...

        customerRepository.updateBalance(customer.getId(), customer.getBalance().add(reqDto.getTotalAmount()));
    }

    @Override
    @Transactional
    public void top(TopReqDto reqDto) {
        if (reqDto.getDummyCardBalance().compareTo(reqDto.getAmount()) < 0) {
            throw new InsufficientFundException();
        }

        var currentUserUuid = securityService.getCurrentUserUuid().orElseThrow(UserNotFoundException::new);
        var customer = customerRepository.findByUserUuid(currentUserUuid).orElseThrow(UserNotFoundException::new);

        // External payment service call...

        customerRepository.updateBalance(customer.getId(), customer.getBalance().add(reqDto.getAmount()));
    }
}
