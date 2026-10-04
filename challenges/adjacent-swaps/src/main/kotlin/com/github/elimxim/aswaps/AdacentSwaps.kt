import kotlin.math.min

/**
 * Find the min number of swaps of adjacent elements to sort the array.
 *
 * Algorithm complexity:
 * - Time - O(n)
 * - Memory - O(1)
 *
 * @param input Unsorted array in which each element contains either a 0 or a 1.
 * @return the min number of swaps to sort the array
 */
fun minSwaps(input: Array<Int>): Int {
    var zeroSwaps = 0
    var oneSwaps = 0
    var zeroCount = 0
    var oneCount = 0

    for (e in input) {
        if (e == 0) {
            zeroCount++
            zeroSwaps += oneCount
        } else {
            oneCount++
            oneSwaps += zeroCount
        }
    }

    return min(zeroSwaps, oneSwaps)
}