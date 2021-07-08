package az.crbn.kanzopizza.ms.auth.web.rest;

import az.crbn.kanzopizza.ms.auth.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;

    //    @GetMapping("/v1")
    //    public ResponseEntity<UserResponseDto> getCurrent() {
    //        log.trace("Get current account");
    //        return ResponseEntity.ok(accountService.getCurrent());
    //    }
}
