package com.example.niftynotes.repo

import com.example.niftynotes.db.AppDatabase
import com.example.niftynotes.db.NoteEntity
import com.example.niftynotes.db.NoteEntityDao
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val database: AppDatabase) {
    private val dao: NoteEntityDao by lazy {
        database.getDao()
    }

    suspend fun addNote(noteEntity: NoteEntity) {
        dao.insert(noteEntity)
    }

    suspend fun loadNotes(): Flow<List<NoteEntity>> {
        return dao.getAllAsFlow()
    }

    suspend fun deleteNoteById(note: NoteEntity) {
        dao.deleteNote(note)
    }

}