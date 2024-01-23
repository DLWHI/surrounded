package com.dlwhi.exceptions;

public class IllegalCommandException extends RuntimeException {
    public IllegalCommandException(String command) {
        super("Illegal command " + command + " for current state");
    }
}
