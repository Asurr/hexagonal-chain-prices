package com.microservice.test.domain.exception;

import java.io.Serial;

public class PriceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public PriceNotFoundException(String msg) {
            super(msg);
        }
}


