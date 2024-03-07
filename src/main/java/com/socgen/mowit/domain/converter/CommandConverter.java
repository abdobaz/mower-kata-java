package com.socgen.mowit.domain.converter;

import com.socgen.mowit.domain.EnumInstruction;

import java.util.List;

public class CommandConverter {
    public List<EnumInstruction> map(String command) {
        return command.chars()
                .mapToObj(Character::toString)
                .map(EnumInstruction::getByCode)
                .toList();
    }
}
