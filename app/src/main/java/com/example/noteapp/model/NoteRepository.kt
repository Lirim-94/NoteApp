package com.example.noteapp.model

import com.example.noteapp.data.NoteDao

class NoteRepository(
    private val dao: NoteDao
)  {

      fun getNotes(): MutableList<Note> {
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
}