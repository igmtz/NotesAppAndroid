package com.gmtz.notesappandroid.domain.usescases

import com.gmtz.notesappandroid.data.Note
import com.gmtz.notesappandroid.data.NoteRepository

/**
 * The GetNote class represents a use case responsible for getting a single note from the database.
 * It encapsulates the business logic for getting a note from the repository.
 * @param repository An instance of NoteRepository used for interacting with the data source.
 */
class GetNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}