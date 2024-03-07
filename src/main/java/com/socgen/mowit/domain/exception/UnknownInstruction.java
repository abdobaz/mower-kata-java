package com.socgen.mowit.domain.exception;

public class UnknownInstruction extends RuntimeException {
    public UnknownInstruction(String instruction) {
        super(String.format("No Instruction present for %s", instruction));
    }
}
