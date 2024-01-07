package choorai.excuseme.global.exception;

import choorai.excuseme.global.exception.dto.CustomExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_LOG_FORMAT = "url={}, errorCode={}, errorMessage={}";
    private static final String ERROR_LOG_FORMAT_WITH_WRONG_INPUT = "url={}, errorCode={}, errorMessage={}, errorInput={}";

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<CustomExceptionResponse> handleException(
            final CommonException exception,
            final HttpServletRequest request
    ) {
        trackLog(exception, request);
        return CustomExceptionResponse.toResponseEntity(exception.getErrorCode());
    }

    private void trackLog(final CommonException exception, final HttpServletRequest request) {
        if (exception.isInputFieldsNUll()) {
            log.error(ERROR_LOG_FORMAT, request.getRequestURL(), exception.getCode(), exception.getMessage());
            return;
        }
        log.error(ERROR_LOG_FORMAT_WITH_WRONG_INPUT, request.getRequestURL(), exception.getCode(), exception.getMessage(), exception.getInputValue());
    }
}
