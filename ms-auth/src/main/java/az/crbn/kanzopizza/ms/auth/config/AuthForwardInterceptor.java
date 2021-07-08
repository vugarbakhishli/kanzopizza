package az.crbn.kanzopizza.ms.auth.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static az.crbn.common.constants.HttpConstants.AUTH_HEADER;

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
