package com.socgen.mowit.domain;

import com.socgen.mowit.domain.exception.UnknownInstruction;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

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
                .orElseThrow(() -> new UnknownInstruction(code));
    }
}
