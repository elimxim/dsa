package com.github.elimxim.fib;

class JFibonacci {
    interface Fib {
        int get(int number);
    }

    /**
     * Implementation of a loop that calculates
     * the next Fibonacci number
     */
    static class LoopV1 implements Fib {
        @Override
        public int get(int number) {
            int curr = 0;
            int next = 1;

            for (int i = 0; i < number; i++) {
                int tmp = curr;
                curr = next;
                next += tmp;
            }

            return curr;
        }
    }

    /**
     * Optimization of implementation {@code LoopV1}
     * that prevent calculating of the next Fibonacci number
     * on the last iteration
     */
    static class LoopV1Improved implements Fib {
        @Override
        public int get(int number) {
            int curr = 0;
            int next = 1;

            for (int i = 0; i < number; i++) {
                int tmp = curr;
                curr = next;

                if (i < number - 1) {
                    next += tmp;
                }
            }

            return curr;
        }
    }

    /**
     * Implementation of a loop that doesn't
     * calculate the next Fibonacci number
     */
    static class LoopV2 implements Fib {
        @Override
        public int get(int number) {
            int prev = 0;
            int curr = 1;

            if (number == 0) {
                return prev;
            }

            for (int i = 1; i < number; i++) {
                int tmp = prev;
                prev = curr;
                curr += tmp;
            }

            return curr;
        }
    }

    /**
     * Implementation of a loop that calculates
     * the current Fibonacci number in a different way
     */
    static class LoopV3 implements Fib {
        @Override
        public int get(int number) {
            int prev = 0;
            int curr = 1;

            if (number == 0) {
                return prev;
            }

            for (int i = 1; i < number; i++) {
                int tmp = prev + curr;
                prev = curr;
                curr = tmp;
            }

            return curr;
        }
    }

    /**
     * Implementation of a recursion
     */
    static class RecursionV1 implements Fib {
        @Override
        public int get(int number) {
            if (number == 0) {
                return 0;
            } else if (number == 1) {
                return 1;
            }

            return get(number - 1) + get(number - 2);
        }
    }
}
