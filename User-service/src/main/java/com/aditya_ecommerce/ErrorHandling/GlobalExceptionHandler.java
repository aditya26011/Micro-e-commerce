package com.aditya_ecommerce.ErrorHandling;

import com.aditya_ecommerce.exceptions.DuplicateEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ApiError> handleDuplicateEmailException(DuplicateEmailException duplicateEmailException){
        ApiError apiError=new ApiError();
        apiError.setError(duplicateEmailException.getLocalizedMessage());
        apiError.setHttpStatusCode(HttpStatus.CONFLICT);

        return new ResponseEntity<>(apiError,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<String> errors= exception
                                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(e->e.getDefaultMessage())
                .toList();

        ApiError apiError= new ApiError();
        apiError.setSuberrors(errors);
        apiError.setHttpStatusCode(HttpStatus.BAD_REQUEST);
        apiError.setError("Input validation Error ");

        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);

    }
}
