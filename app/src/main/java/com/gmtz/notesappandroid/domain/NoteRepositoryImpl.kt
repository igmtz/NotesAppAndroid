package com.gmtz.notesappandroid.domain

import com.gmtz.notesappandroid.data.Note
import com.gmtz.notesappandroid.data.NoteDao
import com.gmtz.notesappandroid.data.NoteRepository
import kotlinx.coroutines.flow.Flow

/**
 * The NoteRepositoryImpl class implements the NoteRepository interface, providing
 * concrete implementations for interacting with note data. It acts as a bridge
 * between the use case layer and the data access layer (NoteDao).
 * @param dao An instance of NoteDao used for data access operations.
 */
class NoteRepositoryImpl(
    private val dao: NoteDao
) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}