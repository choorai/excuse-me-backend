package choorai.excuseme.member.exception;

import choorai.excuseme.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum MemberErrorCode implements ErrorCode {

    WRONG_PASSWORD(HttpStatus.BAD_REQUEST, "잘못된 입력", "올바르지 않은 패스워드 입니다."),
    ALREADY_EXIST(HttpStatus.BAD_REQUEST, "잘못된 입력", "이미 존재하는 회원입니다."),
    USERNAME_NOT_FOUND(HttpStatus.BAD_REQUEST, "잘못된 입력", "해당 유저를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    MemberErrorCode(HttpStatus httpStatus, String errorCode, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
