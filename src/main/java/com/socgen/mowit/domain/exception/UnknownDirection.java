package com.socgen.mowit.domain.exception;

import com.socgen.mowit.domain.EnumDirection;

public class UnknownDirection extends RuntimeException {
    public UnknownDirection(EnumDirection direction) {
        super(String.format("Undefined Direction %s", direction));
    }
}
