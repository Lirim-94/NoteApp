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
        // Retrieving data from DB requires an asynchronous (suspending) operation, so we can't do this from the initialState constructor in the HomeScreenViewModel
        // Therefor we need to define an operation that initialises the State as soon as possible
        data object InitialiseState : Inputs
    }

    sealed interface Events {
        data object NavigateToCreateNewNoteScreen : Events
        data class NavigateToUpdateNoteScreen(val noteId: String) : Events
    }
}
