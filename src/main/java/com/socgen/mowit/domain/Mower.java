package com.socgen.mowit.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mower {
    public static final int ONE_STEP = 1;
    private MowerPosition position;
    private EnumDirection direction;

    public void turnLeft() {
        switch (this.direction) {
            case WEST -> this.direction = EnumDirection.SOUTH;
            case NORTH -> this.direction = EnumDirection.WEST;
            case EAST -> this.direction = EnumDirection.NORTH;
            case SOUTH -> this.direction = EnumDirection.EAST;
        }
    }

    public void moveForward() {
        var currentXPosition = this.getPosition().getX();
        var currentYPosition = this.getPosition().getY();
        switch (this.direction) {
            case WEST -> this.getPosition().setX(currentXPosition - ONE_STEP);
            case NORTH -> this.getPosition().setY(currentYPosition + ONE_STEP);
            case EAST -> this.getPosition().setX(currentXPosition + ONE_STEP);
            case SOUTH -> this.getPosition().setY(currentYPosition - ONE_STEP);
        }
    }

    public void turnRight() {
        switch (this.direction) {
            case WEST -> this.direction = EnumDirection.NORTH;
            case NORTH -> this.direction = EnumDirection.EAST;
            case EAST -> this.direction = EnumDirection.SOUTH;
            case SOUTH -> this.direction = EnumDirection.WEST;
        }
    }
}
