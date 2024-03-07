package com.socgen.mowit.domain.converter;

import com.socgen.mowit.domain.EnumInstruction;
import com.socgen.mowit.domain.exception.UnknownInstruction;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.socgen.mowit.domain.EnumInstruction.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandConverterTest {

    private final CommandConverter commandConverter = new CommandConverter();

    @Test
    void GIVEN_command_is_GADAGAGAA_WHEN_map_THEN_return_9_instruction_list() {
        //GIVEN
        var command = "GADAGAGAA";

        //WHEN
        List<EnumInstruction> instructionList = commandConverter.map(command);

        //THEN
        assertThat(instructionList)
                .hasSize(9)
                .isEqualTo(List.of(LEFT, FORWARD, RIGHT, FORWARD, LEFT, FORWARD, LEFT, FORWARD, FORWARD));
    }

    @Test
    void GIVEN_command_is_empty_WHEN_map_THEN_return_empty_list() {
        assertThat(commandConverter.map(""))
                .isEmpty();
    }

    @Test
    void GIVEN_command_T_WHEN_map_THEN_throw_exception() {
        assertThatThrownBy(() -> commandConverter.map("GTD"))
                        .isExactlyInstanceOf(UnknownInstruction.class)
                                .hasMessage("No Instruction present for T");
    }
}