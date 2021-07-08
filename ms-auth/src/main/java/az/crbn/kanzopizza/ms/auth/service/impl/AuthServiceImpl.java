package az.crbn.kanzopizza.ms.auth.service.impl;

import static az.crbn.common.security.constants.UserAuthority.USER;
import static az.crbn.common.security.constants.UserStatus.ACTIVE;

import az.crbn.common.dto.customer.RegisterReqDto;
import az.crbn.kanzopizza.ms.auth.client.MsCustomerClient;
import az.crbn.kanzopizza.ms.auth.repository.UserRepository;
import az.crbn.kanzopizza.ms.auth.security.SecurityUtil;
import az.crbn.kanzopizza.ms.auth.service.AuthService;
import az.crbn.kanzopizza.ms.auth.service.dto.auth.JwtTokenDto;
import az.crbn.kanzopizza.ms.auth.service.dto.auth.LoginRequestDto;
import az.crbn.kanzopizza.ms.auth.service.dto.auth.RegisterRequestDto;
import az.crbn.kanzopizza.ms.auth.service.mapper.UserMapper;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final SecurityUtil securityUtil;
    private final MsCustomerClient msCustomerClient;

    @Override
    @Transactional
    public void register(RegisterRequestDto registerRequestDto) {
        var user = userMapper.registerDtoToEntity(registerRequestDto);
        user.setAuthority(USER);

        // user.setStatus(INACTIVE); TODO: Send email
        user.setStatus(ACTIVE);
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));

        var saved = userRepository.save(user);

        msCustomerClient.register(new RegisterReqDto(saved.getUuid()));
    }

    @Override
    public JwtTokenDto login(LoginRequestDto loginRequestDto) {
        var jwt = securityUtil.createAuthentication(loginRequestDto);
        // TODO: generate/persist refresh token via Redis
        return JwtTokenDto.builder().authToken(jwt).build();
    }
}
