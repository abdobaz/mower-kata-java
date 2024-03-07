package com.socgen.mowit.domain.services;

import com.socgen.mowit.domain.Lawn;
import com.socgen.mowit.domain.Mower;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class MowerControl {
    private final Lawn lawn;

    public void execute(Mower mower, String command) {
       command.chars()
                .forEach( instruction -> {

                    switch ((char)instruction) {
                        case 'A' -> moveForward(mower);
                        case 'G' -> mower.turnLeft();
                        case 'D' -> mower.turnRight();
                        default ->  throw new UnsupportedOperationException("Invalid instruction "+ (char)instruction);
                    }
                });
    }

    private void moveForward(Mower mower) {
        if(mower.getPosition().getX() + 1 >= 0 && mower.getPosition().getX() + 1 <= lawn.bottomRightCorner()
                || mower.getPosition().getY() + 1 >= 0 && mower.getPosition().getY() + 1 <= lawn.upperRightCorner()) {
            mower.moveForward();
        }
    }
}
