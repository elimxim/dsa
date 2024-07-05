package com.github.elimxim.ringbuffer.exception;

public class RingBufferOverflowException extends RuntimeException {
    public RingBufferOverflowException(String message) {
        super(message);
    }
}
