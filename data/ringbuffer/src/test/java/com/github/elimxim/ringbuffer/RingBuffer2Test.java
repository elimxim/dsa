package com.github.elimxim.ringbuffer;

import com.github.elimxim.ringbuffer.exception.EmptyRingBufferException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RingBuffer2Test extends RingBuffer1Test {

    @Override
    public RingBuffer ringBuffer() {
        return new RingBuffer2(3);
    }

    @Test
    @Disabled
    @Override
    void testPutNull() {
    }

    @Test
    void testGetNull() {
        assertThrows(EmptyRingBufferException.class, () -> ringBuffer.get());
    }

    @Test
    void testPutGetNull() {
        ringBuffer.put(null);

        assertNull(ringBuffer.get());
    }
}