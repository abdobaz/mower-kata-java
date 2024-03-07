package com.socgen.mowit.domain;

public class Mower {
    private Direction direction;
    private int xPosition = 0;
    private int yPosition = 0;

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
        this.xPosition++;
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

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }
}
