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

}
