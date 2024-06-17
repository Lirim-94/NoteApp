package com.example.noteapp.note.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CreateNoteScreen(
    state: CreateNoteScreenContract.State,
    postInput: (CreateNoteScreenContract.Inputs) -> Unit 
) {
    var noteContent by remember { mutableStateOf(state.note) }
    var noteTitle by remember { mutableStateOf(state.title) }

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        IconButton(
            onClick = { postInput(CreateNoteScreenContract.Inputs.GoBackToHome) },
            modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
        ) {
            Icon(
                painter = rememberVectorPainter(image = Icons.Default.ArrowBack),
                contentDescription = "Back"
            )
        }
                Column {
                TextField(
                    placeholder = { Text(
                        text = "Title")},
                    value = noteTitle,
                    onValueChange = {
                        noteTitle = it
                        postInput(CreateNoteScreenContract.Inputs.ChangeTitle(it))
                    },
                )

                TextField(
                    placeholder = { Text(text = "Write your note here")},
                    value = noteContent,
                    onValueChange = {
                        noteContent = it
                        postInput(CreateNoteScreenContract.Inputs.ChangeNote(it))
                    },
                )

                Button(
                    onClick = {
                        postInput(CreateNoteScreenContract.Inputs.CreateNote)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                        .padding(horizontal = 16.dp),
                    enabled = if (noteContent != null || noteTitle != null) true else false
                ) {

                    val text = "Create note"
                    Text(text = text)

                }
            }



    }

}

data class CardViewActions(
   val onCardClickAction: () -> Unit
)

@Preview
@Composable
fun CreateNoteScreenPreview() {
    val state = CreateNoteScreenContract.State()
    val input = CreateNoteScreenContract.Inputs.CreateNote
    CreateNoteScreen(state = state, postInput = {input})
}