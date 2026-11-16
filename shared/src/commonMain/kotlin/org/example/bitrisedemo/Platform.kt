package org.example.bitrisedemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform