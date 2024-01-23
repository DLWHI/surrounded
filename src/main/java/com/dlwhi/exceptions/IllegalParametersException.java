package com.dlwhi.exceptions;

public class IllegalParametersException extends RuntimeException {
    public IllegalParametersException() {
        super("It is impossible to place such a number of enemies and obstacles on a map of the given size");
    }
}
