package com.socgen.mowit.services;

import com.socgen.mowit.domain.Lawn;
import com.socgen.mowit.domain.Mower;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MowerControl {
    private final Lawn lawn;

    public void execute(Mower mower, String commands) {
        throw new UnsupportedOperationException("Not yet Implemented");
    }
}
