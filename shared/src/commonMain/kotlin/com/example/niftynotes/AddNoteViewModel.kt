package com.example.niftynotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.niftynotes.db.NoteEntity
import com.example.niftynotes.repo.NoteRepository
import com.example.niftynotes.screen.NoteEntityViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddNoteViewModel(
    private val taskRepository: NoteRepository
) : ViewModel() {

    val note: MutableStateFlow<NoteEntityViewState> = MutableStateFlow(NoteEntityViewState())
    fun onConfirm() {
        viewModelScope.launch {
            taskRepository.addNote(
                NoteEntity(
                    noteTitle = note.value.noteTitle,
                    noteContent = note.value.noteContent,
                    creationDate = ""
                )
            )
        }
    }

    fun onUpdateTitle(noteTitle: String) {
        note.update { it.copy(noteTitle = noteTitle) }
    }

    fun onUpdateNote(noteContent: String) {
        note.update { it.copy(noteContent = noteContent) }
    }
}