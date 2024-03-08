package com.socgen.mowit.infrastructure.adapter;

import com.socgen.mowit.domain.Mower;
import com.socgen.mowit.domain.port.MowerPrinterPort;

import java.util.Objects;
import java.util.logging.Logger;


public class MowerPrinterAdapter implements MowerPrinterPort {
    @Override
    public void printMower(Mower mower) {
        var log = Logger.getLogger(MowerPrinterAdapter.class.getName());
        if(Objects.nonNull(mower)) {
            log.info("mower.toString()");
        }
    }
}
