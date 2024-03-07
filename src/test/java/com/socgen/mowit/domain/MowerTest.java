package com.socgen.mowit.domain;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MowerTest {
    private Mower mower;

    @BeforeEach
    void init() {
        mower = new Mower(Direction.N);
    }

    @ParameterizedTest(name = "[{index}] Given mower with direction {0} WHEN turn right Then direction should be {1}")
    @MethodSource("turnRightArgumentsTest")
    void should_set_orientation_when_the_mower_turn_right(Direction currentDirection, Direction expectedDirection) {
        //GIVEN
        var myMower = new Mower(currentDirection) ;

        //WHEN
        myMower.turnRight();

        //THEN
        Assertions.assertThat(myMower.getDirection()).isEqualTo(expectedDirection);
    }

    private static Stream<Arguments> turnRightArgumentsTest() {
        return Stream.of(
                Arguments.of(Direction.W, Direction.N), Arguments.of(Direction.N, Direction.E),
                Arguments.of(Direction.E, Direction.S), Arguments.of(Direction.S, Direction.W));
    }

    @ParameterizedTest(name = "[{index}] Given mower with direction {0} WHEN turn left Then direction should be {1}")
    @MethodSource("turnLeftArgumentsTest")
    void should_set_orientation_when_the_mower_turn_Left(Direction currentDirection, Direction expectedDirection) {
        //GIVEN
        var myMower = new Mower(currentDirection) ;

        //WHEN
        myMower.turnLeft();

        //THEN
        Assertions.assertThat(myMower.getDirection()).isEqualTo(expectedDirection);
    }

    private static Stream<Arguments> turnLeftArgumentsTest() {
        return Stream.of(
                Arguments.of(Direction.W, Direction.S), Arguments.of(Direction.S, Direction.E),
                Arguments.of(Direction.E, Direction.N), Arguments.of(Direction.N, Direction.W));
    }

    @Test
    void should_not_set_orientation_when_the_mower_move_forward() {
        //WHEN
        mower.moveForward();

        //THEN
        Assertions.assertThat(mower.getDirection()).isEqualTo(Direction.N);
    }


    @Test
    void should_add_1_to_y_position_when_the_mower_move_forward_and_direction_is_N() {
        //GIVEN
        var myMower = new Mower(Direction.N);

        //WHEN
        myMower.moveForward();

        //THEN
        Assertions.assertThat(myMower.getDirection()).isEqualTo(Direction.N);
        Assertions.assertThat(myMower.getxPosition()).isZero();
        Assertions.assertThat(myMower.getyPosition()).isEqualTo(1);
    }

    @Test
    void should_add_1_to_x_position_when_the_mower_move_forward_and_direction_is_E() {
        //GIVEN
        var myMower = new Mower(Direction.E);

        //WHEN
        myMower.moveForward();

        //THEN
        Assertions.assertThat(myMower.getDirection()).isEqualTo(Direction.E);
        Assertions.assertThat(myMower.getxPosition()).isEqualTo(1);
        Assertions.assertThat(myMower.getyPosition()).isZero();
    }

    @Test
    void should_minus_1_to_x_position_when_the_mower_move_forward_and_direction_is_W() {
        //GIVEN
        var myMower = new Mower(Direction.W);

        //WHEN
        myMower.moveForward();

        //THEN
        Assertions.assertThat(myMower.getDirection()).isEqualTo(Direction.W);
        Assertions.assertThat(myMower.getxPosition()).isEqualTo(-1);
        Assertions.assertThat(myMower.getyPosition()).isZero();
    }

    @Test
    void should_minus_1_to_Y_position_when_the_mower_move_forward_and_direction_is_S() {
        //GIVEN
        var myMower = new Mower(Direction.S);

        //WHEN
        myMower.moveForward();

        //THEN
        Assertions.assertThat(myMower.getDirection()).isEqualTo(Direction.S);
        Assertions.assertThat(myMower.getxPosition()).isZero();
        Assertions.assertThat(myMower.getyPosition()).isEqualTo(-1);
    }

}
