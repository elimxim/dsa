package com.github.elimixm.intersection

class TwoPointersApproach : Approach {
    override fun intersection(a: IntArray, b: IntArray): IntArray {
        val intersection = ArrayList<Int>()

        a.sort()
        b.sort()

        var i = 0
        var j = 0

        while (i < a.size && j < b.size) {
            if (a[i] == b[j]) {
                intersection.add(a[i])
                i++
                j++
            } else if (a[i] < b[j]){
                i++
            } else {
                j++
            }
        }

        return intersection.toIntArray()
    }
}