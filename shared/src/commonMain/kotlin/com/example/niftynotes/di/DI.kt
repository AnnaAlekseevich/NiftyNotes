package com.example.niftynotes.di

import com.example.niftynotes.AddNoteViewModel
import com.example.niftynotes.NoteViewModel
import com.example.niftynotes.repo.NoteRepository
import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import kotlin.reflect.KClass

expect fun platformModule(): Module

fun vmModule(): Module {
    return module {
        single { NoteRepository(get()) }
        factory { AddNoteViewModel(get()) }
        factory { NoteViewModel(get()) }
    }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(platformModule(), vmModule())

    }

object KoinF {
    var di: Koin? = null

    fun setupKoin(appDeclaration: KoinAppDeclaration = {}) {
        if (di == null) {
            di = initKoin(appDeclaration).koin
        }
    }
}