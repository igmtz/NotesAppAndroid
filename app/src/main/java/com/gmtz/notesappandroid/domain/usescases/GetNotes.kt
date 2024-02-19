package com.gmtz.notesappandroid.domain.usescases

import com.gmtz.notesappandroid.domain.NoteOrder
import com.gmtz.notesappandroid.domain.OrderType
import com.gmtz.notesappandroid.data.Note
import com.gmtz.notesappandroid.data.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * The GetNotes class represents a use case responsible for retrieving notes from the database.
 * It encapsulates the business logic for fetching notes from the repository and applying sorting
 * based on specified criteria.
 * @param repository An instance of NoteRepository used for interacting with the data source.
 */
class GetNotes(
    private val repository: NoteRepository
) {
    /**
     * Retrieves notes from the system with optional sorting based on specified criteria
     * when this Use Case is invoked.
     * @param noteOrder Indicates the order in which notes should be sorted using the NoteOrder sealed class.
     * Defaults to sorting by date in descending order.
     * @return A flow of lists containing notes.
     */
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>> {
        return repository.getNotes().map { notes ->
            when(noteOrder.orderType) {
                is OrderType.Ascending -> {
                    when(noteOrder) {
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                    }
                }
                is OrderType.Descending -> {
                    when(noteOrder) {
                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
                    }
                }
            }
        }
    }
}