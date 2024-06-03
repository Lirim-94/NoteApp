package com.example.noteapp.home

import com.copperleaf.ballast.EventHandler
import com.copperleaf.ballast.EventHandlerScope
import com.copperleaf.ballast.navigation.routing.RouterContract
import com.copperleaf.ballast.navigation.routing.build
import com.copperleaf.ballast.navigation.routing.directions
import com.copperleaf.ballast.navigation.routing.pathParameter
import com.copperleaf.ballast.navigation.vm.Router
import com.example.noteapp.nav.AppScreen


typealias HomeScreenContractEventHandler = EventHandler<HomeScreenContract.Inputs, HomeScreenContract.Events, HomeScreenContract.State>
typealias HomeScreenContractEventHandlerScope = EventHandlerScope<HomeScreenContract.Inputs, HomeScreenContract.Events, HomeScreenContract.State>

class HomeScreenEventHandler(
    private val router: Router<AppScreen>
) : HomeScreenContractEventHandler {

    override suspend fun HomeScreenContractEventHandlerScope.handleEvent(
        event: HomeScreenContract.Events
    ) {
        when (event) {
            HomeScreenContract.Events.NavigateToCreateNewNoteScreen -> {
                router.trySend(RouterContract.Inputs.GoToDestination(AppScreen.CreateNote.directions().build()))
            }
            is HomeScreenContract.Events.NavigateToUpdateNoteScreen -> {
                router.trySend(RouterContract.Inputs.GoToDestination(AppScreen.UpdateNote
                    .directions()
                    .pathParameter("session_id", event.noteId)
                    .build()))

            }
        }

    }

}