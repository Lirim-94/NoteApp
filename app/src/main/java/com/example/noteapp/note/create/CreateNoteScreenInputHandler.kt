package com.example.noteapp.note.create

import com.copperleaf.ballast.InputHandler
import com.copperleaf.ballast.InputHandlerScope
import com.example.noteapp.model.Note
import com.example.noteapp.model.NoteRepository

typealias CreateNoteScreenContractInputHandler =
        InputHandler<CreateNoteScreenContract.Inputs,
        CreateNoteScreenContract.Events, CreateNoteScreenContract.State>
typealias CreateNoteScreenContractInputHandlerScope =
        InputHandlerScope<CreateNoteScreenContract.Inputs,
                CreateNoteScreenContract.Events, CreateNoteScreenContract.State>

class CreateNoteScreenInputHandler(private val noteRepository: NoteRepository) : CreateNoteScreenContractInputHandler {

    override suspend fun CreateNoteScreenContractInputHandlerScope.handleInput(input: CreateNoteScreenContract.Inputs) {
        when (input) {
            is CreateNoteScreenContract.Inputs.ChangeNote -> {
                    updateStateAndGet { it.copy(note = input.note) }
                }

            is CreateNoteScreenContract.Inputs.CreateNote -> {
                val state = getCurrentState()
                val note = Note(
                    id = state.hashCode().toString(),
                    content = state.note,
                    title = state.title
                )
                noteRepository.insertNote(note)
                postEvent(CreateNoteScreenContract.Events.GoBackToHome)
        }
            is CreateNoteScreenContract.Inputs.ChangeTitle -> {
                updateStateAndGet { it.copy(title = input.noteTitle) }
            }

            CreateNoteScreenContract.Inputs.GoBackToHome -> postEvent(CreateNoteScreenContract.Events.GoBackToHome)


        }
    }

}
