package az.crbn.kanzopizza.ms.customer.service;

import az.crbn.kanzopizza.ms.customer.service.dto.AddressCreateReqDto;
import az.crbn.kanzopizza.ms.customer.service.dto.AddressResDto;

public interface AddressService {
    AddressResDto create(AddressCreateReqDto reqDto);

    AddressResDto getByUuid(String uuid);

    void delete(String uuid);
}
