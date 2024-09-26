package com.example.niftynotes.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NoteEntity")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val noteId: Long = 0,
    val noteTitle: String,
    val noteContent: String,
    val creationDate: String
)