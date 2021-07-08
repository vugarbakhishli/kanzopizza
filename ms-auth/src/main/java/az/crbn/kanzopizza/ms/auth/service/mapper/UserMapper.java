package az.crbn.kanzopizza.ms.auth.service.mapper;

import az.crbn.kanzopizza.ms.auth.domain.User;
import az.crbn.kanzopizza.ms.auth.service.dto.auth.RegisterRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "authority", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    User registerDtoToEntity(RegisterRequestDto registerRequestDto);

    //    UserResponseDto entityToResponseDto(User user);
}
