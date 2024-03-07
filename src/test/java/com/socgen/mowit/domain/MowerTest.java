package com.socgen.mowit.domain;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class MowerTest {

    private final MowerPosition mowerPosition = new MowerPosition(0,0);

    @ParameterizedTest(name = "[{index}] Given mower with direction {0} WHEN turn right Then direction should be {1}")
    @MethodSource("turnRightArgumentsTest")
    void should_set_orientation_when_the_mower_turn_right(Direction currentDirection, Direction expectedDirection) {
        //GIVEN
        var myMower = new Mower(mowerPosition,currentDirection) ;

        //WHEN
        myMower.turnRight();

        //THEN
        assertThat(myMower.getDirection()).isEqualTo(expectedDirection);
        assertThat(myMower.getPosition().getX()).isZero();
        assertThat(myMower.getPosition().getY()).isZero();
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
        var myMower = new Mower(mowerPosition,currentDirection) ;

        //WHEN
        myMower.turnLeft();

        //THEN
        assertThat(myMower.getDirection()).isEqualTo(expectedDirection);
        assertThat(myMower.getPosition().getX()).isZero();
        assertThat(myMower.getPosition().getY()).isZero();
    }

    private static Stream<Arguments> turnLeftArgumentsTest() {
        return Stream.of(
                Arguments.of(Direction.W, Direction.S), Arguments.of(Direction.S, Direction.E),
                Arguments.of(Direction.E, Direction.N), Arguments.of(Direction.N, Direction.W));
    }

    @Test
    void should_increase_by_1_to_y_position_when_the_mower_move_forward_and_direction_is_N() {
        //GIVEN
        var myMower = new Mower(mowerPosition,Direction.N);

        //WHEN
        myMower.moveForward();

        //THEN
        assertThat(myMower.getDirection()).isEqualTo(Direction.N);
        assertThat(myMower.getPosition().getX()).isZero();
        assertThat(myMower.getPosition().getY()).isEqualTo(1);
    }

    @Test
    void should_increase_by_1_to_x_position_when_the_mower_move_forward_and_direction_is_E() {
        //GIVEN
        var myMower = new Mower(mowerPosition,Direction.E);

        //WHEN
        myMower.moveForward();

        //THEN
        assertThat(myMower.getDirection()).isEqualTo(Direction.E);
        assertThat(myMower.getPosition().getX()).isEqualTo(1);
        assertThat(myMower.getPosition().getY()).isZero();
    }

    @Test
    void should_decrease_by_1_from_x_position_when_the_mower_move_forward_and_direction_is_W() {
        //GIVEN
        var myMower = new Mower(mowerPosition, Direction.W);

        //WHEN
        myMower.moveForward();

        //THEN
        assertThat(myMower.getDirection()).isEqualTo(Direction.W);
        assertThat(myMower.getPosition().getX()).isEqualTo(-1);
        assertThat(myMower.getPosition().getY()).isZero();
    }

    @Test
    void should_decrease_by_1_from_Y_position_when_the_mower_move_forward_and_direction_is_S() {
        //GIVEN
        var myMower = new Mower(mowerPosition, Direction.S);

        //WHEN
        myMower.moveForward();

        //THEN
        assertThat(myMower.getDirection()).isEqualTo(Direction.S);
        assertThat(myMower.getPosition().getX()).isZero();
        assertThat(myMower.getPosition().getY()).isEqualTo(-1);
    }

}
