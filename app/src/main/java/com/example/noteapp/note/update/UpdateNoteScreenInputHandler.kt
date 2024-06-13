package com.example.noteapp.note.update

import com.copperleaf.ballast.InputHandler
import com.copperleaf.ballast.InputHandlerScope
import com.example.noteapp.model.Note
import com.example.noteapp.model.NoteRepository


typealias UpdateNoteScreenContractInputHandler = InputHandler<UpdateNoteScreenContract.Inputs, UpdateNoteScreenContract.Events, UpdateNoteScreenContract.State>
typealias UpdateNoteScreenContractInputHandlerScope = InputHandlerScope<UpdateNoteScreenContract.Inputs, UpdateNoteScreenContract.Events, UpdateNoteScreenContract.State>

class UpdateNoteScreenInputHandler(
    private val note: Note,
    private val noteRepo: NoteRepository
) : UpdateNoteScreenContractInputHandler {

    override suspend fun UpdateNoteScreenContractInputHandlerScope.handleInput(input: UpdateNoteScreenContract.Inputs) {
        when (input) {
            is UpdateNoteScreenContract.Inputs.ChangeNoteTitle -> {
                val newState = updateStateAndGet {
                    it.copy(noteTitle = input.note)
                }
                canUpdateNoteButtonBeEnabled(newState)
            }

            is UpdateNoteScreenContract.Inputs.ChangeNoteContent -> {
                val newState = updateStateAndGet {
                    it.copy(noteContent = input.note)
                }
                canUpdateNoteButtonBeEnabled(newState)
            }

            is UpdateNoteScreenContract.Inputs.GoBackToHome -> postEvent(UpdateNoteScreenContract.Events.GoBackToHome)

            is UpdateNoteScreenContract.Inputs.UpdateNote -> {
                val state = getCurrentState()
                val updatedNote = note.copy(
                    title = state.noteTitle,
                    content = state.noteContent
                )
                noteRepo.updateSession(updatedNote)
                postEvent(UpdateNoteScreenContract.Events.GoBackToHome)
            }

            is UpdateNoteScreenContract.Inputs.DeleteNote -> { noteRepo.deleteNote(note) }
        }
    }

    private suspend fun UpdateNoteScreenContractInputHandlerScope.canUpdateNoteButtonBeEnabled(state: UpdateNoteScreenContract.State) {
        val createConditions = state.noteTitle != null || state.noteContent != null

        updateState {
            it.copy(isUpdateButtonEnabled = createConditions)
        }
    }
}