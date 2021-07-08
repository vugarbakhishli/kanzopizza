package az.crbn.kanzopizza.ms.auth.service.impl;

import az.crbn.kanzopizza.ms.auth.security.SecurityUtil;
import az.crbn.kanzopizza.ms.auth.service.AccountService;
import az.crbn.kanzopizza.ms.auth.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final UserMapper userMapper;
    private final SecurityUtil securityUtil;

    //    @Override
    //    public UserResponseDto getCurrent() {
    //        return userMapper.entityToResponseDto(securityUtil.getCurrentUser());
    //    }
}
