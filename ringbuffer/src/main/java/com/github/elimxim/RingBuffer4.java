package com.github.elimxim;

import com.github.elimxim.exception.EmptyRingBufferException;
import com.github.elimxim.exception.RingBufferOverflowException;

import java.util.Arrays;

/**
 * Thread-safe implementation
 */
public class RingBuffer4 implements RingBuffer {
    private static final Object NULL = new Object();

    private int read;
    private int write;
    private byte lastOp;

    private final Object[] buffer;

    public RingBuffer4(int size) {
        buffer = initBuffer(size);
    }

    private synchronized Object[] initBuffer(int size) {
        Object[] buffer = new Object[size];
        Arrays.fill(buffer, NULL);
        return buffer;
    }

    @Override
    public synchronized void put(Object o) {
        try {
            if (read == write && lastOp == 1) {
                throw new RingBufferOverflowException("buffer is full");
            }

            buffer[write++] = o;
        } finally {
            lastOp = 1;
            if (write == buffer.length) {
                write = 0;
            }
        }
    }

    @Override
    public synchronized Object get() {
        try {
            if (read == write && lastOp == 0) {
                throw new EmptyRingBufferException("buffer is empty");
            }

            return buffer[read++];
        } finally {
            lastOp = 0;
            if (read == buffer.length) {
                read = 0;
            }
        }
    }

    @Override
    public synchronized int size() {
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