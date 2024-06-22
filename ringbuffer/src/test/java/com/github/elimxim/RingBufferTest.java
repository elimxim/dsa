package com.github.elimxim;

import org.junit.jupiter.api.BeforeEach;

public abstract class RingBufferTest {
    protected RingBuffer ringBuffer;

    @BeforeEach
    void beforeEach() {
        ringBuffer = ringBuffer();
    }
    abstract RingBuffer ringBuffer();
}
