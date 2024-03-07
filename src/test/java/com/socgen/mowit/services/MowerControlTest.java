package com.socgen.mowit.services;

import com.socgen.mowit.domain.Direction;
import com.socgen.mowit.domain.Lawn;
import com.socgen.mowit.domain.Mower;
import com.socgen.mowit.domain.MowerPosition;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MowerControlTest {

    private final MowerControl mowerControl = new MowerControl(new Lawn(5,5));

    @Test
    void should_move_the_mower_to_position_1_3_with_N_Direction_when_execute_with_GAGAGAGAA_commands() {
        var mowerInitialPosition = new MowerPosition(1, 2);
        var mowerOne = new Mower(mowerInitialPosition, Direction.N);
        String command = "GAGAGAGAA";

        mowerControl.execute(mowerOne, command);

        assertAll("Assert that the mower finale Position is  1 3 N",() -> {
            assertEquals(1, mowerOne.getPosition().getX());
            assertEquals(3, mowerOne.getPosition().getY());
            assertEquals(Direction.N, mowerOne.getDirection());
        });
    }

    @Test
    void should_move_the_mower_to_position_5_1_with_E_Direction_when_execute_with_AADAADADDA_commands() {
        var mowerInitialPosition = new MowerPosition(3, 3);
        var mowerOne = new Mower(mowerInitialPosition, Direction.E);
        String command = "AADAADADDA";

        mowerControl.execute(mowerOne, command);

        assertAll("Assert that the mower finale Position is  5 1 E",() -> {
            assertEquals(5, mowerOne.getPosition().getX());
            assertEquals(1, mowerOne.getPosition().getY());
            assertEquals(Direction.E, mowerOne.getDirection());
        });
    }


    @Test
    void should_throw_Exception_when_instruction_invalid() {
        var mowerInitialPosition = new MowerPosition(1, 3);
        var mowerOne = new Mower(mowerInitialPosition, Direction.E);
        String command = "XGA";

        Assertions.assertThatThrownBy(() -> mowerControl.execute(mowerOne, command))
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Invalid instruction X");

        assertAll("Assert that the mower finale Position still  1 3 E",() -> {
            assertEquals(1, mowerOne.getPosition().getX());
            assertEquals(3, mowerOne.getPosition().getY());
            assertEquals(Direction.E, mowerOne.getDirection());
        });
    }
}