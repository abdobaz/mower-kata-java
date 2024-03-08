package com.socgen.mowit.infrastructure.adapter;

import com.socgen.mowit.domain.Mower;
import com.socgen.mowit.domain.MowerPosition;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.logging.Logger;

import static com.socgen.mowit.domain.EnumDirection.EAST;
import static org.mockito.Mockito.*;

class MowerPrinterAdapterTest {

    private final MowerPrinterAdapter mowerPrinterAdapter = new MowerPrinterAdapter();

    @Test
    void should_print_mower_information() {
        //GIVEN
        var mowerInitialPosition = new MowerPosition(5, 5);
        var mower = new Mower(mowerInitialPosition, EAST);
        try(MockedStatic<Logger> loggerFactoryMockedStatic = mockStatic(Logger.class)) {
            var logger = mock(Logger.class);
            loggerFactoryMockedStatic.when(()-> Logger.getLogger(MowerPrinterAdapter.class.getName()))
                    .thenReturn(logger);
            //WHEN
            mowerPrinterAdapter.printMower(mower);

            //THEN check logger was invoked
            verify(logger).info("mower.toString()");
        }
    }
}