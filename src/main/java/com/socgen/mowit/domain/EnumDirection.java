package com.socgen.mowit.domain;

import com.socgen.mowit.domain.exception.UnknownDirection;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum EnumDirection {
    WEST("W"),
    NORTH("N"),
    EAST("E"),
    SOUTH("S");

    private final String code;

    public static EnumDirection getByCode(String code) {
        return Arrays.stream(EnumDirection.values())
                .filter(enumDirection -> enumDirection.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new UnknownDirection(code));
    }
}
