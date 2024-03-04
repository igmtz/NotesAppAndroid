package com.gmtz.notesappandroid

import android.app.Application
import android.content.res.Configuration
import dagger.hilt.android.HiltAndroidApp

/**
 * The NoteApp class serves as the entry point for the application and it extends the Application class.
 * @property HiltAndroidApp This annotation is necessary to enable dependency injection
 * with Dagger Hilt in the application.
 */
@HiltAndroidApp
class NoteApp : Application() {
    fun isDarkModeEnabled(): Boolean { // TODO User preferences
        val nightModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return nightModeFlags == Configuration.UI_MODE_NIGHT_YES
    }
}