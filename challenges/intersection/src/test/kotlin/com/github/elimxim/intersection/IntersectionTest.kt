package com.github.elimxim.intersection

import com.github.elimixm.intersection.*
import kotlin.test.Test
import kotlin.test.assertContentEquals

class IntersectionTest {
    @Test
    fun `brute force approach test`() {
        testApproach(BruteForceApproach())
    }

    @Test
    fun `binary search approach test`() {
        testApproach(BinarySearchApproach())
    }

    @Test
    fun `two pointers approach test`() {
        testApproach(TwoPointersApproach())
    }

    @Test
    fun `hash set approach test`() {
        testApproach(HashSetApproach())
    }

    private fun testApproach(approach: Approach) {
        val a = intArrayOf(3, 6, 9, 7, 4, 2, 1, 8)
        val b = intArrayOf(6, 8, 5, 0, 3)

        val intersection = approach.intersection(a, b)
        intersection.sort()
        assertContentEquals(intArrayOf(3, 6, 8), intersection)
    }
}