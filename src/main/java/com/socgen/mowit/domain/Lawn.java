package com.socgen.mowit.domain;



public record Lawn(int upperRightCorner, int bottomRightCorner) {
    public boolean isInsideLAwnAfterForward(EnumDirection direction, MowerPosition position) {
        switch (direction) {
            case NORTH, SOUTH -> {
                return position.getY() + 1 >= 0 && position.getY() + 1 <= this.bottomRightCorner();
            }
            case EAST, WEST -> {
                return position.getX() + 1 >= 0 && position.getX() + 1 <= this.upperRightCorner();
            }
            default -> {
                return false;
            }
        }
    }

}
