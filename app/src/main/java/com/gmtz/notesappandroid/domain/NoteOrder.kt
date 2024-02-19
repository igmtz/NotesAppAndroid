package com.gmtz.notesappandroid.domain

/**
 * The NoteOrder sealed class represents different ordering criteria for notes.
 * It defines two subclasses, Title and Date.
 * @param orderType An instance of OrderType sealed class indicating the order (ascending or descending)
 */
sealed class NoteOrder(val orderType: OrderType) {
    class Title(orderType: OrderType): NoteOrder(orderType)
    class Date(orderType: OrderType): NoteOrder(orderType)

    /**
     * Creates a new instance of NoteOrder with the specified order type.
     * @return NoteOrder instance
     */
    fun copy(orderType: OrderType): NoteOrder {
        return when(this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
        }
    }
}