package com.github.elimxim.fib

interface Fib {
    fun get(number: Int): Int
}

/**
 * Implementation of a loop that calculates
 * the next Fibonacci number
 */
class LoopV1 : Fib {
    override fun get(number: Int): Int {
        var curr = 0
        var next = 1

        for (i in 0 until number) {
            next += curr.also { curr = next }
        }

        return curr
    }
}

/**
 * Implementation of a loop that doesn't
 * calculate the next Fibonacci number
 */
class LoopV2 : Fib {
    override fun get(number: Int): Int {
        var prev = 0
        var curr = 1

        if (number == 0) {
            return prev
        }

        for (i in 1..<number) {
            curr += prev.also { prev = curr }
        }

        return curr
    }
}

/**
 * Implementation of a recursion
 */
class RecursionV1 : Fib {
    override fun get(number: Int): Int {
        return when (number) {
            0 -> 0
            1 -> 1
            else -> get(number - 1) + get(number - 2)
        }
    }
}