package com.example.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import com.copperleaf.ballast.navigation.routing.Backstack
import com.copperleaf.ballast.navigation.routing.RouterContract
import com.copperleaf.ballast.navigation.routing.build
import com.copperleaf.ballast.navigation.routing.currentRouteOrNull
import com.copperleaf.ballast.navigation.routing.directions
import com.copperleaf.ballast.navigation.routing.renderCurrentDestination
import com.example.noteapp.home.HomeScreen
import com.example.noteapp.home.HomeScreenContract
import com.example.noteapp.home.HomeScreenViewModel
import com.example.noteapp.model.NoteRepository
import com.example.noteapp.nav.AppScreen
import com.example.noteapp.nav.RouterViewModel
import com.example.noteapp.note.create.CreateNoteScreen
import com.example.noteapp.note.create.CreateNoteScreenViewModel
import com.example.noteapp.ui.theme.NoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val application = application as NoteApplication

        setContent {
            RootLayout(noteRepository = application.noteRepo)
        }
    }
}

@Composable
fun RootLayout(
    noteRepository: NoteRepository
) {
   val scope = rememberCoroutineScope()
   val router = remember(scope) { RouterViewModel(scope) }
   val routerState: Backstack<AppScreen> by router.observeStates().collectAsState()

    NoteAppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
              when (routerState.currentRouteOrNull) {
                  AppScreen.Home -> FloatingActionButton(onClick = {
                      router.trySend(
                          RouterContract.Inputs.GoToDestination(
                              AppScreen.CreateNote.directions().build()
                          )
                      )
                  }) {
                      Icon(
                          painter = rememberVectorPainter(image = Icons.Default.Add), 
                          contentDescription = "Add note"
                      )

                  }
                  else -> Unit
              }
            }
        ) { rootPadding ->
            routerState.renderCurrentDestination(
                route = { appScreen ->  
                    Surface(Modifier.padding(rootPadding)) {
                        when (appScreen) {
                            AppScreen.Home -> {
                                val homeVm = remember { HomeScreenViewModel(scope, router, noteRepository) }
                                val state by homeVm.observeStates().collectAsState()
                                homeVm.trySend(HomeScreenContract.Inputs.InitialiseState) // Initialise state

                                HomeScreen(state = state) { homeVm.trySend(it) }
                            }
                            
                            AppScreen.CreateNote -> {
                                val createNoteViewModel = remember { CreateNoteScreenViewModel(scope, router, noteRepository)}
                                val state by createNoteViewModel.observeStates().collectAsState()
                                
                                CreateNoteScreen(state = state ) {
                                    createNoteViewModel.trySend(it)
                                    
                                }
                            }
                            AppScreen.UpdateNote -> {
                                val noteId = this@renderCurrentDestination.pathParameters["note_id"]?.first()
                                require(noteId != null)
                                val noteVm = remember { CreateNoteScreenViewModel(scope, router, noteRepository)}
                                val state by noteVm.observeStates().collectAsState()
                                CreateNoteScreen(state = state) { noteVm.trySend(it) }
                            }
                        }
                        
                    }
                },
                notFound = { Text(text = "Not Found")}
            )

        }
    }

}
