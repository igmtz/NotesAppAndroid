package com.gmtz.notesappandroid.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = BoneWhite,
    primaryVariant = BoneWhite,
    secondary = SoftBlack,
    background = BackgroundDarkGray,
)

private val ColorPalette = darkColors(
    primary = SoftBlack,
    primaryVariant = BoneWhite,
    secondary = MilitaryGreen,
    background = BoneWhite
)

@Composable
fun NoteAppAndroidTheme(isDarkThemeEnabled: Boolean, content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (isDarkThemeEnabled) DarkColorPalette else ColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}