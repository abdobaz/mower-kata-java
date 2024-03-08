package com.socgen.mowit.infrastructure.adapter;

import com.socgen.mowit.domain.Mower;
import com.socgen.mowit.domain.port.MowerPrinterPort;

public class MowerPrinterAdapter implements MowerPrinterPort {
    @Override
    public void printMower(Mower mower) {
        throw new  UnsupportedOperationException("Not yest Implemented");
    }
}
