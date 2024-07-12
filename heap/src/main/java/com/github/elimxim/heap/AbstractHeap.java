package com.github.elimxim.heap;

import com.github.afkbrb.btp.BTPrinter;
import com.github.elimxim.heap.exception.HeapEmptyException;
import com.github.elimxim.heap.exception.HeapOverflowException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class AbstractHeap implements Heap<Integer> {
    protected static final int ABSENT = -1;

    protected int[] elementData;
    protected int size;

    public AbstractHeap(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("the size cannot be less than or equal to zero");
        }
        this.elementData = new int[initialCapacity];
    }

    protected AbstractHeap(int[] elementData) {
        this.elementData = elementData;
        this.size = elementData.length;
    }

    @Override
    public Integer peek() {
        if (size == 0) {
            throw new HeapEmptyException();
        }
        return elementData[0];
    }

    @Override
    public Integer pull() {
        if (size == 0) {
            throw new HeapEmptyException();
        }

        int root = elementData[0];

        if (size == 1) {
            size--;
            return root;
        }

        elementData[0] = elementData[size - 1];
        size--;
        heapifyRec(0);

        return root;
    }

    @Override
    public boolean delete(Integer e) {
        int idx = searchIdx(e);
        if (idx == ABSENT) {
            return false;
        }

        if (idx == size - 1) {
            size--;
        } else {
            elementData[idx] = elementData[size - 1];
            size--;
            heapifyRec(idx);
        }

        return true;
    }

    @Override
    public boolean contains(Integer e) {
        return searchIdx(e) != ABSENT;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        int height = 0;

        int idx = 0;
        while (idx < size) {
            idx = leftChildIdx(idx);
            height++;
        }

        return height;
    }

    @Override
    public Integer[] array() {
        int[] array = new int[size];
        System.arraycopy(elementData, 0, array, 0, size);
        return Arrays.stream(array).boxed().toArray(Integer[]::new);
    }

    @Override
    public void print() {
        BTPrinter.printTree((Object[]) array());
    }

    protected void grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + oldCapacity >> 1;
        if (newCapacity < oldCapacity) {
            throw new HeapOverflowException();
        }
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    protected abstract void heapifyRec(int idx);

    protected abstract int searchIdx(Integer e);

    protected void swap(int idx1, int idx2) {
        int tmp = elementData[idx1];
        elementData[idx1] = elementData[idx2];
        elementData[idx2] = tmp;
    }

    private List<Integer> indexes(int height) {
        List<Integer> indexes = new ArrayList<>();
        indexesRec(indexes, 0, 1, height);
        return indexes;
    }

    private void indexesRec(List<Integer> indexes, int root, int deep, int height) {
        int curDeep = deep + 1;

        int left = leftChildIdx(root);
        int right = rightChildIdx(root);

        if (curDeep == height) {
            if (left < size) {
                indexes.add(left);
            }
            if (right < size) {
                indexes.add(right);
            }
        } else if (curDeep < height) {
            if (left < size) {
                indexesRec(indexes, left, curDeep, height);
            }
            if (right < size) {
                indexesRec(indexes, right, curDeep, height);
            }
        }
    }

    protected static int parentIdx(int idx) {
        return (idx - 1) / 2;
    }

    protected static int leftChildIdx(int idx) {
        return idx * 2 + 1;
    }

    protected static int rightChildIdx(int idx) {
        return idx * 2 + 2;
    }
}
