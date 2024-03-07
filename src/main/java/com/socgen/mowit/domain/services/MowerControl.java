package com.socgen.mowit.domain.services;

import com.socgen.mowit.domain.Lawn;
import com.socgen.mowit.domain.Mower;
import com.socgen.mowit.domain.converter.CommandConverter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class MowerControl {
    private final Lawn lawn;
    private final CommandConverter commandConverter;

    public void execute(Mower mower, String command) {
        this.commandConverter.map(command)
                        .forEach(enumInstruction -> {
                            switch (enumInstruction) {
                                case FORWARD -> moveForward(mower);
                                case LEFT -> mower.turnLeft();
                                case RIGHT -> mower.turnRight();
                                default ->  throw new UnsupportedOperationException("Invalid instruction "+ enumInstruction);
                            }
                        } );
    }

    private void moveForward(Mower mower) {
        if(mower.getPosition().getX() + 1 >= 0 && mower.getPosition().getX() + 1 <= lawn.bottomRightCorner()
                || mower.getPosition().getY() + 1 >= 0 && mower.getPosition().getY() + 1 <= lawn.upperRightCorner()) {
            mower.moveForward();
        }
    }
}
