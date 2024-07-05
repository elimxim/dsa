package com.github.elimxim.ringbuffer;

public interface RingBuffer {
    void put(Object o);

    Object get();

    default int size() {
        throw new UnsupportedOperationException("method size() isn't supported");
    }
}
