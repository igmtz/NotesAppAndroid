package com.gmtz.notesappandroid.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * The Note class represents a single note entity in the application.
 * @property Entity RoomDB annotation to have a mapping SQLite table in the database.
 * @property PrimaryKey RoomDB annotation that specifies the id param as the primary key for the database table. This ensures that each note has a unique identifier.
 * @exception InvalidNoteException An exception class that extends the base Exception class. It is thrown to indicate that a note is invalid.
 */
@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    @PrimaryKey val id: Int? = null
)

class InvalidNoteException(message: String): Exception(message)
