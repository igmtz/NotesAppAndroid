package com.gmtz.notesappandroid.data

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * The NoteDatabase class serves as the main entry point for accessing the SQLite database containing note entities. It extends the RoomDatabase class.
 * @property Database Annotates the class as a Room database class.
 */
@Database(
    /**
     * The entity property in the Database annotation represent the list of entities included in the database.
     * The exportSchema property defines whether or not to export the database schema into a folder.
     */
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase: RoomDatabase() {

    abstract val noteDao: NoteDao

    /**
     * A companion object define class members that are going to be used independently of any instance of a class, similar to static declarations in Java.
     */
    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}