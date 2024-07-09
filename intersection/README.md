# Intersection of two unsorted arrays

Given two unsorted arrays _a_ and _b_ of sizes _m_ and _n_ respectively.
The intersection _I_ represents an array of common elements present in both arrays.

Example:
```text
a = [3, 6, 9, 7, 4, 2, 1, 8]
b = [6, 8, 5, 0, 3]

I = [3, 6, 8] or I = [6, 3, 8]
or any other order (means the result is unstable)
```

## Approaches
| approach      | time complexity          | space complexity |
|---------------|--------------------------|------------------|
| brute force   | O(mn)                    | O(1)             |
| binary search | O(nlogn + mlogn)         | O(1)             |
| two pointers  | O(nlogn + mlogm + m + n) | O(1)             |
| hash table    | O(n + m)                 | O(n)             |