package com.socgen.mowit.domain;

public class Mower {
    private Direction direction;

    public Mower(Direction direction) {
        this.direction = direction;
    }

    public void turnLeft() {
        switch (this.direction) {
            case W -> this.direction = Direction.S;
            case N -> this.direction = Direction.W;
            case E -> this.direction = Direction.N;
            case S -> this.direction = Direction.E;
        }
    }

    public void moveForward() {

    }

    public void turnRight() {
        switch (this.direction) {
            case W -> this.direction = Direction.N;
            case N -> this.direction = Direction.E;
            case E -> this.direction = Direction.S;
            case S -> this.direction = Direction.W;
        }
    }

    public Direction getDirection() {
        return this.direction;
    }
}
