package com.example.noteapp.model

import com.example.noteapp.data.NoteDao

class NoteRepository(
    private val dao: NoteDao
) {

    private var notes = mutableListOf<Note>()
    fun getNotes(): MutableList<Note> { // Needs to suspend or it will lock the UI
        return dao.getNotes()
    }

    suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }

    suspend fun updateSession(note: Note) {
        dao.updateNote(note)
    }
}