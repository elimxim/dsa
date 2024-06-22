package com.github.elimxim;

import com.github.elimxim.exception.EmptyRingBufferException;
import com.github.elimxim.exception.RingBufferOverflowException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RingBuffer1Test extends RingBufferTest {

    @Override
    public RingBuffer ringBuffer() {
        return new RingBuffer1(3);
    }

    @Test
    void testEmpty() {
        assertThrows(EmptyRingBufferException.class, () -> ringBuffer.get());
    }

    @Test
    void testPutGetOne() {
        ringBuffer.put(1);

        assertEquals(1, ringBuffer.get());
    }

    @Test
    void testPutNull() {
        assertThrows(NullPointerException.class, () -> ringBuffer.put(null));
    }

    @Test
    void testFIFO() {
        ringBuffer.put(1);
        ringBuffer.put(2);
        ringBuffer.put(3);

        assertEquals(1, ringBuffer.get());
        assertEquals(2, ringBuffer.get());
        assertEquals(3, ringBuffer.get());
    }

    @Test
    void testFull() {
        ringBuffer.put(1);
        ringBuffer.put(2);
        ringBuffer.put(3);

        assertThrows(RingBufferOverflowException.class, () -> ringBuffer.put(4));
    }

    @Test
    void testRing() {
        ringBuffer.put(1);
        ringBuffer.put(2);
        ringBuffer.put(3);

        assertEquals(1, ringBuffer.get());

        ringBuffer.put(4);

        assertEquals(2, ringBuffer.get());
        assertEquals(3, ringBuffer.get());
        assertEquals(4, ringBuffer.get());
    }
}
