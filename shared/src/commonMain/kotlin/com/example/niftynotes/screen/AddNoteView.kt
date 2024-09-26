package com.example.niftynotes.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.niftynotes.AddNoteViewModel
import niftynotes.shared.generated.resources.Res

@Composable
fun AddNoteView(
    viewModel: AddNoteViewModel,
    modifier: Modifier = Modifier,
    back: () -> Unit
) {
    val note by viewModel.note.collectAsState()

    Column(modifier = modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = "Add Note", fontSize = 22.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 20.dp)
                .padding(vertical = 10.dp),
            shape = RoundedCornerShape(16.dp),
            value = note.noteTitle,
            onValueChange = {
                viewModel.onUpdateTitle(it)
            },
            placeholder = { Text("Enter title:") },
            singleLine = true,
            label = { Text("Title:") },
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 120.dp)
                .padding(vertical = 10.dp),
            shape = RoundedCornerShape(16.dp),
            value = note.noteContent,
            onValueChange = {
                viewModel.onUpdateNote(it)
            },
            placeholder = { Text("Enter your note:") },
            singleLine = true,
            label = { Text("Note:") },
            minLines = 15,
            maxLines = 30
        )

        Button(
            onClick = {
                viewModel.onConfirm()
                back.invoke()
            },
            modifier = Modifier.padding(vertical = 16.dp),
        ) {
            Text(text = "Add")

        }
    }
}