package az.crbn.kanzopizza.ms.auth.web.rest;

import az.crbn.kanzopizza.ms.auth.service.AuthService;
import az.crbn.kanzopizza.ms.auth.service.dto.auth.JwtTokenDto;
import az.crbn.kanzopizza.ms.auth.service.dto.auth.LoginRequestDto;
import az.crbn.kanzopizza.ms.auth.service.dto.auth.RegisterRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/v1/register")
    public ResponseEntity<Void> register(@RequestBody @Validated RegisterRequestDto registerRequestDto) {
        log.trace("Register user: {}", registerRequestDto);
        authService.register(registerRequestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/v1/login")
    public ResponseEntity<JwtTokenDto> login(@RequestBody @Validated LoginRequestDto loginRequestDto) {
        log.trace("Login user: {}", loginRequestDto);
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }
}
