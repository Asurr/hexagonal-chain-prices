package com.microservice.test.infrastructure.rest.exception;

public class PriceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PriceNotFoundException(String msg) {
            super(msg);
        }
}


