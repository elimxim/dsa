package com.github.elimxim.ringbuffer;

import com.github.elimxim.ringbuffer.exception.EmptyRingBufferException;
import com.github.elimxim.ringbuffer.exception.RingBufferOverflowException;

import java.util.Arrays;

/**
 * Accepts {@code null}-values
 */
public class RingBuffer2 implements RingBuffer {
    private static final Object NULL = new Object();

    private int read;
    private int write;
    private byte lastOp;

    private final Object[] buffer;

    public RingBuffer2(int size) {
        buffer = initBuffer(size);
    }

    private static Object[] initBuffer(int size) {
        Object[] buffer = new Object[size];
        Arrays.fill(buffer, NULL);
        return buffer;
    }

    @Override
    public void put(Object o) {
        if (write == buffer.length) {
            write = 0;
        }

        if (read == write && lastOp == 0x01) {
            throw new RingBufferOverflowException("buffer is full");
        }

        lastOp = 0x01;
        buffer[write++] = o;
    }

    @Override
    public Object get() {
        if (read == buffer.length) {
            read = 0;
        }

        if (read == write && lastOp == 0x00) {
            throw new EmptyRingBufferException("buffer is empty");
        }

        lastOp = 0x00;
        return buffer[read++];
    }
}
