package com.example.niftynotes.di

import com.example.niftynotes.db.AppDatabase
import com.example.niftynotes.db.getDatabase
import org.koin.dsl.module

actual fun platformModule() = module {
    single<AppDatabase> { getDatabase(get()) }
}