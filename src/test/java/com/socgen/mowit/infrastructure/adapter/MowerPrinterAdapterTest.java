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
    void should_print_1_3_E_as_mower_information() {
        //GIVEN
        var mowerInitialPosition = new MowerPosition(1, 3);
        var mower = new Mower(mowerInitialPosition, EAST);
        try(MockedStatic<Logger> loggerFactoryMockedStatic = mockStatic(Logger.class)) {
            var logger = mock(Logger.class);
            loggerFactoryMockedStatic.when(()-> Logger.getLogger(MowerPrinterAdapter.class.getName()))
                    .thenReturn(logger);
            //WHEN
            mowerPrinterAdapter.printMower(mower);

            //THEN check logger was invoked
            verify(logger).info("1 3 E");
        }
    }

    @Test
    void should_log_nothing_when_no_direction_is_defined() {
        //GIVEN
        var mowerInitialPosition = new MowerPosition(1, 3);
        var mower = new Mower(mowerInitialPosition, null);

        try(MockedStatic<Logger> loggerFactoryMockedStatic = mockStatic(Logger.class)) {
            var logger = mock(Logger.class);
            loggerFactoryMockedStatic.when(()-> Logger.getLogger(MowerPrinterAdapter.class.getName()))
                    .thenReturn(logger);
            //WHEN
            mowerPrinterAdapter.printMower(mower);

            //THEN check logger was invoked
            verifyNoInteractions(logger);
        }
    }


    @Test
    void should_log_nothing_when_no_position_is_defined() {
        //GIVEN
        var mower = new Mower(null, EAST);

        try(MockedStatic<Logger> loggerFactoryMockedStatic = mockStatic(Logger.class)) {
            var logger = mock(Logger.class);
            loggerFactoryMockedStatic.when(()-> Logger.getLogger(MowerPrinterAdapter.class.getName()))
                    .thenReturn(logger);
            //WHEN
            mowerPrinterAdapter.printMower(mower);

            //THEN check logger was invoked
            verifyNoInteractions(logger);
        }
    }
}