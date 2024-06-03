package com.example.noteapp.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.noteapp.model.Note


    @Composable
    fun HomeScreen(
        state: HomeScreenContract.State,
        postInput: (HomeScreenContract.Inputs) -> Unit
    ) {
        Column(Modifier.padding(horizontal = 16.dp)) {
            if (state.noteList.isEmpty()) {
                Text(
                    text = "Currently no notes",
                    modifier = Modifier.padding(16.dp)
                )
            }
            state.noteList.sortedBy { it.id }.forEach {
                NoteView(
                    note = it,
                    noteViewActions = NoteViewActions(
                        onCardClickAction = { postInput(HomeScreenContract.Inputs.NavigateToUpdate(it))}
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
        onClick = { noteViewActions.onCardClickAction() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Text(text = "Title: ${note.title}")
        Divider()
        Text(text = "${note.content}" )
        }

    }

data class NoteViewActions(
    val onCardClickAction: () -> Unit,
)