package com.example.noteapp.note.update


object UpdateNoteScreenContract {

    data class State (
        val noteTitle: String,
        val noteContent: String,
        val isUpdateButtonEnabled: Boolean = false
    )

    sealed interface Inputs {
        data class ChangeNoteTitle(val note: String) : Inputs
        data class ChangeNoteContent(val note: String) : Inputs
        data object UpdateNote : Inputs
        data object GoBackToHome : Inputs
    }

    sealed interface Events {
        data object GoBackToHome : Events
    }
}

