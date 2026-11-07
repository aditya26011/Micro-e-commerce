package com.aditya_ecommerce.exception;

public class ServiceNotAvailable extends RuntimeException {
    public ServiceNotAvailable(String message) {
        super(message);
    }
}
