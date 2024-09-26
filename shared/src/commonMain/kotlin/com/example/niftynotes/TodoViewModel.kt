package com.example.niftynotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.niftynotes.db.NoteEntity
import com.example.niftynotes.repo.NoteRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    val notes: MutableSharedFlow<List<NoteEntity>> = MutableSharedFlow(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    fun loadData() {
        viewModelScope.launch {
            repository.loadNotes().collectLatest {
                notes.tryEmit(it)
            }
        }
    }

    fun deleteNote(note: NoteEntity) {
        viewModelScope.launch {
            repository.deleteNoteById(note)
        }
    }
}