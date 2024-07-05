package com.github.elimixm.intersection

class BinarySearchApproach : Approach {
    override fun intersection(a: IntArray, b: IntArray): IntArray {
        val intersection = ArrayList<Int>()

        a.sort()
        for (i in b.indices) {
            if (binarySearch(a, b[i])) {
                intersection.add(b[i])
            }
        }

        return intersection.toIntArray()
    }

    private fun binarySearch(array: IntArray, value: Int): Boolean {
        var l = 0
        var h = array.size
        while (l < h) {
            val mid = l + (h - l) / 2
            if (array[mid] == value) {
                return true
            } else if (array[mid] > value) {
                h = mid - 1
            } else {
                l = mid + 1
            }
        }
        return false
    }
}