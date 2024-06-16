package com.example.noteapp.note.update

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun UpdateNoteScreen(
    state: UpdateNoteScreenContract.State,
    postInput: (UpdateNoteScreenContract.Inputs) -> Unit
) {
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

        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = state.newTitle,
                onValueChange = {
                    postInput(UpdateNoteScreenContract.Inputs.ChangeNoteTitle(it))
                },
                placeholder = { Text(text = "Title") }
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = state.newContent,
                onValueChange = {
                    postInput(UpdateNoteScreenContract.Inputs.ChangeNoteContent(it))
                },
                placeholder = { Text(text = "Write your note here") }
            )
            Spacer(modifier = Modifier.padding(bottom = 10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.Center,
                verticalAlignment = Alignment.CenterVertically)
            {
                Button(
                    onClick = {
                        postInput(UpdateNoteScreenContract.Inputs.UpdateNote)
                    },
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .padding(horizontal = 5.dp),
                    enabled = state.isUpdateButtonEnabled,
                ) {
                    val text = "Update Note"
                    Text(text = text)
                }
                Button(
                    onClick = {
                        postInput(UpdateNoteScreenContract.Inputs.DeleteNote(state.note?.id))
                    },
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .padding(5.dp)) {
                    val text = "Delete Note"
                    Text(
                        text = text
                    )

                }
            }





        }
    }

}