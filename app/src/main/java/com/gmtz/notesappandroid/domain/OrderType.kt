package com.gmtz.notesappandroid.domain

/**
 * The OrderType sealed class represents the direction of sorting.
 * It defines two singleton objects, Ascending and Descending, to
 * indicate the direction of sorting.
 */
sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}