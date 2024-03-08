package com.socgen.mowit.domain.services;

import com.socgen.mowit.domain.EnumInstruction;
import com.socgen.mowit.domain.Lawn;
import com.socgen.mowit.domain.Mower;
import com.socgen.mowit.domain.converter.CommandConverter;
import com.socgen.mowit.domain.exception.UnknownInstruction;
import com.socgen.mowit.domain.port.MowerPrinterPort;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;


@RequiredArgsConstructor
public class MowerControl {
    private final Lawn lawn;
    private final CommandConverter commandConverter;
    private final MowerPrinterPort mowerPrinterPort;

    public void execute(Mower mower, String command) {
        this.commandConverter.map(command)
                            .forEach(executeInstruction(mower));
    }

    public void printMowerInformation(Mower mower) {
        mowerPrinterPort.printMower(mower);
    }

    private Consumer<EnumInstruction> executeInstruction(Mower mower) {
        return enumInstruction -> {
            switch (enumInstruction) {
                case FORWARD -> moveForward(mower);
                case LEFT -> mower.turnLeft();
                case RIGHT -> mower.turnRight();
                default -> throw new UnknownInstruction(enumInstruction.getCode());
            }
        };
    }

    private void moveForward(Mower mower) {
        if(this.lawn.isInsideLAwnAfterForward(mower.getDirection(),mower.getPosition())) {
            mower.moveForward();
        }
    }
}
