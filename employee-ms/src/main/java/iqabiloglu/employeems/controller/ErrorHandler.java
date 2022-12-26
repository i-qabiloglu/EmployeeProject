package iqabiloglu.employeems.controller;

import iqabiloglu.employeems.model.exception.AlreadyExistException;
import iqabiloglu.employeems.model.exception.NotEmptyException;
import iqabiloglu.employeems.model.exception.NotFoundException;
import iqabiloglu.employeems.model.view.ExceptionView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

import static iqabiloglu.employeems.util.constant.ExceptionConstants.UNEXPECTED_EXCEPTION_CODE;
import static iqabiloglu.employeems.util.constant.ExceptionConstants.UNEXPECTED_EXCEPTION_MESSAGE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionView handle(Exception ex) {
        log.error("Exception: ", ex);
        return new ExceptionView(UNEXPECTED_EXCEPTION_CODE, UNEXPECTED_EXCEPTION_MESSAGE);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionView handle(NotFoundException ex) {
        log.error("NotFoundException: ", ex);
        return new ExceptionView(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionView handle(AlreadyExistException ex) {
        log.error("AlreadyExistException: ", ex);
        return new ExceptionView(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(NotEmptyException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionView handle(NotEmptyException ex) {
        log.error("NotEmptyException: ", ex);
        return new ExceptionView(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public List<ExceptionView> handleValidation(MethodArgumentNotValidException ex) {

        log.error("ValidationException: ", ex);
        List<ExceptionView> errors = new ArrayList<>();
        ex.getBindingResult()
          .getFieldErrors()
          .forEach(err -> errors.add(new ExceptionView(err.getField()
                                                          .toUpperCase() + "_NOT_VALID",
                                                       err.getDefaultMessage())));
        return errors;
    }


}
