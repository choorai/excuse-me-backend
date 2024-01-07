package choorai.excuseme.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class CommonException extends RuntimeException {

    private static final String PROPERTY_VALUE = "Property: %s, Value: %s ";
    private static final String VALUE_DELIMITER = " / ";

    private final ErrorCode errorCode;
    private final Map<String, Object> additionalInfos;

    public CommonException(final ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.additionalInfos = null;
    }

    public String getInputValue() {
        return Objects.requireNonNull(additionalInfos).entrySet()
                .stream()
                .map(entry -> String.format(PROPERTY_VALUE, entry.getKey(), entry.getValue().toString()))
                .collect(Collectors.joining(VALUE_DELIMITER));
    }

    public boolean isInputFieldsNUll() {
        return additionalInfos == null;
    }

    public String getCode() {
        return errorCode.getCode();
    }

    public String getMessage() {
        return errorCode.getMessage();
    }
}
