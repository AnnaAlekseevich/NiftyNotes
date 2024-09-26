package com.example.niftynotes.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.niftynotes.AddNoteViewModel
import com.example.niftynotes.NoteViewModel
import com.example.niftynotes.di.KoinF
import com.example.niftynotes.screen.AddNoteView
import com.example.niftynotes.screen.NoteList
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition

enum class TaskScreen {
    NoteList,
    NoteItem
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    currentScreen: TaskScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(text = currentScreen.name, style = MaterialTheme.typography.bodyLarge)
        },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back"
                    )
                }
            }
        })
}

@Composable
fun App() {
    PreComposeApp {
        val navigator = rememberNavigator()
        val modifier = Modifier
        val backStackEntry by navigator.currentEntry.collectAsState(null)
        KoinF.setupKoin()
        // Get the name of the current screen
        val currentScreen = TaskScreen.valueOf(
            backStackEntry?.route?.route ?: TaskScreen.NoteList.name
        )
        val canNavigateBack by navigator.canGoBack.collectAsState(false)
        Scaffold(
            topBar = {
                AppBar(
                    currentScreen = currentScreen,
                    canNavigateBack = canNavigateBack,
                    navigateUp = { navigator.goBack() }
                )
            }
        ) { paddingValues ->

            NavHost(
                modifier = Modifier.padding(paddingValues),
                navigator = navigator,
                navTransition = NavTransition(),
                initialRoute = TaskScreen.NoteList.name
            ) {
                scene(
                    route = TaskScreen.NoteList.name,
                    navTransition = NavTransition()
                ) {
                    NoteList(KoinF.di?.get<NoteViewModel>()!!, modifier) {
                        navigator.navigate(TaskScreen.NoteItem.name)
                    }
                }
                scene(route = TaskScreen.NoteItem.name) {
                    AddNoteView(KoinF.di?.get<AddNoteViewModel>()!!, modifier) {
                        navigator.goBack()
                    }
                }
            }
        }
    }
}