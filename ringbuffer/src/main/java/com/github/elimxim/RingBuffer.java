package com.github.elimxim;

public interface RingBuffer {
    void put(Object o);

    Object get();

    default int size() {
        throw new UnsupportedOperationException("method size() isn't supported");
    }
}
