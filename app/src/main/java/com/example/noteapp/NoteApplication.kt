package com.example.noteapp

import android.app.Application
import com.example.noteapp.data.NoteDao
import com.example.noteapp.model.NoteRepository

class NoteApplication(dao: NoteDao) : Application() {

    val noteRepo = NoteRepository(dao)
}


