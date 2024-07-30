package com.github.elimxim.fib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JFibonacciTest {
    @Test
    void testLoopV1() {
        checkFib(new JFibonacci.LoopV1());
    }

    @Test
    void testLoopV1Improved() {
        checkFib(new JFibonacci.LoopV1Improved());
    }

    @Test
    void testLoopV2() {
        checkFib(new JFibonacci.LoopV2());
    }

    @Test
    void testLoopV3() {
        checkFib(new JFibonacci.LoopV3());
    }

    @Test
    void testRecursionV1() {
        checkFib(new JFibonacci.RecursionV1());
    }

    private static void checkFib(JFibonacci.Fib fib) {
        int[] expected = new int[]{0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], fib.get(i));
        }
    }
}