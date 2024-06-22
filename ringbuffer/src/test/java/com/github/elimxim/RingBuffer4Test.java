package com.github.elimxim;

import com.github.elimxim.exception.EmptyRingBufferException;
import com.github.elimxim.exception.RingBufferOverflowException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RingBuffer4Test extends RingBuffer3Test {
    @Override
    public RingBuffer ringBuffer() {
        return new RingBuffer4(3);
    }

    @Test
    void testProducerConsumer() {
        int produceTimes = 10000;
        int producerCount = 4;
        int consumerCount = 10;

        RingBuffer4 cRingBuffer = new RingBuffer4(produceTimes);

        List<Producer> producers = IntStream.rangeClosed(1, producerCount)
                .mapToObj(i -> new Producer("Producer-" + i, cRingBuffer, produceTimes))
                .toList();

        List<Consumer> consumers = IntStream.rangeClosed(1, consumerCount)
                .mapToObj(i -> new Consumer("Consumer-" + i, cRingBuffer))
                .toList();

        run(producers, consumers);

        int sent = 0;
        for (Producer p : producers) {
            int successCount = p.getSuccessCount();
            int failureCount = p.getFailureCount();
            sent += successCount - failureCount;

            System.out.println(p.getName() + ": " + successCount + " " + failureCount);
        }

        int received = 0;
        for (Consumer c : consumers) {
            int successCount = c.getSuccessCount();
            received += successCount;

            System.out.println(c.getName() + ": " + successCount + " " + c.getFailureCount());
        }

        assertEquals(sent, received);
    }

    private static void run(List<Producer> producers, List<Consumer> consumers) {
        List<AbstractCounter> counters = new ArrayList<>();
        counters.addAll(producers);
        counters.addAll(consumers);
        Collections.shuffle(counters);

        Map<AbstractCounter, Thread> threads = counters.stream().collect(
                Collectors.toMap(Function.identity(), Thread::new)
        );

        threads.values().forEach(Thread::start);

        threads.forEach((counter, thread) -> {
            if (counter instanceof Producer) {
                try {
                    thread.join();
                } catch (InterruptedException ignore) {
                    System.out.println(counter.name + " has been interrupted");
                }
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        threads.forEach((counter, thread) -> {
            if (counter instanceof Consumer) {
                thread.interrupt();
            }
        });
    }

    private static abstract class AbstractCounter implements Runnable {
        private final String name;
        protected final RingBuffer4 ringBuffer4;
        protected final AtomicInteger successCounter = new AtomicInteger();
        protected final AtomicInteger failureCounter = new AtomicInteger();

        AbstractCounter(String name, RingBuffer4 ringBuffer4) {
            this.name = name;
            this.ringBuffer4 = ringBuffer4;
        }


        public String getName() {
            return name;
        }

        public int getSuccessCount() {
            return successCounter.get();
        }

        public int getFailureCount() {
            return failureCounter.get();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof AbstractCounter counter)) {
                return false;
            }

            return name.equals(counter.getName());
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }

    private static class Producer extends AbstractCounter {
        private final int produceTimes;

        public Producer(String name, RingBuffer4 ringBuffer4, int produceTimes) {
            super(name, ringBuffer4);
            this.produceTimes = produceTimes;
        }

        @Override
        public void run() {
            IntStream.rangeClosed(1, produceTimes).forEach(this::produce);
        }

        public void produce(Object value) {
            try {
                ringBuffer4.put(value);
                successCounter.incrementAndGet();
            } catch (RingBufferOverflowException ignore) {
                failureCounter.incrementAndGet();
            }
        }
    }

    private static class Consumer extends AbstractCounter {
        public Consumer(String name, RingBuffer4 ringBuffer4) {
            super(name, ringBuffer4);
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                consume();
            }
        }

        public void consume() {
            try {
                ringBuffer4.get();
                successCounter.incrementAndGet();
                ringBuffer4.size();
            } catch (EmptyRingBufferException ignore) {
                failureCounter.incrementAndGet();
            }
        }
    }
}