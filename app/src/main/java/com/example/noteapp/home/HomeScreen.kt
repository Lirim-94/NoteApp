package com.example.noteapp.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.example.noteapp.model.Note


    @Composable
    fun HomeScreen(
        state: HomeScreenContract.State,
        postInput: (HomeScreenContract.Inputs) -> Unit
    ) {
        Column(
            Modifier
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (state.noteList.isEmpty()) {
                Text(
                    text = "Currently no notes",
                    modifier = Modifier.padding(16.dp)
                )
            }
            state.noteList.sortedBy { it.id }.forEach {
                NoteView(
                    note = it,
                    noteViewActions =
                    NoteViewActions(
                        onCardClickAction = {
                            postInput(HomeScreenContract.Inputs.NavigateToUpdate(it))
                        }, onDeleteClickAction = {
                            postInput(HomeScreenContract.Inputs.DeleteNote(it))
                        }
                    )

                )
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
            }
        }
            }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteView(
    note: Note,
    noteViewActions: NoteViewActions
) {
    Card(
        colors = CardDefaults.cardColors(Color.White),
        onClick = { noteViewActions.onCardClickAction() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Text(text = "${note.title}")
        Divider()
        Text(text = "${note.content}" )
        IconButton(onClick = { noteViewActions.onDeleteClickAction() } ) {
            Icon(painter = rememberVectorPainter(image = Icons.Default.Delete),
                contentDescription = "Delete note" )

        }
        }

    }

data class NoteViewActions(
    val onCardClickAction: () -> Unit,
    val onDeleteClickAction: () -> Unit
)