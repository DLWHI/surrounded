package com.dlwhi.exceptions;

public class RegenerationException extends RuntimeException {
    public RegenerationException() {
        super("Field require regeneration");
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
