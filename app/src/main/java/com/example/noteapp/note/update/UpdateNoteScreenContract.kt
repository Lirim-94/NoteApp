package com.example.noteapp.note.update

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.input.TextFieldValue
import com.example.noteapp.model.Note


object UpdateNoteScreenContract {

    @Immutable
    data class State (
        val note: Note? = null,
        val newTitle: TextFieldValue = TextFieldValue(),
        val newContent: TextFieldValue = TextFieldValue(),
        val isUpdateButtonEnabled: Boolean = false
    )

    sealed interface Inputs {
        data class Initialise(val noteId: String) : Inputs
        data class ChangeNoteTitle(val title: TextFieldValue) : Inputs
        data class ChangeNoteContent(val content: TextFieldValue) : Inputs
        data object UpdateNote : Inputs
        data class DeleteNote(val noteId: String?) : Inputs
        data object GoBackToHome : Inputs
    }

    sealed interface Events {
        data object GoBackToHome : Events
    }
}

