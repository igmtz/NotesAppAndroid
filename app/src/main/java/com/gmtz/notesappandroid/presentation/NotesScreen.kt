package com.gmtz.notesappandroid.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Dehaze
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.gmtz.notesappandroid.domain.NoteOrder
import com.gmtz.notesappandroid.domain.OrderType
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalAnimationApi
@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    var showDropDownMenu by remember { mutableStateOf(false) }
    var isNotesOrderByNameAscending by remember { mutableStateOf(false) }
    var isNotesOrderByDateAscending by remember { mutableStateOf(false) }


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddEditNoteScreen.route)
                },
                backgroundColor = MaterialTheme.colors.secondary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add note", tint = MaterialTheme.colors.onSecondary)
            }
        },
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.background,
        drawerContent = {
            NavigationDrawer(navController)
        },
        topBar = {
            TopAppBar(
                windowInsets = AppBarDefaults.topAppBarWindowInsets,
                title = {
                    Text(
                        text = "Notes",
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.primary
                    )},
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(
                            Icons.Default.Dehaze,
                            contentDescription = null,
                            tint = MaterialTheme.colors.primary
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {  }) {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "Search",
                            tint = MaterialTheme.colors.primary
                        )
                    }
                    IconButton(onClick = { showDropDownMenu = true }) {
                        Icon(
                            Icons.Filled.MoreVert,
                            contentDescription = "More",
                            tint = MaterialTheme.colors.primary)
                    }
                    DropdownMenu(
                        expanded = showDropDownMenu,
                        onDismissRequest = { showDropDownMenu = false },
                        modifier = Modifier.width(200.dp)
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                isNotesOrderByDateAscending = !isNotesOrderByDateAscending
                                if (isNotesOrderByDateAscending) {
                                    viewModel.onEvent(NotesEvent.Order(noteOrder = NoteOrder.Date(OrderType.Ascending)))
                                } else {
                                    viewModel.onEvent(NotesEvent.Order(noteOrder = NoteOrder.Date(OrderType.Descending)))
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (isNotesOrderByDateAscending) Icons.Default.ArrowDownward  else Icons.Default.ArrowUpward,
                                contentDescription = "SortByDate",
                                tint = MaterialTheme.colors.primary
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Sort by date",
                                color = MaterialTheme.colors.primary
                            )
                        }
                        DropdownMenuItem(
                            onClick = {
                                isNotesOrderByNameAscending = !isNotesOrderByNameAscending
                                if (isNotesOrderByNameAscending) {
                                    viewModel.onEvent(NotesEvent.Order(noteOrder = NoteOrder.Title(OrderType.Ascending)))
                                } else {
                                    viewModel.onEvent(NotesEvent.Order(noteOrder = NoteOrder.Title(OrderType.Descending)))
                                }}
                        ) {
                            Icon(
                                imageVector = if (isNotesOrderByNameAscending) Icons.Default.ArrowDownward  else Icons.Default.ArrowUpward,
                                contentDescription = "SortByTitle",
                                tint = MaterialTheme.colors.primary
//                                modifier = Modifier.rotate(rotationAngleName)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Sort by title",
                                color = MaterialTheme.colors.primary
                            )
                        }
                    }
                },
                backgroundColor = MaterialTheme.colors.surface
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    noteOrder = state.noteOrder,
                    onOrderChange = {
                        viewModel.onEvent(NotesEvent.Order(it))
                    }
                )
            }
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, 8.dp, 16.dp, 0.dp),
                state = rememberLazyListState()
            ) {
                items(state.notes) { note ->

                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            if (it == DismissValue.DismissedToStart) {
                                viewModel.onEvent(NotesEvent.DeleteNote(note))
                            }
                            true
                        }
                    )

                    SwipeToDismiss(
                        state = dismissState,
                        background = {
                            val dismissDirection = dismissState.dismissDirection ?: return@SwipeToDismiss
                            val color = when (dismissDirection) {
                                DismissDirection.EndToStart -> Color.Red
                                DismissDirection.StartToEnd -> Color.Transparent
                                else -> Color.Transparent
                            }
                            Box(modifier = Modifier.fillMaxSize()
                                .background(color)
                                .padding(horizontal = 20.dp, vertical = 6.dp)
                            ){
                                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete",
                                    modifier = Modifier.align(Alignment.CenterEnd))
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        },
                        dismissContent = {
                            NoteItem(
                                note = note,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate(
                                            Screen.AddEditNoteScreen.route +
                                                    "?noteId=${note.id}"
                                        )
                                    }
                                    .height(130.dp)
                                    .padding(0.dp, 0.dp, 0.dp, 12.dp),
                                onDeleteClick = {
                                    viewModel.onEvent(NotesEvent.DeleteNote(note))
                                },
                                onLockClick = {
                                    viewModel.onEvent(NotesEvent.LockNote(note))
                                },
                                backgroundColor = MaterialTheme.colors.surface
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    )
                }
            }
        }
    }
}