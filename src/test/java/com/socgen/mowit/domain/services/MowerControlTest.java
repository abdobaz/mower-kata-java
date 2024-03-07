package com.socgen.mowit.domain.services;

import com.socgen.mowit.domain.EnumDirection;
import com.socgen.mowit.domain.Lawn;
import com.socgen.mowit.domain.Mower;
import com.socgen.mowit.domain.MowerPosition;
import com.socgen.mowit.domain.converter.CommandConverter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.socgen.mowit.domain.EnumInstruction.*;
import static com.socgen.mowit.domain.EnumInstruction.FORWARD;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MowerControlTest {
    @Spy
    private Lawn lawn = new Lawn(5,5);

    @Mock
    private CommandConverter commandConverter;

    @InjectMocks
    private MowerControl mowerControl;

    @Test
    void should_move_the_mower_to_position_1_3_with_NORTH_Direction_when_execute_with_GAGAGAGAA_command() {
        //GIVEN
        var mowerInitialPosition = new MowerPosition(1, 2);
        var mower = new Mower(mowerInitialPosition, EnumDirection.NORTH);
        var command = "GAGAGAGAA";
        when(commandConverter.map(command))
                .thenReturn(List.of(LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, FORWARD));

        //WHEN
        mowerControl.execute(mower, command);

        //THEN
        assertAll("Assert that the mower finale Position is  1 3 N",() -> {
            assertEquals(1, mower.getPosition().getX());
            assertEquals(3, mower.getPosition().getY());
            assertEquals(EnumDirection.NORTH, mower.getDirection());
        });
        verify(commandConverter, Mockito.only()).map(command);
        verify(lawn,times(5)).bottomRightCorner();
    }

    @Test
    void should_move_the_mower_to_position_5_1_with_EAST_Direction_when_execute_with_AADAADADDA_command() {
        //GIVEN
        var mowerInitialPosition = new MowerPosition(3, 3);
        var mower = new Mower(mowerInitialPosition, EnumDirection.EAST);
        var command = "AADAADADDA";
        when(commandConverter.map(command))
                .thenReturn(List.of(FORWARD, FORWARD, RIGHT, FORWARD, FORWARD, RIGHT, FORWARD, RIGHT, RIGHT, FORWARD));

        //WHEN
        mowerControl.execute(mower, command);

        //THEN
        assertAll("Assert that the mower finale Position is  5 1 E",() -> {
            assertEquals(5, mower.getPosition().getX());
            assertEquals(1, mower.getPosition().getY());
            assertEquals(EnumDirection.EAST, mower.getDirection());
        });
        verify(commandConverter, Mockito.only()).map(command);
        verify(lawn,times(6)).bottomRightCorner();
    }


    @Test
    void should_throw_Exception_when_instruction_invalid() {
        //GIVEN
        var mowerInitialPosition = new MowerPosition(1, 3);
        var mowerOne = new Mower(mowerInitialPosition, EnumDirection.EAST);
        var command = "XGA";
        when(commandConverter.map(command))
                .thenThrow(new UnsupportedOperationException("No Instruction present for X"));

        //WHEN THEN
        Assertions.assertThatThrownBy(() -> mowerControl.execute(mowerOne, command))
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("No Instruction present for X");

        assertAll("Assert that the mower finale Position still  1 3 E",() -> {
            assertEquals(1, mowerOne.getPosition().getX());
            assertEquals(3, mowerOne.getPosition().getY());
            assertEquals(EnumDirection.EAST, mowerOne.getDirection());
        });
        verify(commandConverter, Mockito.only()).map(command);
        verifyNoInteractions(lawn);
    }


    @Test
    void should_not_move_the_mower_when_execute_with_AADAADADDA_commands() {
        //GIVEN
        var mowerInitialPosition = new MowerPosition(5, 5);
        var mowerOne = new Mower(mowerInitialPosition, EnumDirection.EAST);
        var command = "AA";
        when(commandConverter.map(command)).thenReturn(List.of(FORWARD, FORWARD));

        //WHEN
        mowerControl.execute(mowerOne, command);

        //THEN
        assertAll("Assert that the mower finale Position still  5 5 E",() -> {
            assertEquals(5, mowerOne.getPosition().getX());
            assertEquals(5, mowerOne.getPosition().getY());
            assertEquals(EnumDirection.EAST, mowerOne.getDirection());
        });
        verify(commandConverter, Mockito.only()).map(command);

    }
}