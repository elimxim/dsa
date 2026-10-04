package com.github.elimxim.fib

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class KFibonacciTest {
    @Test
    fun `LoopV1 test`() {
        checkFib(LoopV1())
    }

    @Test
    fun `LoopV2 test`() {
        checkFib(LoopV2())
    }

    @Test
    fun `RecursionV1 test`() {
        checkFib(RecursionV1())
    }

    private fun checkFib(fib: Fib) {
        val expected = intArrayOf(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144)
        for (i in expected.indices) {
            assertEquals(expected[i], fib.get(i))
        }
    }
}