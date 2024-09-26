package com.example.niftynotes

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform