package com.socgen.mowit.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mower {

    private MowerPosition position;
    private Direction direction;

    public void turnLeft() {
        switch (this.direction) {
            case W -> this.direction = Direction.S;
            case N -> this.direction = Direction.W;
            case E -> this.direction = Direction.N;
            case S -> this.direction = Direction.E;
        }
    }

    public void moveForward() {
        switch (this.direction) {
            case W -> this.getPosition().setX(this.getPosition().getX() - 1);
            case N -> this.getPosition().setY(this.getPosition().getY() + 1);
            case E -> this.getPosition().setX(this.getPosition().getX() + 1);
            case S -> this.getPosition().setY(this.getPosition().getY() - 1);
        }
    }

    public void turnRight() {
        switch (this.direction) {
            case W -> this.direction = Direction.N;
            case N -> this.direction = Direction.E;
            case E -> this.direction = Direction.S;
            case S -> this.direction = Direction.W;
        }
    }
}
