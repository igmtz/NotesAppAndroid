package com.gmtz.notesappandroid.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gmtz.notesappandroid.data.Note
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    onDeleteClick: () -> Unit,
    onLockClick: () -> Unit,
    backgroundColor: Color
) {

    var isOptionsButtonActive by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            drawRoundRect(
                color = backgroundColor,
                size = size,
                cornerRadius = CornerRadius(cornerRadius.toPx())
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.content,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primaryVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = formatDate(note.timestamp),
                style = MaterialTheme.typography.caption,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colors.primary,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis,
            )
        }
        IconButton(
            onClick = { isOptionsButtonActive = !isOptionsButtonActive },
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Options",
                tint = MaterialTheme.colors.primary
            )
        }
    }
}

fun formatDate(timestamp: Long): String {
    val currentDate = Calendar.getInstance()
    val noteDate = Calendar.getInstance().apply { timeInMillis = timestamp }

    val format = when {
        currentDate.get(Calendar.YEAR) == noteDate.get(Calendar.YEAR) &&
                currentDate.get(Calendar.MONTH) == noteDate.get(Calendar.MONTH) &&
                currentDate.get(Calendar.DAY_OF_MONTH) == noteDate.get(Calendar.DAY_OF_MONTH) ->
            "Today " + SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(timestamp))

        currentDate.get(Calendar.YEAR) == noteDate.get(Calendar.YEAR) &&
                currentDate.get(Calendar.WEEK_OF_YEAR) == noteDate.get(Calendar.WEEK_OF_YEAR) ->
            SimpleDateFormat("EEEE HH:mm", Locale.getDefault()).format(Date(timestamp))

        currentDate.get(Calendar.YEAR) == noteDate.get(Calendar.YEAR) ->
            SimpleDateFormat("MMMM dd", Locale.getDefault()).format(Date(timestamp))

        else ->
            SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(Date(timestamp))
    }

    return format
}