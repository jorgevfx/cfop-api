package com.george.cfopapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ExceptionHandler({CustomErrorHandler.class})
    public ResponseEntity<?> handleOllNotFound(CustomErrorHandler ex){
        return new ResponseEntity<>(
                new ErrorDetails(
                        new Date(),
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleAllExceptions(Exception ex){
        return new ResponseEntity<>(
                new ErrorDetails(
                        new Date(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String error = "There was an error processing your request.";
        return buildResponseEntity(new ErrorDetails(new Date(), status.value(), error));
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorDetails errorDetails) {
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
