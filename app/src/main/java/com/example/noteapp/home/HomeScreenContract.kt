package com.example.noteapp.home

import com.example.noteapp.model.Note

object HomeScreenContract {
    data class State(
        val noteList: List<Note>
    )

    sealed interface Inputs {
        data object CreateNote : Inputs
        data class NavigateToUpdate(val note: Note) : Inputs
        data class DeleteNote(val note: Note): Inputs

    }

    sealed interface Events {
        data object NavigateToCreateNewNoteScreen : Events
        data class NavigateToUpdateNoteScreen(val noteId: String) : Events

    }
}
