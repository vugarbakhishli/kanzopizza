package az.crbn.kanzopizza.ms.auth.service.impl;

import az.crbn.kanzopizza.ms.auth.repository.UserRepository;
import az.crbn.kanzopizza.ms.auth.service.UserService;
import az.crbn.kanzopizza.ms.auth.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
}
