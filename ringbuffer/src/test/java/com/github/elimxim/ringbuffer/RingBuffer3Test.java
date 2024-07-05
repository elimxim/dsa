package com.github.elimxim.ringbuffer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RingBuffer3Test extends RingBuffer2Test {

    @Override
    public RingBuffer ringBuffer() {
        return new RingBuffer3(3);
    }

    @Test
    void testSize() {
        assertEquals(0, ringBuffer.size());

        ringBuffer.put(null);

        assertEquals(1, ringBuffer.size());

        ringBuffer.put(null);
        ringBuffer.put(null);

        assertEquals(3, ringBuffer.size());

        ringBuffer.get();

        assertEquals(2, ringBuffer.size());

        ringBuffer.get();
        ringBuffer.get();

        assertEquals(0, ringBuffer.size());
    }
}