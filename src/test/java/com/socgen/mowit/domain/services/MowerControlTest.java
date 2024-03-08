package com.socgen.mowit.domain.services;

import com.socgen.mowit.domain.EnumDirection;
import com.socgen.mowit.domain.Lawn;
import com.socgen.mowit.domain.Mower;
import com.socgen.mowit.domain.MowerPosition;
import com.socgen.mowit.domain.converter.CommandConverter;
import com.socgen.mowit.domain.exception.UnknownInstruction;
import com.socgen.mowit.domain.port.MowerPrinterPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.socgen.mowit.domain.EnumDirection.*;
import static com.socgen.mowit.domain.EnumInstruction.*;
import static com.socgen.mowit.domain.EnumInstruction.FORWARD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MowerControlTest {
    @Spy
    private Lawn lawn = new Lawn(5, 5);

    @Mock
    private CommandConverter commandConverter;

    @Mock
    private MowerPrinterPort mowerPrinterPort;

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
        assertAll("Assert that the mower finale Position is  1 3 N", () -> {
            assertEquals(1, mower.getPosition().getX());
            assertEquals(3, mower.getPosition().getY());
            assertEquals(EnumDirection.NORTH, mower.getDirection());
        });
        verify(commandConverter, Mockito.only()).map(command);
        var enumDirectionArgumentCaptor = ArgumentCaptor.forClass(EnumDirection.class);
        verify(lawn, times(5))
                .isInsideLAwnAfterForward(enumDirectionArgumentCaptor.capture(), any(MowerPosition.class));

        assertThat(enumDirectionArgumentCaptor.getAllValues())
                .hasSize(5)
                .hasToString("[WEST, SOUTH, EAST, NORTH, NORTH]");
    }

    @Test
    void should_move_the_mower_to_position_5_1_with_EAST_Direction_when_execute_with_AADAADADDA_command() {
        //GIVEN
        var mowerInitialPosition = new MowerPosition(3, 3);
        var mower = new Mower(mowerInitialPosition, EAST);
        var command = "AADAADADDA";
        when(commandConverter.map(command))
                .thenReturn(List.of(FORWARD, FORWARD, RIGHT, FORWARD, FORWARD, RIGHT, FORWARD, RIGHT, RIGHT, FORWARD));

        //WHEN
        mowerControl.execute(mower, command);

        //THEN
        assertAll("Assert that the mower finale Position is  5 1 E", () -> {
            assertEquals(5, mower.getPosition().getX());
            assertEquals(1, mower.getPosition().getY());
            assertEquals(EAST, mower.getDirection());
        });
        verify(commandConverter, Mockito.only()).map(command);
        var enumDirectionArgumentCaptor = ArgumentCaptor.forClass(EnumDirection.class);
        verify(lawn, times(6))
                .isInsideLAwnAfterForward(enumDirectionArgumentCaptor.capture(), any(MowerPosition.class));
        assertThat(enumDirectionArgumentCaptor.getAllValues())
                .hasSize(6)
                .hasToString("[EAST, EAST, SOUTH, SOUTH, WEST, EAST]");
    }


    @Test
    void should_throw_UnknownInstruction_when_mapper_throw_UnknownInstruction() {
        //GIVEN
        var mowerInitialPosition = new MowerPosition(1, 3);
        var mowerOne = new Mower(mowerInitialPosition, EAST);
        var command = "XGA";
        when(commandConverter.map(command))
                .thenThrow(new UnknownInstruction("X"));

        //WHEN THEN
        Assertions.assertThatThrownBy(() -> mowerControl.execute(mowerOne, command))
                .isExactlyInstanceOf(UnknownInstruction.class)
                .hasMessage("No Instruction present for X");

        assertAll("Assert that the mower finale Position still  1 3 E", () -> {
            assertEquals(1, mowerOne.getPosition().getX());
            assertEquals(3, mowerOne.getPosition().getY());
            assertEquals(EAST, mowerOne.getDirection());
        });
        verify(commandConverter, Mockito.only()).map(command);
        verifyNoInteractions(lawn);
    }


    @Test
    void should_not_move_the_mower_when_execute_with_AADAADADDA_commands() {
        //GIVEN
        var mowerInitialPosition = new MowerPosition(5, 5);
        var mowerOne = new Mower(mowerInitialPosition, EAST);
        var command = "AA";
        when(commandConverter.map(command)).thenReturn(List.of(FORWARD, FORWARD));

        //WHEN
        mowerControl.execute(mowerOne, command);

        //THEN
        assertAll("Assert that the mower finale Position still  5 5 E", () -> {
            assertEquals(5, mowerOne.getPosition().getX());
            assertEquals(5, mowerOne.getPosition().getY());
            assertEquals(EAST, mowerOne.getDirection());
        });
        verify(commandConverter, Mockito.only()).map(command);

    }

    @Test
    void should_invoke_mowerPrinterPort_when_printMowerInformation() {
        //GIVEN
        var mowerInitialPosition = new MowerPosition(5, 5);
        var mower = new Mower(mowerInitialPosition, EAST);

        //WHEN
        mowerControl.printMowerInformation(mower);

        //THEN
        verify(mowerPrinterPort,only()).printMower(mower);
    }
}