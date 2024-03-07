package com.socgen.mowit.domain;

public class Mower {
    private final String direction;

    public Mower(String direction) {
        this.direction = direction;
    }

    public void turnLeft() {
        throw new UnsupportedOperationException("Not yet Implemented");
    }

    public void moveForward() {
        throw new UnsupportedOperationException("Not yet Implemented");
    }

    public void turnRight() {
        throw new UnsupportedOperationException("Not yet Implemented");
    }

    public String getDirection() {
        return this.direction;
    }
}
