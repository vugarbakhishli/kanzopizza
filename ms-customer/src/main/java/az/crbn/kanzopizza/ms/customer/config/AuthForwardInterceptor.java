package az.crbn.kanzopizza.ms.customer.config;

import static az.crbn.common.constants.HttpConstants.AUTH_HEADER;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class AuthForwardInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());

        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            template.header(AUTH_HEADER, request.getHeader(AUTH_HEADER));
        }
    }
}
