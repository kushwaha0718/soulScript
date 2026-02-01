package com.cognexa.soulScript.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.cognexa.soulScript.entity.ExceptionModel;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ExceptionModel generateExceptionResponse(Integer status, String error, String message) {
        ExceptionModel exception = new ExceptionModel();
        exception.setStatus(status);
        exception.setError(error);
        exception.setMessage(message);
        exception.setTimestamp(LocalDateTime.now());
        return exception;
    }

    @ExceptionHandler(JournalNotFoundException.class)
    public ResponseEntity<?> handleJournalNotFoundException(JournalNotFoundException ex) {

        return new ResponseEntity<>(
                generateExceptionResponse(
                        HttpStatus.NOT_FOUND.value(),
                        "No Journal found",
                        ex.getMessage()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> fieldValidation(MethodArgumentNotValidException ex){
        return new ResponseEntity<>(
                generateExceptionResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        "field validation failed",
                        ex.getBindingResult()
                                .getFieldErrors()
                                .get(0)
                                .getDefaultMessage()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(
                generateExceptionResponse(
                        HttpStatus.NOT_FOUND.value(),
                        "User not found exception",
                        ex.getMessage()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> userAlreadyExistsException(UserAlreadyExistsException ex) {
        return new ResponseEntity<>(
                generateExceptionResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        "user already exist",
                        ex.getMessage()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {
        return new ResponseEntity<>(
                generateExceptionResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        ex.getMessage(),
                        ex.getMessage()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }


}
