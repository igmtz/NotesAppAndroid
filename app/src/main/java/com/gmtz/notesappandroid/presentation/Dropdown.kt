package com.gmtz.notesappandroid.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup

@Composable
fun Dropdown (
    modifier: Modifier = Modifier
) {
    Box {
        Popup(
            alignment = Alignment.TopCenter
        ) {
            Column(
                modifier = modifier
            ) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colors.background)
                        .fillMaxWidth()
                        .height(60.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Hide note")
                }
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colors.background)
                        .fillMaxWidth()
                        .height(60.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Delete note")
                }
            }
        }
    }
}
