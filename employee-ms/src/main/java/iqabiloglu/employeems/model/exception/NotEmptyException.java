package iqabiloglu.employeems.model.exception;

import lombok.Getter;

@Getter
public class NotEmptyException extends RuntimeException {
    private final String code;

    public NotEmptyException(String code, String message) {
        super(message);
        this.code = code;
    }
}
