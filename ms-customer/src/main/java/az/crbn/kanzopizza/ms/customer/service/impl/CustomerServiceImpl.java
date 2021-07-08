package az.crbn.kanzopizza.ms.customer.service.impl;

import az.crbn.common.security.auth.service.SecurityService;
import az.crbn.kanzopizza.ms.customer.domain.Customer;
import az.crbn.kanzopizza.ms.customer.exception.CustomerAlreadyRegisteredException;
import az.crbn.kanzopizza.ms.customer.repository.CustomerRepository;
import az.crbn.kanzopizza.ms.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final SecurityService securityService;

    @Override
    public void register(String userUuid) {
        var found = customerRepository.findByUserUuid(userUuid);

        if (found.isPresent()) {
            throw new CustomerAlreadyRegisteredException(userUuid);
        }

        customerRepository.save(Customer.builder().userUuid(userUuid).build());
    }
}
