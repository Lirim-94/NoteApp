package com.example.noteapp.model

import com.example.noteapp.data.NoteDao

class NoteRepository(
    private val dao: NoteDao
) {

    suspend fun getNotes(): MutableList<Note> { // Needs to suspend or it will lock the UI
        return dao.getNotes()
    }

    suspend fun getNoteById(id: String): Note? {
        return dao.getNoteById(id)
    }

    suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    suspend fun deleteNote(noteId: String) {
        dao.deleteNote(noteId)
    }

    suspend fun updateSession(note: Note) {
        dao.updateNote(note)
    }
}