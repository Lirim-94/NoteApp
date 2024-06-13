package com.example.noteapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp.model.Note


@Dao
interface NoteDao {

@Query("SELECT * FROM note")
fun getNotes(): MutableList<Note>

@Query("SELECT * FROM note WHERE id = :id")
suspend fun getNoteById(id: Int): Note?

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insertNote(note: Note)

@Delete
suspend fun deleteNote(note: Note)


@Update
suspend fun updateNote(note: Note)


}