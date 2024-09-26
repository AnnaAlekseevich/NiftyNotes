package com.example.niftynotes.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteEntityDao {
    @Insert
    suspend fun insert(item: NoteEntity)

    @Query("SELECT count(*) FROM NoteEntity")
    suspend fun count(): Int

    @Query("SELECT * FROM NoteEntity")
    fun getAllAsFlow(): Flow<List<NoteEntity>>

    @Delete
    suspend fun deleteNote(note: NoteEntity)
}