package com.socgen.mowit.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LawnTest {

    @Test
    void should_return_true_when_isInsideLAwnAfterForward_and_x_position_is_9_and_y_position_is_8_and_direction_is_NORTH() {
        var lawn = new Lawn(10,10);

        assertTrue(lawn.isInsideLAwnAfterForward(EnumDirection.NORTH, new MowerPosition(9,8)));
    }

    @Test
    void should_return_false_when_isInsideLAwnAfterForward_and_x_position_is_8_and_y_position_is_11_and_direction_is_SOUTH() {
        var lawn = new Lawn(10,10);

        assertFalse(lawn.isInsideLAwnAfterForward(EnumDirection.SOUTH,new MowerPosition(8,11)));
    }

    @Test
    void should_return_false_when_isInsideLAwnAfterForward_and_x_position_is_10_and_y_position_is_11_and_direction_is_EAST() {
        var lawn = new Lawn(10,10);

        assertFalse(lawn.isInsideLAwnAfterForward(EnumDirection.EAST,new MowerPosition(10,11)));
    }

    @Test
    void should_return_false_when_isInsideLAwnAfterForward_and_x_position_is_12_and_y_position_is_12_and_direction_is_WEST() {
        var lawn = new Lawn(10,10);

        assertFalse(lawn.isInsideLAwnAfterForward(EnumDirection.WEST,new MowerPosition(12,12)));
    }
}