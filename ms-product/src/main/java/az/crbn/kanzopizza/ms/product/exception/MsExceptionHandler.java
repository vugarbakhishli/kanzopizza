package az.crbn.kanzopizza.ms.product.exception;

import static az.crbn.common.constants.HttpResponseConstants.ERROR;
import static az.crbn.common.constants.HttpResponseConstants.ERRORS;
import static az.crbn.common.constants.HttpResponseConstants.MESSAGE;
import static az.crbn.common.constants.HttpResponseConstants.PATH;
import static az.crbn.common.constants.HttpResponseConstants.STATUS;

import az.crbn.common.exception.GlobalExceptionHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.RetryableException;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class MsExceptionHandler extends GlobalExceptionHandler {
    private final ObjectMapper objectMapper;

    @ExceptionHandler(RetryableException.class)
    public ResponseEntity<Map<String, Object>> handleFeignExceptions(RetryableException ex, WebRequest webRequest) {
        HttpStatus httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
        Map<String, Object> attributes = getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
        attributes.put(STATUS, httpStatus.value());
        attributes.put(ERROR, httpStatus.getReasonPhrase());
        attributes.put(MESSAGE, ex.getMessage());
        attributes.put(ERRORS, List.of());
        attributes.put(PATH, ((ServletWebRequest) webRequest).getRequest().getRequestURI());
        return new ResponseEntity<>(attributes, httpStatus);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Map<String, Object>> handleFeignExceptions(FeignException ex, WebRequest webRequest) {
        try {
            HttpStatus httpStatus = HttpStatus.valueOf(ex.status());
            Map<String, Object> objectMap = objectMapper.readValue(ex.contentUTF8(), Map.class);
            objectMap.put(PATH, ((ServletWebRequest) webRequest).getRequest().getRequestURI());
            return new ResponseEntity<>(objectMap, httpStatus);
        } catch (JsonProcessingException exp) {
            log.error("JsonProcessingException occurred!", exp);
            return ofType(webRequest, HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }
}
