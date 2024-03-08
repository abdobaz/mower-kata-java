package com.socgen.mowit.domain;

import com.socgen.mowit.domain.exception.UnknownInstruction;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnumInstructionTest {

    @Test
    void test_get_code_instruction() {
        assertThat(EnumInstruction.RIGHT.getCode()).isEqualTo("D");
        assertThat(EnumInstruction.LEFT.getCode()).isEqualTo("G");
        assertThat(EnumInstruction.FORWARD.getCode()).isEqualTo("A");
    }

    @Test
    void test_get_enum_instruction_ByCode() {
        assertThat(EnumInstruction.getByCode("D")).isEqualTo(EnumInstruction.RIGHT);

        assertThat(EnumInstruction.getByCode("G")).isEqualTo(EnumInstruction.LEFT);

        assertThat(EnumInstruction.getByCode("A")).isEqualTo(EnumInstruction.FORWARD);

        assertThatThrownBy(() -> EnumInstruction.getByCode("T"))
                .isExactlyInstanceOf(UnknownInstruction.class)
                .hasMessage("No Instruction present for T");
    }
}