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
suspend fun getNotes(): MutableList<Note>

@Query("SELECT * FROM note WHERE id = :id")
suspend fun getNoteById(id: String): Note?

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insertNote(note: Note)

@Query("DELETE FROM note WHERE id = :noteId")
suspend fun deleteNote(noteId: String)


@Update(onConflict = OnConflictStrategy.REPLACE)
suspend fun updateNote(note: Note)

}