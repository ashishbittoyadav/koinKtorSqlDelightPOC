package com.ashish.multiplateformapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform