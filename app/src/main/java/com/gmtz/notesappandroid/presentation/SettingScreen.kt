package com.gmtz.notesappandroid.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import com.gmtz.notesappandroid.data.UserPreferences
import kotlinx.coroutines.Dispatchers

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "RestrictedApi")
@Composable
fun SettingsScreen(
    navController: NavController,
    userPreferences: UserPreferences,
    isDarkMode: Boolean,
    isColorBlindMode: Boolean
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                windowInsets = AppBarDefaults.topAppBarWindowInsets,
                title = {
                    Text(
                        text = "Settings",
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.primary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            navController.navigate(Screen.NotesScreen.route)
                        }
                    }) {
                        Icon(
                            Icons.Default.ArrowBackIosNew,
                            contentDescription = null,
                            tint = MaterialTheme.colors.primary
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.surface
            )
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .padding(top = 10.dp)
        ) {
            Text(
                text = "General",
                color = MaterialTheme.colors.onSurface,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )
            GeneralSettingItem(
                isColorBlindMode = isColorBlindMode,
                isDarkMode = isDarkMode,
                userPreferences = userPreferences,
                text = "Dark Mode",
                description = "On / Off",
                icon = Icons.Default.RemoveRedEye,
                mode = "dark_mode"
            )
            GeneralSettingItem(
                isColorBlindMode = isColorBlindMode,
                isDarkMode = isDarkMode,
                userPreferences = userPreferences,
                text = "Colorblind Mode",
                description = "On / Off",
                icon = Icons.Default.Accessibility,
                mode = "colorblind_mode"
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun GeneralSettingItem(
    mode: String,
    isColorBlindMode: Boolean,
    isDarkMode: Boolean,
    userPreferences: UserPreferences,
    icon: ImageVector,
    text: String,
    description: String
) {

    val scope = rememberCoroutineScope()

    Card(
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .fillMaxWidth().height(75.dp)
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(34.dp).offset(12.dp)
                ) {
                    Icon(
                        icon,
                        contentDescription = "",
                        tint = MaterialTheme.colors.onSurface
                    )
                }
                Spacer(modifier = Modifier.width(14.dp))
                Column(
                    modifier = Modifier.offset(y = (2).dp)
                ) {
                    Text(
                        text = text,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 18.sp,
                        modifier = Modifier.offset(y = (-8).dp)
                    )
                    Text(
                        text = description,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 14.sp,
                        modifier = Modifier
                    )
                }
            }
            Row(modifier = Modifier.offset(x = (-8).dp)) {
                Switch(
                    checked = when(mode) {
                        "dark_mode" -> isDarkMode
                        "colorblind_mode" -> isColorBlindMode
                        else -> isDarkMode
                    },
                    onCheckedChange = { checked ->
                        scope.launch(Dispatchers.IO) {
                            when(mode) {
                                "dark_mode" -> userPreferences.updateDarkMode(checked)
                                "colorblind_mode" -> userPreferences.updateColorBlindMode(checked)
                            }

                        }
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colors.secondary,
                        uncheckedThumbColor = Color.Gray
                    )
                )
            }
        }
    }
}