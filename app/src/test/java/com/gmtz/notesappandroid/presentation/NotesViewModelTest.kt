package com.gmtz.notesappandroid.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gmtz.notesappandroid.data.Note
import com.gmtz.notesappandroid.domain.usescases.GetNotes
import com.gmtz.notesappandroid.domain.usescases.NoteUseCases
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
@ExperimentalCoroutinesApi
class NotesViewModelTest {

    @RelaxedMockK
    private lateinit var noteUseCases: NoteUseCases

    private lateinit var notesViewModel: NotesViewModel

    @get:Rule
    var rule:InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        notesViewModel = NotesViewModel(noteUseCases)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun whenViewModel_getNotes() = runTest {
        val notesList = listOf(
            Note("Title", "Content", 1646445900000),
            Note("Another title", "Another content", 1646422200000),
            Note("To do list", "Homework", 1646384400000),
            Note("Groceries", "Milk, Eggs, Bread", 1646358000000)
        )

        coEvery { noteUseCases.getNotes() } returns flowOf(notesList)
    }
}