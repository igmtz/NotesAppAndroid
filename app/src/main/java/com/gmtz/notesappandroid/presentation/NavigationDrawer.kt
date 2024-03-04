package com.gmtz.notesappandroid.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun NavigationDrawer(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(Screen.SettingScreen.route)
                }
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                tint = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.width(32.dp))
            Text(
                text = "Settings",
                color = MaterialTheme.colors.primary
            )
        }
    }
    
}