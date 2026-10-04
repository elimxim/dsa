package com.github.elimxim.heap;

import com.github.elimxim.heap.exception.HeapEmptyException;

class MinHeap extends AbstractHeap implements Heap<Integer> {
    public MinHeap(int initialCapacity) {
        super(initialCapacity);
    }

    private MinHeap(int[] elementData) {
        super(elementData);
    }

    @Override
    public void put(Integer e) {
        if (size == elementData.length) {
            grow();
        }

        int idx = size;
        elementData[idx] = e;
        size++;

        while (idx != 0 && elementData[idx] < elementData[parentIdx(idx)]) {
            swap(idx, parentIdx(idx));
            idx = parentIdx(idx);
        }
    }

    @Override
    protected void heapifyRec(int idx) {
        int root = idx;
        int left = leftChildIdx(idx);
        int right = rightChildIdx(idx);

        if (left < size && elementData[left] < elementData[root]) {
            root = left;
        }

        if (right < size && elementData[right] < elementData[root]) {
            root = right;
        }

        if (root != idx) {
            swap(idx, root);
            heapifyRec(root);
        }
    }

    @Override
    protected int searchIdx(Integer e) {
        if (size == 0) {
            return ABSENT;
        }

        if (e < elementData[0]) {
            return ABSENT;
        }

        for (int i = 0; i < size; i++) {
            if (elementData[i] == e) {
                return i;
            }
        }

        return ABSENT;
    }

    public static MinHeap heapify(int[] array) {
        if (array.length == 0) {
            throw new HeapEmptyException();
        }

        int[] copy = new int[array.length];
        System.arraycopy(array, 0, copy, 0, array.length);

        var heap = new MinHeap(copy);
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            heap.heapifyRec(i);
        }

        return heap;
    }
}
