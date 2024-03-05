package com.gmtz.notesappandroid.domain.usescases

import com.gmtz.notesappandroid.data.Note
import com.gmtz.notesappandroid.data.NoteRepository
import com.gmtz.notesappandroid.domain.NoteOrder
import com.gmtz.notesappandroid.domain.OrderType
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNotesTest {

    @RelaxedMockK
    private lateinit var noteRepository: NoteRepository

    lateinit var getNotes: GetNotes

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getNotes = GetNotes(noteRepository)
    }

    @Test
    fun givenNoParameter_whenGetNotesCalled_thenGetNotesFromDatabaseInDateDescending () {
        val notesList = flowOf(listOf(
            Note("Title", "Content", 1646445900000),
            Note("Another title", "Another content", 1646422200000),
            Note("To do list", "Homework", 1646384400000),
            Note("Groceries", "Milk, Eggs, Bread", 1646358000000)
        ))
        every { noteRepository.getNotes() } returns notesList

        val response = getNotes(NoteOrder.Title(OrderType.Descending))

        verify(exactly = 1) { noteRepository.getNotes() }

    }

}
