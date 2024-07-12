package com.github.elimxim.heap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest {
    private final int[] array = new int[]{9, 3, 4, 7, 1, 6, 5, 2, 8, 11, 18, 13, 20, 12, 15, 14, 19, 17, 16, 10};

    @Test
    void testMinHeap() {
        var heap = MinHeap.heapify(array);
        heap.print();

        assertEquals(20, heap.size());
        assertEquals(5, heap.height());
        checkMinHeapPropertyRec(heap.array(), 0);

        assertEquals(1, heap.peek());

        assertEquals(1, heap.pull());
        heap.print();
        assertEquals(19, heap.size());
        assertFalse(heap.contains(1));
        checkMinHeapPropertyRec(heap.array(), 0);

        assertFalse(heap.delete(42));
        assertTrue(heap.contains(10));

        assertTrue(heap.delete(10));
        heap.print();
        assertEquals(18, heap.size());
        assertFalse(heap.contains(10));
        checkMinHeapPropertyRec(heap.array(), 0);

        assertTrue(heap.delete(4));
        heap.print();
        assertEquals(17, heap.size());
        assertFalse(heap.contains(4));
        checkMinHeapPropertyRec(heap.array(), 0);

        heap.put(4);;
        heap.print();
        assertEquals(18, heap.size());
        assertTrue(heap.contains(4));
        checkMinHeapPropertyRec(heap.array(), 0);

        assertEquals(2, heap.peek());
        assertTrue(heap.delete(2));
        heap.print();
        assertEquals(17, heap.size());
        assertFalse(heap.contains(2));
        checkMinHeapPropertyRec(heap.array(), 0);
    }

    @Test
    void testMaxHeap() {
        var heap = MaxHeap.heapify(array);
        heap.print();

        assertEquals(20, heap.size());
        assertEquals(5, heap.height());
        checkMaxHeapPropertyRec(heap.array(), 0);

        assertEquals(20, heap.peek());

        assertEquals(20, heap.pull());
        heap.print();
        assertEquals(19, heap.size());
        assertFalse(heap.contains(20));
        checkMaxHeapPropertyRec(heap.array(), 0);

        assertFalse(heap.delete(42));
        assertTrue(heap.contains(10));

        assertTrue(heap.delete(10));
        heap.print();
        assertEquals(18, heap.size());
        assertFalse(heap.contains(10));
        checkMaxHeapPropertyRec(heap.array(), 0);

        assertTrue(heap.delete(15));
        heap.print();
        assertEquals(17, heap.size());
        assertFalse(heap.contains(15));
        checkMaxHeapPropertyRec(heap.array(), 0);

        heap.put(15);
        heap.print();
        assertEquals(18, heap.size());
        assertTrue(heap.contains(15));
        checkMaxHeapPropertyRec(heap.array(), 0);

        assertEquals(19, heap.peek());
        assertTrue(heap.delete(19));
        heap.print();
        assertEquals(17, heap.size());
        assertFalse(heap.contains(19));
        checkMaxHeapPropertyRec(heap.array(), 0);
    }

    private void checkMinHeapPropertyRec(Integer[] heap, int root) {
        int left = AbstractHeap.leftChildIdx(root);
        int right = AbstractHeap.leftChildIdx(root);

        if (left < heap.length) {
            assertTrue(heap[root] < heap[left],
                    String.format("%d < %d", heap[root], heap[left])
            );
            checkMinHeapPropertyRec(heap, left);
        }

        if (right < heap.length) {
            assertTrue(heap[root] < heap[right],
                    String.format("%d < %d", heap[root], heap[left])
            );
            checkMinHeapPropertyRec(heap, right);
        }
    }

    private void checkMaxHeapPropertyRec(Integer[] heap, int root) {
        int left = AbstractHeap.leftChildIdx(root);
        int right = AbstractHeap.leftChildIdx(root);

        if (left < heap.length) {
            assertTrue(heap[root] > heap[left],
                    String.format("%d > %d", heap[root], heap[left])
            );
            checkMaxHeapPropertyRec(heap, left);
        }

        if (right < heap.length) {
            assertTrue(heap[root] > heap[right],
                    String.format("%d > %d", heap[root], heap[left])
            );
            checkMaxHeapPropertyRec(heap, right);
        }
    }
}
