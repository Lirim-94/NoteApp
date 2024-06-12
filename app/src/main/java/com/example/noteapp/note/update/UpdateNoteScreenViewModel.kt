package com.example.noteapp.note.update

import com.copperleaf.ballast.BallastViewModelConfiguration
import com.copperleaf.ballast.build
import com.copperleaf.ballast.core.BasicViewModel
import com.copperleaf.ballast.navigation.vm.Router
import com.copperleaf.ballast.withViewModel
import com.example.noteapp.model.Note
import com.example.noteapp.model.NoteRepository
import com.example.noteapp.nav.AppScreen
import kotlinx.coroutines.CoroutineScope

class UpdateNoteScreenViewModel(
    viewModelCoroutineScope: CoroutineScope,
    router: Router<AppScreen>,
    noteRepo: NoteRepository,
    noteId: String,
    note: Note = noteRepo.getNotes().first { it.id == noteId }
) : BasicViewModel<
        UpdateNoteScreenContract.Inputs,
        UpdateNoteScreenContract.Events,
        UpdateNoteScreenContract.State
        >(
    config = BallastViewModelConfiguration.Builder()
        .withViewModel(
            initialState = UpdateNoteScreenContract.State(
                noteTitle = note.title,
                noteContent = note.content
            ),
            inputHandler = UpdateNoteScreenInputHandler(note, noteRepo),
        )
        .build(),
    eventHandler = UpdateNoteScreenEventHandler(router),
    coroutineScope = viewModelCoroutineScope,
)