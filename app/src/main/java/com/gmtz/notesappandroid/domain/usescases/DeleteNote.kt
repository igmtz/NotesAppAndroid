package com.gmtz.notesappandroid.domain.usescases


import com.gmtz.notesappandroid.data.Note
import com.gmtz.notesappandroid.data.NoteRepository

/**
 * The DeleteNote class represents a use case responsible for deleting a note from the system.
 * It encapsulates the business logic for removing a note from the repository.
 * @param repository An instance of NoteRepository used for interacting with the data source.
 */
class DeleteNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}