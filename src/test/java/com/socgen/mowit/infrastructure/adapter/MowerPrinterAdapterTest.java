package com.socgen.mowit.infrastructure.adapter;

import com.socgen.mowit.domain.Mower;
import com.socgen.mowit.domain.MowerPosition;
import org.junit.jupiter.api.Test;

import static com.socgen.mowit.domain.EnumDirection.EAST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

class MowerPrinterAdapterTest {

    private final MowerPrinterAdapter mowerPrinterAdapter = new MowerPrinterAdapter();
    @Test
    void should_print_mower_information() {
        //GIVEN
        var mowerInitialPosition = new MowerPosition(5, 5);
        var mower = new Mower(mowerInitialPosition, EAST);

        //WHEN
        mowerPrinterAdapter.printMower(mower);

        //THEN check logger was invoked
       fail("check logger was invoked not yet implemented");
    }
}