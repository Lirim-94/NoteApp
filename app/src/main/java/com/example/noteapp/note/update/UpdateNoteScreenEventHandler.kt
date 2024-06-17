package com.example.noteapp.note.update

import com.copperleaf.ballast.EventHandler
import com.copperleaf.ballast.EventHandlerScope
import com.copperleaf.ballast.navigation.routing.RouterContract
import com.copperleaf.ballast.navigation.vm.Router
import com.example.noteapp.nav.AppScreen

typealias UpdateNoteScreenContractEventHandler = EventHandler<UpdateNoteScreenContract.Inputs, UpdateNoteScreenContract.Events, UpdateNoteScreenContract.State>
typealias UpdateNoteScreenContractEventHandlerScope = EventHandlerScope<UpdateNoteScreenContract.Inputs, UpdateNoteScreenContract.Events, UpdateNoteScreenContract.State>

class UpdateNoteScreenEventHandler(
    private val router: Router<AppScreen>
) : UpdateNoteScreenContractEventHandler {

    override suspend fun UpdateNoteScreenContractEventHandlerScope.handleEvent(
        event: UpdateNoteScreenContract.Events
    ) {
        when (event) {
            UpdateNoteScreenContract.Events.GoBackToHome -> router.trySend(RouterContract.Inputs.GoBack())
        }
    }
}