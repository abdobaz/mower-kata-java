package com.socgen.mowit.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mower {

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
            case WEST -> this.getPosition().setX(currentXPosition - 1);
            case NORTH -> this.getPosition().setY(currentYPosition + 1);
            case EAST -> this.getPosition().setX(currentXPosition + 1);
            case SOUTH -> this.getPosition().setY(currentYPosition - 1);
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
