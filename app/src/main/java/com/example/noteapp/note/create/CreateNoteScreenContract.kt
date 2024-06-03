package com.example.noteapp.note.create

object CreateNoteScreenContract {
    data class State(
        val note: String = "",
        val title: String = ""
    )

    sealed interface Inputs {
        data class ChangeNote(val note: String) : Inputs
        data class ChangeTitle(val noteTitle: String) : Inputs
        data object CreateNote : Inputs
        data object GoBackToHome : Inputs
    }

    sealed interface Events {
        data object GoBackToHome : Events
    }
}
