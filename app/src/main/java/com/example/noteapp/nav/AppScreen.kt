package com.example.noteapp.nav

import com.copperleaf.ballast.navigation.routing.Route
import com.copperleaf.ballast.navigation.routing.RouteAnnotation
import com.copperleaf.ballast.navigation.routing.RouteMatcher


    enum class AppScreen(
        routeFormat: String,
        override val annotations: Set<RouteAnnotation> = emptySet(),
    ) : Route {
        Home("/app/home"),
        CreateNote("/app/create_note"),
        UpdateNote("/app/update_note/{note_id}") ;

        override val matcher: RouteMatcher = RouteMatcher.create(routeFormat)
    }