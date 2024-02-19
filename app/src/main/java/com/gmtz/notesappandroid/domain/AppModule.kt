package com.gmtz.notesappandroid.domain

import android.app.Application
import androidx.room.Room
import com.gmtz.notesappandroid.domain.usescases.DeleteNote
import com.gmtz.notesappandroid.domain.usescases.GetNote
import com.gmtz.notesappandroid.domain.usescases.GetNotes
import com.gmtz.notesappandroid.domain.usescases.NoteUseCases
import com.gmtz.notesappandroid.data.NoteDatabase
import com.gmtz.notesappandroid.data.NoteRepository
import com.gmtz.notesappandroid.domain.usescases.AddNote
import com.gmtz.notesappandroid.domain.usescases.LockNote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * The AppModule class is responsible for providing dependency injection configurations
 * using Dagger Hilt.
 * @property Module It is used to create a special class that helps Dagger-Hilt understand
 * how to create and provide instances of different objects, like database classes.
 * @property InstallIn It tells Hilt where to install a module.
 * @property Provides It is an annotation that is used to define a method inside a @Module
 * class that provides an instance of an object that can be injected. This method is used
 * to specify how to create the object that will be provided.
 * @property Singleton This annotation is used to tell Dagger-Hilt to only create a single
 * instance of the provided object, which will be shared across the entire application.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    /**
     * Provides an instance of the NoteDatabase class.
     */
    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository),
            lockNote = LockNote()
        )
    }
}