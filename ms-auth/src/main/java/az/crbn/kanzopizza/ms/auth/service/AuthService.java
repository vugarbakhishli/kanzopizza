package az.crbn.kanzopizza.ms.auth.service;

import az.crbn.kanzopizza.ms.auth.service.dto.auth.JwtTokenDto;
import az.crbn.kanzopizza.ms.auth.service.dto.auth.LoginRequestDto;
import az.crbn.kanzopizza.ms.auth.service.dto.auth.RegisterRequestDto;

public interface AuthService {

    void register(RegisterRequestDto registerRequestDto);

    JwtTokenDto login(LoginRequestDto loginRequestDto);

}
