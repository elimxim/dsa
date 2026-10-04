package com.github.elimxim.heap.exception;

public class HeapEmptyException extends RuntimeException {
    public HeapEmptyException() {
        super("heap is empty");
    }
}
