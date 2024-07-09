package com.github.elimxim.ringbuffer;

public interface RingBuffer {
    void put(Object o);

    Object get();

    int size();
}
