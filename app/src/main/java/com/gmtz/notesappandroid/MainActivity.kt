package com.gmtz.notesappandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gmtz.notesappandroid.data.UserPreferences
import com.gmtz.notesappandroid.data.ktor.PostResponse
import com.gmtz.notesappandroid.data.ktor.PostService
import com.gmtz.notesappandroid.presentation.AddEditNoteScreen
import com.gmtz.notesappandroid.presentation.KtorClientScreen
import com.gmtz.notesappandroid.presentation.NotesScreen
import com.gmtz.notesappandroid.presentation.Screen
import com.gmtz.notesappandroid.presentation.SettingsScreen
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

    private val client = PostService.create()
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userPreferences = UserPreferences(this)
        setContent {
            val navController = rememberNavController()
            val darkMode = userPreferences.getDarkMode.collectAsState(initial = false)
            val isDarkMode = darkMode.value
            val colorBlindMode = userPreferences.getColorBlindMode.collectAsState(initial = false)
            val isColorBlindMode = colorBlindMode.value

            val posts = produceState<List<PostResponse>>(
                initialValue = emptyList(),
                producer = {
                    value = client.getPosts()
                }
            )

            NoteAppAndroidTheme(isDarkMode, isColorBlindMode) {
                Surface(
                    color = MaterialTheme.colors.primary
                ) {
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
                        composable(route = Screen.SettingScreen.route) {
                            SettingsScreen(navController, userPreferences, isDarkMode, isColorBlindMode)
                        }
                        composable(route = Screen.KtorClientScreen.route) {
                            KtorClientScreen(items = posts.value, navController)
                        }
                    }
                }
            }
        }
    }
}