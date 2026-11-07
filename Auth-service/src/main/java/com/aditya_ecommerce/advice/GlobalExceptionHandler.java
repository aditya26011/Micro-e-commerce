package com.aditya_ecommerce.advice;

import com.aditya_ecommerce.exception.ResourceNotFoundException;
import com.aditya_ecommerce.exception.ServiceNotAvailable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handelResourceNotFoundException(Exception e){
        ApiError apiError=new ApiError();

        apiError.setError(e.getLocalizedMessage());
        apiError.setHttpStatusCode(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
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

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> handelBadCredentialException(Exception e){
        ApiError apiError=new ApiError();
        apiError.setHttpStatusCode(HttpStatus.UNAUTHORIZED);
        apiError.setError(e.getLocalizedMessage());

        return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ServiceNotAvailable.class)
    public ResponseEntity<ApiError> handelServiceNotAvailabelException(Exception e){
        ApiError apiError=new ApiError();
        apiError.setError(e.getLocalizedMessage());
        apiError.setHttpStatusCode(HttpStatus.SERVICE_UNAVAILABLE);

        return new ResponseEntity<>(apiError,HttpStatus.SERVICE_UNAVAILABLE);
    }


}
