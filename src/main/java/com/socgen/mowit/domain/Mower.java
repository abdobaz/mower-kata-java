package com.socgen.mowit.domain;

import javax.lang.model.element.ModuleElement;

public class Mower {
    private Direction direction;

    public Mower(Direction direction) {
        this.direction = direction;
    }

    public void turnLeft() {
        this.direction = Direction.W;
    }

    public void moveForward() {

    }

    public void turnRight() {
        if(Direction.N.equals(this.direction)) {
            this.direction = Direction.E;
        } else {
            this.direction = Direction.S;
        }
    }

    public Direction getDirection() {
        return this.direction;
    }
}
