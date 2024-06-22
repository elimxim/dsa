package com.github.elimxim;

import java.util.stream.IntStream;

class JFibonacci {

    /**
     * Implementation of a loop that starts
     * from the zero Fibonacci number of 0
     */
    static class LoopV1 {
        int get(int number) {
            int curr = 0;
            int next = 1;

            for (int i = 0; i < number; i++) {
                int prev = curr;

                curr = next;
                next += prev;
            }

            return curr;
        }
    }

    /**
     * Optimization of implementation {@code LoopV1}
     * that prevent calculating of the next Fibonacci number
     * on the last iteration
     */
    static class LoopV1Improved {
        int get(int number) {
            int curr = 0;
            int next = 1;

            for (int i = 0; i < number; i++) {
                int prev = curr;

                curr = next;

                if (i < number - 1) {
                    next += prev;
                }
            }

            return curr;
        }
    }

    /**
     * Implementation of a loop that starts
     * from the first Fibonacci number of 1
     */
    static class LoopV2 {
        int get(int number) {
            int curr = 0;
            int next = 1;

            if (number == 0) {
                return curr;
            }

            for (int i = 0; i < number - 1; i++) {
                int prev = curr;

                curr = next;
                next += prev;
            }

            return next;
        }
    }

    /**
     * Implementation of a loop that starts
     * from the second Fibonacci number of 1
     */
    static class LoopV3 {
        int get(int number) {
            int prev = 0;
            int curr = 1;

            if (number == 0) {
                return prev;
            }

            for (int i = 2; i <= number; i++) {
                int next = prev + curr;

                prev = curr;
                curr = next;
            }

            return curr;
        }
    }

    /**
     * Variation of implementation {@code imp3}
     */
    static class LoopV3Variation {
        int get(int number) {
            int prev = 0;
            int curr = 1;

            if (number == 0) {
                return prev;
            }

            for (int i = 0; i < number - 1; i++) {
                int next = prev + curr;

                prev = curr;
                curr = next;
            }

            return curr;
        }
    }

    /**
     * Implementation of a recursion
     */
    static class RecursionV1 {
        int get(int number) {
            if (number == 0) {
                return 0;
            } else if (number == 1) {
                return 1;
            }

            return get(number - 1) + get(number - 2);
        }
    }

    public static void main(String[] args) {
        var fib = new RecursionV1();

        IntStream.rangeClosed(0, 9).forEach(n ->
                System.out.println(n + " number of Fibonacci is " + fib.get(n))
        );
    }
}
