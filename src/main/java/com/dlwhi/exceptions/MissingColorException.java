package com.dlwhi.exceptions;

public class MissingColorException extends RuntimeException {
    public MissingColorException() {
        super("No such color in ansi standart");
    }
}
