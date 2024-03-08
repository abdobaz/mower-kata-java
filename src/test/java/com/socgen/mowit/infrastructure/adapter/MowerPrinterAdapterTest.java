package com.socgen.mowit.infrastructure.adapter;

import com.socgen.mowit.domain.Mower;
import com.socgen.mowit.domain.MowerPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.socgen.mowit.domain.EnumDirection.EAST;
import static org.assertj.core.api.Assertions.assertThat;

class MowerPrinterAdapterTest {

    private final MowerPrinterAdapter mowerPrinterAdapter = new MowerPrinterAdapter();
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void init() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void should_print_1_3_E_as_mower_information() {
        //GIVEN
        var mowerInitialPosition = new MowerPosition(1, 3);
        var mower = new Mower(mowerInitialPosition, EAST);

        //WHEN
        mowerPrinterAdapter.printMower(mower);

        //THEN
        assertThat(outputStreamCaptor.toString().trim())
                .isEqualTo("1 3 E");
    }

    @Test
    void should_log_nothing_when_no_direction_is_defined() {
        //GIVEN
        var mowerInitialPosition = new MowerPosition(1, 3);
        var mower = new Mower(mowerInitialPosition, null);

        //WHEN
        mowerPrinterAdapter.printMower(mower);

        assertThat(outputStreamCaptor.toString().trim())
                .isEmpty();
    }


    @Test
    void should_log_nothing_when_no_position_is_defined() {
        //GIVEN
        var mower = new Mower(null, EAST);

        //WHEN
        mowerPrinterAdapter.printMower(mower);

        assertThat(outputStreamCaptor.toString().trim())
                .isEmpty();
    }
}