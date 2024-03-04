package com.gmtz.notesappandroid.ui.theme

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = Color.White,
    primaryVariant = Color(0xFF8f8f8f),
    secondary = Color.White,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onBackground = Color(0xffd4d4d4),
    onSurface = Color(0xff9a9a9a),
    onPrimary = Color(0xFF121212),
    onSecondary = Color(0xFF121212)
)

@SuppressLint("ConflictingOnColor")
private val ColorPalette = lightColors(
    primary = Color.Black,
    primaryVariant = Color.Black,
    secondary = Color.Black,
    background = Color.White,
    surface = Color(0xFFF0F0F0),
    onBackground = Color.Black,
    onSurface = Color.Black,
    onPrimary = Color(0xFF121212),
    onSecondary = Color.White
)

@SuppressLint("ConflictingOnColor")
private val ColorBlindPalette = lightColors(
    primary = Color.White,
    primaryVariant = Color.White,
    secondary = Color.White,
    background = Color.White,
    surface = Color(0xFF244B52),
    onBackground = Color(0xFF244B52),
    onSurface = Color.White,
    onPrimary = Color(0xFF244B52),
    onSecondary = Color(0xFF244B52)
)

@Composable
fun NoteAppAndroidTheme(isDarkThemeEnabled: Boolean, isColorBlindModeEnabled: Boolean, content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (!isColorBlindModeEnabled) {
            if (isDarkThemeEnabled) DarkColorPalette else ColorPalette
        } else {
            ColorBlindPalette
        },
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}