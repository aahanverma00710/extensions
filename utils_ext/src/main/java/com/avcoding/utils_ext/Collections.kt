package com.avcoding.utils_ext

import java.util.SortedMap

/**
 * Returns a [Pair] containing the first and last elements of the [ArrayList].
 * If the list is empty, both elements of the [Pair] will be null.
 */
fun <T> ArrayList<T>.firstAndLast(): Pair<T?, T?> = Pair(firstOrNull(), lastOrNull())

/**
 * Returns a new [List] containing a specified number of random elements from the original [ArrayList].
 * The order of elements in the returned list is not guaranteed to be the same as in the original list.
 *
 * @param count The number of random elements to take. If count is zero or negative, an empty list is returned.
 * @return A new [List] containing up to [count] random elements.
 */
fun <T> ArrayList<T>.takeRandom(count: Int): List<T> {
    if (count <= 0) return emptyList() // Return empty list for non-positive count
    val shuffledList = this.shuffled() // Create a new shuffled list to avoid modifying the original
    return shuffledList.take(kotlin.math.min(count, size)) // Take up to 'count' elements, or the list size if smaller
}

/**
 * Removes the first element from the [ArrayList] that satisfies the given [predicate].
 *
 * @param predicate A function that takes an element of type [T] and returns true if the element should be removed.
 * @return `true` if an element was removed, `false` otherwise.
 */
fun <T> ArrayList<T>.removeFirstIf(predicate: (T) -> Boolean): Boolean {
    val indexToRemove = indexOfFirst(predicate) // Find the index of the first matching element
    return if (indexToRemove != -1) { // If an element was found
        removeAt(indexToRemove) // Remove the element at the found index
        true // Return true to indicate a removal occurred
    } else {
        false // Return false if no element matched the predicate
    }
}

/**
 * Inserts the given [element] into the [ArrayList] at the specified [index].
 *
 * @param index The index at which the [element] should be inserted.
 * Valid indices are from 0 up to and including the current size of the list.
 * @param element The element to be inserted.
 */
fun <T> ArrayList<T>.insertAt(index: Int, element: T) {
    if (index in 0..size) { // Check if the index is within the valid range
        add(index, element) // Add the element at the specified index
    }
    // If the index is out of bounds, the list remains unchanged (no exception is thrown)
}

// Extension functions for Map

/**
 * Converts the [Map] into a [SortedMap] where the entries are sorted by their keys
 * using the natural ordering of the keys.
 *
 * @return A new [SortedMap] with entries sorted by key.
 */
fun <K : Comparable<K>, V> Map<K, V>.toSortedMapByKey(): SortedMap<K, V> = toSortedMap()

/**
 * Merges another [Map] into the current [Map]. If there are duplicate keys,
 * the [onConflict] function is called to determine which value to keep.
 *
 * @param other The [Map] to merge into this [Map].
 * @param onConflict A function that takes the conflicting key, the old value from this map,
 * and the new value from the [other] map, and returns the value to be kept.
 * @return A new [Map] containing the merged entries.
 */
fun <K, V> Map<K, V>.merge(other: Map<K, V>, onConflict: (key: K, oldValue: V, newValue: V) -> V): Map<K, V> {
    val result = mutableMapOf<K, V>() // Create a mutable map to store the result
    result.putAll(this) // Add all entries from the current map
    other.forEach { key, newValue ->
        result.compute(key) { _, oldValue -> // Compute the value for the given key
            if (oldValue == null) newValue else onConflict(key, oldValue, newValue) // If no old value, use the new value; otherwise, resolve the conflict
        }
    }
    return result // Return the merged map
}

/**
 * Creates a new [Map] by transforming the keys of the original [Map] using the provided [transform] function,
 * which also takes the corresponding value as input.
 *
 * @param transform A function that takes a key of type [K] and its corresponding value of type [V],
 * and returns the new key of type [K].
 * @return A new [Map] with transformed keys and the original values.
 */
fun <K, V, NK> Map<K, V>.mapKeysWithValue(transform: (key: K, value: V) -> NK): Map<NK, V> {
    return map { (key, value) -> transform(key, value) to value }.toMap() // Transform each entry into a new key-value pair and create a new map
}