package com.socgen.mowit.domain;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MowerTest {
    private Mower mower;

    @BeforeEach
    void init() {
        mower = new Mower("N");

    }

    @Test
    void should_set_orientation_to_w_when_the_mower_turn_left() {

        //WHEN
        mower.turnLeft();

        //THEN
        Assertions.assertThat(mower.getDirection()).isEqualTo("W");
    }

    @Test
    void should_set_orientation_to_E_when_the_mower_turn_right() {
        //WHEN
        mower.turnRight();

        //THEN
        Assertions.assertThat(mower.getDirection()).isEqualTo("E");
    }

    @Test
    void should_set_orientation_to_S_when_the_mower_turn_right_twice() {
        //WHEN
        mower.turnRight();
        mower.turnRight();

        //THEN
        Assertions.assertThat(mower.getDirection()).isEqualTo("S");
    }

    @Test
    void should_not_set_orientation_to_E_when_the_mower_move_forward() {
        //WHEN
        mower.moveForward();

        //THEN
        Assertions.assertThat(mower.getDirection()).isEqualTo("N");
    }

}
