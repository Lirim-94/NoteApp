package com.example.noteapp.home

import com.copperleaf.ballast.InputHandler
import com.copperleaf.ballast.InputHandlerScope
import com.example.noteapp.model.NoteRepository


typealias HomeScreenContractInputHandler = InputHandler<HomeScreenContract.Inputs, HomeScreenContract.Events, HomeScreenContract.State>
typealias HomeScreenContractInputHandlerScope = InputHandlerScope<HomeScreenContract.Inputs, HomeScreenContract.Events, HomeScreenContract.State>

class HomeScreenInputHandler(val noteRepo : NoteRepository) : HomeScreenContractInputHandler {

    override suspend fun HomeScreenContractInputHandlerScope.handleInput(input: HomeScreenContract.Inputs) = when (input) {
        HomeScreenContract.Inputs.CreateNote -> postEvent(HomeScreenContract.Events.NavigateToCreateNewNoteScreen)

        is HomeScreenContract.Inputs.NavigateToUpdate -> postEvent(HomeScreenContract.Events.NavigateToUpdateNoteScreen(input.note.id))
        is HomeScreenContract.Inputs.DeleteNote -> noteRepo.deleteNote(input.note)

        }
    }