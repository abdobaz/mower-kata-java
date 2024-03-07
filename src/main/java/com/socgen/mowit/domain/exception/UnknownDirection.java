package com.socgen.mowit.domain.exception;

public class UnknownDirection extends RuntimeException {
    public UnknownDirection(String direction) {
        super(String.format("Undefined Direction %s", direction));
    }
}
