package com.github.elimxim.ringbuffer.exception;

public class EmptyRingBufferException extends RuntimeException {
    public EmptyRingBufferException(String message) {
        super(message);
    }
}
