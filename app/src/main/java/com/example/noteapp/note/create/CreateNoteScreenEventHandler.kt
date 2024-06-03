package com.example.noteapp.note.create

import com.copperleaf.ballast.EventHandler
import com.copperleaf.ballast.EventHandlerScope
import com.copperleaf.ballast.navigation.routing.RouterContract
import com.copperleaf.ballast.navigation.vm.Router
import com.example.noteapp.nav.AppScreen

typealias CreateNoteScreenContractEventHandler =
        EventHandler<CreateNoteScreenContract.Inputs,
        CreateNoteScreenContract.Events, CreateNoteScreenContract.State>

typealias CreateNoteScreenContractEventHandlerScope =
        EventHandlerScope<CreateNoteScreenContract.Inputs,
        CreateNoteScreenContract.Events, CreateNoteScreenContract.State>

class CreateNoteScreenEventHandler(
    private val router: Router<AppScreen>
) : CreateNoteScreenContractEventHandler {

    override suspend fun CreateNoteScreenContractEventHandlerScope.handleEvent(
        event: CreateNoteScreenContract.Events
    )
     {
        when (event) {
            CreateNoteScreenContract.Events.GoBackToHome -> router.trySend(RouterContract.Inputs.GoBack())
        }

    }

}
