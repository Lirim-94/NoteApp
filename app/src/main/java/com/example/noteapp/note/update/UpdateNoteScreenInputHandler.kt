package com.example.noteapp.note.update

import android.util.Log
import com.copperleaf.ballast.InputHandler
import com.copperleaf.ballast.InputHandlerScope
import com.example.noteapp.model.NoteRepository


typealias UpdateNoteScreenContractInputHandler = InputHandler<UpdateNoteScreenContract.Inputs, UpdateNoteScreenContract.Events, UpdateNoteScreenContract.State>
typealias UpdateNoteScreenContractInputHandlerScope = InputHandlerScope<UpdateNoteScreenContract.Inputs, UpdateNoteScreenContract.Events, UpdateNoteScreenContract.State>

class UpdateNoteScreenInputHandler(
    private val noteRepo: NoteRepository
) : UpdateNoteScreenContractInputHandler {

    override suspend fun UpdateNoteScreenContractInputHandlerScope.handleInput(input: UpdateNoteScreenContract.Inputs) {
        when (input) {

            is UpdateNoteScreenContract.Inputs.Initialise -> {
                val note = noteRepo.getNoteById(input.noteId)
                updateState { it.copy(
                    note = note,
                    newTitle = it.newTitle.copy(text = note?.title ?: ""),
                    newContent = it.newContent.copy(note?.content ?: "")
                ) }
            }

            is UpdateNoteScreenContract.Inputs.ChangeNoteTitle -> {
                val conditions = input.title.text.isNotEmpty() && input.title.text != getCurrentState().note?.title

                updateState {
                    it.copy(
                        newTitle = input.title,
                        isUpdateButtonEnabled = conditions
                    )
                }
            }

            is UpdateNoteScreenContract.Inputs.ChangeNoteContent -> {
                val conditions = input.content.text != getCurrentState().note?.content

                val newState = updateStateAndGet {
                    it.copy(newContent = input.content, isUpdateButtonEnabled = conditions)
                }
            }

            is UpdateNoteScreenContract.Inputs.GoBackToHome -> postEvent(UpdateNoteScreenContract.Events.GoBackToHome)

            is UpdateNoteScreenContract.Inputs.UpdateNote -> {
                val state = getCurrentState()
                state.note?.copy(
                    title = state.newTitle.text,
                    content = state.newContent.text
                )?.let {
                    noteRepo.updateSession(it)
                }
                postEvent(UpdateNoteScreenContract.Events.GoBackToHome)
            }

            is UpdateNoteScreenContract.Inputs.DeleteNote -> {
                val note = input?.noteId?.let { noteRepo.getNoteById(it) }
                note?.let {
                    noteRepo.deleteNote(note.id)
                }
                postEvent(UpdateNoteScreenContract.Events.GoBackToHome)
            }
        }
    }
}