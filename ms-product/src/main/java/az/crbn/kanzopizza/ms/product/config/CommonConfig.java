package az.crbn.kanzopizza.ms.product.config;

import az.crbn.common.security.auth.service.SecurityService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SecurityService.class})
public class CommonConfig {
}
