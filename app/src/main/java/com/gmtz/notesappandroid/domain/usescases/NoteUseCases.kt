package com.gmtz.notesappandroid.domain.usescases

/**
 * The NoteUseCases data class encapsulates the use cases related to note management
 * within the system, providing a convenient way to access and use them.
 */
data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getNote: GetNote,
    val lockNote: LockNote
)