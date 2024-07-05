package com.github.elimxim.fib

interface Fib {
    fun get(number: Int): Int
}

/**
 * Implementation of a loop that starts
 * from the zero Fibonacci number of 0
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
 * Implementation of a loop that starts
 * from the first Fibonacci number of 1
 */
class LoopV2 : Fib {
    override fun get(number: Int): Int {
        var curr = 0
        var next = 1

        if (number == 0) {
            return curr
        }

        for (i in 0 until number - 1) {
            next += curr.also { curr = next }
        }

        return next
    }
}

/**
 * Implementation of a loop that starts
 * from the second Fibonacci number of 1
 */
class LoopV3 : Fib {
    override fun get(number: Int): Int {
        var prev = 0
        var curr = 1

        if (number == 0) {
            return prev
        }

        for (i in 2..number) {
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