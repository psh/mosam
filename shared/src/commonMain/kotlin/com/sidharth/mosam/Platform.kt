package com.sidharth.mosam

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform