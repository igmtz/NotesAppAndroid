package com.gmtz.notesappandroid.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * The NoteDao interface defines methods for accessing and manipulating note entities in the database.
 * @property Dao Annotates the interface as a Data Access Object (DAO) for RoomDB. This annotation is used by Room to generate the necessary database access code at compile time.
 * @property Query The value of the annotation includes the SQL query that will be run when this method is called.
 * @property Insert It will insert classes annotated with @Entity into the database. The onConflict property defines what to do if a conflict happens.
 * @property Delete It will delete classes annotated with @Entity into the database.
 */
@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}