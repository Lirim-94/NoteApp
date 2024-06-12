package com.example.noteapp.note.update

import com.copperleaf.ballast.EventHandler
import com.copperleaf.ballast.EventHandlerScope
import com.copperleaf.ballast.navigation.routing.RouterContract
import com.copperleaf.ballast.navigation.vm.Router
import com.example.noteapp.nav.AppScreen

typealias CreateSessionScreenContractEventHandler = EventHandler<UpdateNoteScreenContract.Inputs, UpdateNoteScreenContract.Events, UpdateNoteScreenContract.State>
typealias CreateSessionScreenContractEventHandlerScope = EventHandlerScope<UpdateNoteScreenContract.Inputs, UpdateNoteScreenContract.Events, UpdateNoteScreenContract.State>

class UpdateNoteScreenEventHandler(
    private val router: Router<AppScreen>
) : CreateSessionScreenContractEventHandler {

    override suspend fun CreateSessionScreenContractEventHandlerScope.handleEvent(
        event: UpdateNoteScreenContract.Events
    ) {
        when (event) {
            UpdateNoteScreenContract.Events.GoBackToHome -> router.trySend(RouterContract.Inputs.GoBack())
        }
    }
}