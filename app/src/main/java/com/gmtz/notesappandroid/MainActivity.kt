package com.gmtz.notesappandroid

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gmtz.notesappandroid.presentation.AddEditNoteScreen
import com.gmtz.notesappandroid.presentation.NotesScreen
import com.gmtz.notesappandroid.presentation.Screen
import com.gmtz.notesappandroid.ui.theme.NoteAppAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * The MainActivity class serves as the main entry point for the application's user interface and
 * it extends the ComponentActivity.
 * @property AndroidEntryPoint Indicates that it participates in Dagger Hilt's dependency injection.
 * This annotation allows Dagger Hilt to inject dependencies into this class.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private fun isDarkModeEnabled(): Boolean {
        val nightModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return nightModeFlags == Configuration.UI_MODE_NIGHT_YES
    }
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppAndroidTheme(isDarkModeEnabled()) {
                Surface(
                    color = MaterialTheme.colors.primary
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NotesScreen.route
                    ) {
                        composable(route = Screen.NotesScreen.route) {
                            NotesScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditNoteScreen.route +
                                    "?noteId={noteId}",
                            arguments = listOf(
                                navArgument(
                                    name = "noteId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            AddEditNoteScreen(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}