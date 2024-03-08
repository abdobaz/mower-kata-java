package com.socgen.mowit.domain;

import com.socgen.mowit.domain.exception.UnknownDirection;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnumDirectionTest {

    @Test
    void test_get_code_direction() {
        assertThat(EnumDirection.WEST.getCode()).isEqualTo("W");
        assertThat(EnumDirection.NORTH.getCode()).isEqualTo("N");
        assertThat(EnumDirection.EAST.getCode()).isEqualTo("E");
        assertThat(EnumDirection.SOUTH.getCode()).isEqualTo("S");
    }

    @Test
    void test_get_enum_direction_ByCode() {
        assertThat(EnumDirection.getByCode("W")).isEqualTo(EnumDirection.WEST);

        assertThat(EnumDirection.getByCode("N")).isEqualTo(EnumDirection.NORTH);

        assertThat(EnumDirection.getByCode("E")).isEqualTo(EnumDirection.EAST);

        assertThat(EnumDirection.getByCode("S")).isEqualTo(EnumDirection.SOUTH);

        assertThatThrownBy(() -> EnumDirection.getByCode("X"))
                .isExactlyInstanceOf(UnknownDirection.class)
                .hasMessage("Undefined Direction X");
    }
}