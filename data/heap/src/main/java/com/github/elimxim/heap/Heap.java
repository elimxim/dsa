package com.github.elimxim.heap;

public interface Heap<E> {
    E peek();

    E pull();

    void put(E e);

    boolean delete(E e);

    boolean contains(E e);

    int size();

    int height();

    E[] array();

    void print();
}
