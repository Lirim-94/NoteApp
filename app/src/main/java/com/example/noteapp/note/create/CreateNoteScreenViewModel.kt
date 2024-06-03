package com.example.noteapp.note.create

import com.copperleaf.ballast.BallastViewModelConfiguration
import com.copperleaf.ballast.build
import com.copperleaf.ballast.core.BasicViewModel
import com.copperleaf.ballast.navigation.vm.Router
import com.copperleaf.ballast.withViewModel
import com.example.noteapp.model.NoteRepository
import com.example.noteapp.nav.AppScreen
import kotlinx.coroutines.CoroutineScope

class CreateNoteScreenViewModel(
    viewModelCoroutineScope: CoroutineScope,
    router: Router<AppScreen>,
    noteRepository: NoteRepository,
) : BasicViewModel<
        CreateNoteScreenContract.Inputs,
        CreateNoteScreenContract.Events,
        CreateNoteScreenContract.State
        >(
    config = BallastViewModelConfiguration.Builder()
        .withViewModel(
            initialState = CreateNoteScreenContract.State(),
            inputHandler = CreateNoteScreenInputHandler(noteRepository),
        )
        .build(),
    eventHandler = CreateNoteScreenEventHandler(router),
    coroutineScope = viewModelCoroutineScope,
)