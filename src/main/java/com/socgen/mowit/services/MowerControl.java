package com.socgen.mowit.services;

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
                        case 'A' -> mower.moveForward();
                        case 'G' -> mower.turnLeft();
                        case 'D' -> mower.turnRight();
                        default ->  throw new UnsupportedOperationException("Invalid instruction "+ (char)instruction);
                    }
                });
    }
}
