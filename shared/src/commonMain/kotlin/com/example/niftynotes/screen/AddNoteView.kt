@file:OptIn(ExperimentalResourceApi::class)

package com.example.niftynotes.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import niftynotes.shared.generated.resources.add_btn
import niftynotes.shared.generated.resources.hint_note
import niftynotes.shared.generated.resources.hint_note_label
import niftynotes.shared.generated.resources.hint_title
import niftynotes.shared.generated.resources.hint_title_label
import niftynotes.shared.generated.resources.title_add_note
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@Composable
fun AddNoteView(
    viewModel: AddNoteViewModel,
    modifier: Modifier = Modifier,
    back: () -> Unit
) {
    val note by viewModel.note.collectAsState()

    Column(modifier = modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = stringResource(Res.string.title_add_note), fontSize = 22.sp,
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
            placeholder = { Text(stringResource(Res.string.hint_title)) },
            singleLine = true,
            label = { Text(stringResource(Res.string.hint_title_label)) },
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
            placeholder = { Text(stringResource(Res.string.hint_note)) },
            singleLine = true,
            label = { Text(stringResource(Res.string.hint_note_label)) },
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
            Text(stringResource(Res.string.add_btn))
        }
    }
}