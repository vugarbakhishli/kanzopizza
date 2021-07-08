package az.crbn.kanzopizza.ms.customer.service.adapter;

import az.crbn.kanzopizza.ms.customer.domain.Address;
import az.crbn.kanzopizza.ms.customer.service.dto.AddressCreateReqDto;
import az.crbn.kanzopizza.ms.customer.service.dto.AddressResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressAdapter {
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Address map(AddressCreateReqDto reqDto);

    AddressResDto map(Address address);
}
