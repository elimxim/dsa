package com.github.elimxim.heap.exception;

public class HeapOverflowException extends RuntimeException {
    public HeapOverflowException() {
        super("heap is overflow");
    }
}
