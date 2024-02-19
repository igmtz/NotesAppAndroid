package com.gmtz.notesappandroid.domain.usescases

import com.gmtz.notesappandroid.data.InvalidNoteException
import com.gmtz.notesappandroid.data.Note
import com.gmtz.notesappandroid.data.NoteRepository

/**
 * The AddNote class represents a use case responsible for adding a new note to the system.
 * It encapsulates the business logic for validating and inserting a note into the repository.
 * @param repository An instance of NoteRepository used for interacting with the data source.
 * @throws InvalidNoteException If the title or content of the note is empty.
 */
class AddNote(
    private val repository: NoteRepository
) {
    /**
     * Adds a new note to the system when invoking this Use Case.
     * @param note The note object to be added.
     */
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if(note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty.")
        }
        if(note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty.")
        }
        repository.insertNote(note)
    }
}