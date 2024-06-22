package com.github.elimxim.exception;

public class RingBufferOverflowException extends RuntimeException {
    public RingBufferOverflowException(String message) {
        super(message);
    }
}
