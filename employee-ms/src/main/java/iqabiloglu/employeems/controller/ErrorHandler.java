package iqabiloglu.employeems.controller;

import iqabiloglu.employeems.model.exception.AlreadyExistException;
import iqabiloglu.employeems.model.exception.NotFoundException;
import iqabiloglu.employeems.model.view.ExceptionView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static iqabiloglu.employeems.model.constant.ExceptionConstants.UNEXPECTED_EXCEPTION_CODE;
import static iqabiloglu.employeems.model.constant.ExceptionConstants.UNEXPECTED_EXCEPTION_MESSAGE;

@RestControllerAdvice
@Slf4j
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionView handle(Exception ex) {
        log.info("Exceptin: ", ex);
        return new ExceptionView(UNEXPECTED_EXCEPTION_CODE, UNEXPECTED_EXCEPTION_MESSAGE);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionView handle(NotFoundException ex) {
        log.info("NotFoundException: ", ex);
        return new ExceptionView(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionView handle(AlreadyExistException ex) {
        log.info("AlreadyExistException: ", ex);
        return new ExceptionView(ex.getCode(), ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {

        log.error("ValidationException: ", ex);
        List<ExceptionView> errors = new ArrayList<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(err -> errors.add(new ExceptionView(err.getField()
                        .toUpperCase() + "_NOT_VALID", err.getDefaultMessage())));
        return ResponseEntity.status(status)
                .body(errors);

    }

}
