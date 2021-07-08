package az.crbn.kanzopizza.ms.customer.service.impl;

import az.crbn.common.security.auth.service.SecurityService;
import az.crbn.common.security.exception.UserNotFoundException;
import az.crbn.kanzopizza.ms.customer.exception.AddressNotFoundException;
import az.crbn.kanzopizza.ms.customer.repository.AddressRepository;
import az.crbn.kanzopizza.ms.customer.repository.CustomerRepository;
import az.crbn.kanzopizza.ms.customer.service.AddressService;
import az.crbn.kanzopizza.ms.customer.service.adapter.AddressAdapter;
import az.crbn.kanzopizza.ms.customer.service.dto.AddressCreateReqDto;
import az.crbn.kanzopizza.ms.customer.service.dto.AddressResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressAdapter addressAdapter;
    private final CustomerRepository customerRepository;
    private final SecurityService securityService;

    @Override
    public AddressResDto create(AddressCreateReqDto reqDto) {
        var currentUserUuid = securityService.getCurrentUserUuid().orElseThrow(UserNotFoundException::new);
        var customer = customerRepository
                .findByUserUuid(currentUserUuid)
                .orElseThrow(UserNotFoundException::new);

        var address = addressAdapter.map(reqDto);
        address.setCustomer(customer);
        return addressAdapter.map(addressRepository.save(address));
    }

    @Override
    public AddressResDto getByUuid(String uuid) {
        return addressRepository
                .findByUuid(uuid)
                .map(addressAdapter::map)
                .orElseThrow(() -> new AddressNotFoundException(uuid));
    }

    @Override
    public void delete(String uuid) {
        var address = addressRepository.findByUuid(uuid).orElseThrow(() -> new AddressNotFoundException(uuid));
        addressRepository.delete(address);
    }
}
