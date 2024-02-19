package com.gmtz.notesappandroid.data

import kotlinx.coroutines.flow.Flow

/**
 * The NoteRepository interface defines the contract for interacting with note data.
 * It abstracts the data access layer and provides methods to retrieve, insert, and delete notes.
 * Implementations of this interface are responsible for managing the data sources and business logic.
 */
interface NoteRepository {
    /**
     * A Flow is a stream of data that can be computed asynchronously using Coroutines.
     * This allows you to implement observer pattern where observers automatically notify
     * of any state changes, like LiveData and RxJava streams.
     */
    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}