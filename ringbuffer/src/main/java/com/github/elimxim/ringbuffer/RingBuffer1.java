package com.github.elimxim.ringbuffer;

import com.github.elimxim.ringbuffer.exception.EmptyRingBufferException;
import com.github.elimxim.ringbuffer.exception.RingBufferOverflowException;

public class RingBuffer1 implements RingBuffer {
    private int read;
    private int write;
    private byte lastOp;

    private final Object[] buffer;

    public RingBuffer1(int size) {
        buffer = new Object[size];
    }

    @Override
    public void put(Object o) {
        if (o == null) {
            throw new NullPointerException("null is not supported");
        }

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
