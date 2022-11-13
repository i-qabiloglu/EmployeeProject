package iqabiloglu.employeems.model.exception;


import lombok.Getter;

@Getter
public class AlreadyExistException extends RuntimeException {

    private final String code;

    public AlreadyExistException(String code, String message) {
        super(message);
        this.code = code;
    }

}
