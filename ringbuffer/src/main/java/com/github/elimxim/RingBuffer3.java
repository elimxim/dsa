package com.github.elimxim;

import com.github.elimxim.exception.EmptyRingBufferException;
import com.github.elimxim.exception.RingBufferOverflowException;

import java.util.Arrays;

/**
 * Has {@code size()} method
 */
public class RingBuffer3 implements RingBuffer {
    private static final Object NULL = new Object();

    private int read;
    private int write;
    private byte lastOp;

    private final Object[] buffer;

    public RingBuffer3(int size) {
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

    @Override
    public int size() {
        if (write == buffer.length) {
            write = 0;
        }

        if (read == buffer.length) {
            read = 0;
        }

        if (write > read) {
            return write - read;
        }

        if (write < read) {
            return write + (buffer.length - read);
        }

        if (lastOp == 0x01) {
            return buffer.length;
        } else {
            return 0;
        }
    }
}

