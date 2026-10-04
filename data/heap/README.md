# Heap

Heap data structure is a [complete binary tree](https://en.wikipedia.org/wiki/Binary_tree) 
that satisfies the **heap property**:
- **max heap property:** any given node is always greater than its child node/s and the 
  key of the root node is the largest among all other nodes 
- **min heap property:** any given node is always smaller than the child node/s and the
  key of the root node is the smallest among all other nodes

> A complete binary tree is a special type of binary tree where all the levels of the tree 
> are filled completely except the lowest level nodes which are filled from as left as possible.

MinHeap example:

```text
     2   
    / \  
   /   \ 
  4    16
 / \   / 
32  8 64 
```

MaxHeap example:

```text
    64   
    / \  
   /   \ 
  32   16
 / \   / 
4   8 2 
```

## Operations

| operation                | description                     | time complexity |
|--------------------------|---------------------------------|-----------------|
| `heapify(array) -> heap` | creates a heap of a given array | O((n/2)logn)    |
| `peek() -> e`            | gets the root element           | O(1)            | 
| `pull() -> e`            | extracts the root element       | O(logn)         |
| `put(e)`                 | adds a new element              | O(logn)         |
| `delete(e)`              | removes a given element         | O(logn)         |
| `size() -> n`            | returns the number of elements  | O(1)            |
| `height() -> n`          | heap height                     | O(logn)         |