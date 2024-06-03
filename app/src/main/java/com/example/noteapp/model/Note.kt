package com.example.noteapp.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey val id: String,
    val title: String,
    val content: String
)
{
    companion object {
        val colors = listOf(Color.Red, Color.Blue, Color.Green, Color.Yellow)
    }
}

class InvalidNoteException(message: String) : Exception(message)

