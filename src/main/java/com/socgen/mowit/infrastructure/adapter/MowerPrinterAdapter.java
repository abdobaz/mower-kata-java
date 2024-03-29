package com.socgen.mowit.infrastructure.adapter;

import com.socgen.mowit.domain.Mower;
import com.socgen.mowit.domain.port.MowerPrinterPort;

import java.util.Objects;


public class MowerPrinterAdapter implements MowerPrinterPort {
    @Override
    public void printMower(Mower mower) {
        if(Objects.nonNull(mower) && Objects.nonNull(mower.getPosition())
                && Objects.nonNull(mower.getDirection())) {
            var mowerPosition = mower.getPosition();

            var infoToDisplay = String.format("%d %d %s", mowerPosition.getX(), mowerPosition.getY(), mower.getDirection().getCode());

            System.out.println(infoToDisplay);
        }
    }
}
