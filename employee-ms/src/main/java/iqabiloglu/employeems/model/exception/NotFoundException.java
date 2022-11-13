package iqabiloglu.employeems.model.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
public class NotFoundException extends RuntimeException {

    private final String code;

    public NotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }
}
