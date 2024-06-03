package com.example.noteapp.home

import com.copperleaf.ballast.BallastViewModelConfiguration
import com.copperleaf.ballast.build
import com.copperleaf.ballast.core.BasicViewModel
import com.copperleaf.ballast.navigation.vm.Router
import com.copperleaf.ballast.withViewModel
import com.example.noteapp.model.NoteRepository
import com.example.noteapp.nav.AppScreen
import kotlinx.coroutines.CoroutineScope

class HomeScreenViewModel(
    viewModelCoroutineScope: CoroutineScope,
    router: Router<AppScreen>,
    noteRepository: NoteRepository
) : BasicViewModel<
        HomeScreenContract.Inputs,
        HomeScreenContract.Events,
        HomeScreenContract.State
        >(
    config = BallastViewModelConfiguration.Builder()
        .withViewModel(
            initialState = HomeScreenContract.State(noteList = emptyList()),
            inputHandler = HomeScreenInputHandler(noteRepository),
        )
        .build(),
    eventHandler = HomeScreenEventHandler(router),
    coroutineScope = viewModelCoroutineScope,
)