package com.socgen.mowit.domain;


import java.time.temporal.ValueRange;

public record Lawn(int upperRightCorner, int bottomRightCorner) {

    public static final long ONE_STEP = 1L;
    public static final int ZERO = 0;

    public boolean isInsideLAwnAfterForward(EnumDirection direction, MowerPosition position) {
        switch (direction) {
            case NORTH, SOUTH -> {
                return ValueRange.of(ZERO, this.bottomRightCorner()).isValidIntValue(position.getY() + ONE_STEP);
            }
            case EAST, WEST -> {
                return ValueRange.of(ZERO, this.upperRightCorner()).isValidIntValue(position.getX() + ONE_STEP);
            }
            default -> {
                return false;
            }
        }
    }

}
