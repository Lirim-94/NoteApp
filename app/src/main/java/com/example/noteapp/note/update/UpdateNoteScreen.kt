package com.example.noteapp.note.update

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp

@Composable
fun UpdateNoteScreen(
    state: UpdateNoteScreenContract.State,
    postInput: (UpdateNoteScreenContract.Inputs) -> Unit
) {
    var textFieldValueTitle by remember { mutableStateOf(state.noteTitle) }
    var textFieldValueContent by remember { mutableStateOf(state.noteContent) }

    Column(Modifier.padding(horizontal = 16.dp)) {
        IconButton(
            onClick = { postInput(UpdateNoteScreenContract.Inputs.GoBackToHome) },
            modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
        ) {
            Icon(
                painter = rememberVectorPainter(image = Icons.Default.ArrowBack),
                contentDescription = "Back"
            )
        }

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            TextField(
                value = textFieldValueTitle,
                onValueChange = {
                    textFieldValueTitle = it
                    postInput(UpdateNoteScreenContract.Inputs.ChangeNoteTitle(it))
                },
                placeholder = { Text(text = "Title") }
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = textFieldValueContent,
                onValueChange = {
                    textFieldValueContent = it
                    postInput(UpdateNoteScreenContract.Inputs.ChangeNoteContent(it))
                },
                placeholder = { Text(text = "Write your note here") }
            )
            Button(
                onClick = {
                    postInput(UpdateNoteScreenContract.Inputs.UpdateNote)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
                    .padding(horizontal = 16.dp),
                enabled = state.isUpdateButtonEnabled,
            ) {
                val text = "Update Note"
                Text(text = text)
            }
        }
    }

}