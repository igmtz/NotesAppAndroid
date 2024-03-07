package com.gmtz.notesappandroid.presentation

sealed class Screen(val route: String) {
    object NotesScreen: Screen("notes_screen")
    object AddEditNoteScreen: Screen("add_edit_note_screen")
    object SettingScreen: Screen("settings_screen")
    object KtorClientScreen: Screen("ktor_client_screen")
}