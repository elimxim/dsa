# Swapping Adjacent Array Elements

## Problem Statement

Given an array of binary digits containing only 0 and 1, rearrange the elements 
by swapping adjacent positions until all zeros are grouped at one end and all ones at the other. 
Either end can contain the zeros. The objective is to determine the absolute minimum number 
of adjacent swaps required to sort the array.

## Example

- Input: `a = [0,1,0,1]`
- Operation: Swapping elements 1 and 2 yields `[0,0,1,1]`, a sorted array achieved in one move.

## Constraints

- 1 <= n <= 10<sup>5</sup>
- `a[i]` is in the set `{0,1}`