package com.socgen.mowit.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.NoSuchElementException;

@Getter
@AllArgsConstructor
public enum EnumInstruction {
    RIGHT("D"),
    LEFT("G"),
    FORWARD("A");


    private final String code;

    public static EnumInstruction getByCode(String code) {
        return Arrays.stream(EnumInstruction.values()).filter(enumInstruction -> enumInstruction.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("No Instruction present for %s", code)));
    }
}
