package com.example.noteapp

import android.app.Application
import androidx.room.Room
import com.example.noteapp.data.NoteDao
import com.example.noteapp.data.NoteDatabase
import com.example.noteapp.model.NoteRepository

class NoteApplication : Application() {


    lateinit var noteRepo : NoteRepository

    override fun onCreate() {
        super.onCreate()

        val db = Room.databaseBuilder(this, NoteDatabase::class.java, NoteDatabase.DATABASE_NAME).build()
        noteRepo = NoteRepository(db.noteDao())
    }
}


