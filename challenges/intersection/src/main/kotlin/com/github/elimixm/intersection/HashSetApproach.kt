package com.github.elimixm.intersection

class HashSetApproach : Approach {
    override fun intersection(a: IntArray, b: IntArray): IntArray {
        val intersections = ArrayList<Int>()

        val hash = HashSet<Int>()
        for (i in a.indices) {
            hash.add(a[i])
        }

        for (i in b.indices) {
            if (hash.contains(b[i])) {
                intersections.add(b[i])
            }
        }

        return intersections.toIntArray()
    }
}