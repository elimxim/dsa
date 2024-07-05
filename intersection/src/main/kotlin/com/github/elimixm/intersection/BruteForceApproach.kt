package com.github.elimixm.intersection

class BruteForceApproach : Approach {
    override fun intersection(a: IntArray, b: IntArray): IntArray {
        val intersection = ArrayList<Int>()
        for (i in a.indices) {
            for (j in b.indices) {
                if (a[i] == b[j]) {
                    intersection.add(a[i])
                    break
                }
            }
        }
        return intersection.toIntArray()
    }
}