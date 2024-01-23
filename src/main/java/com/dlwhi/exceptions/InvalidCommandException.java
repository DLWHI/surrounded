package com.dlwhi.exceptions;

public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException(String command) {
        super("Unknown command " + command);
    }
}
